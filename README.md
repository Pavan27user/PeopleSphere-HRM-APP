# PeopleSphere HRMS — Manual Deployment Training Project

A training-grade enterprise HRMS with four Spring Boot REST microservices, a Spring Cloud Gateway, a React frontend, and MySQL. The intended exercise is to provision AWS infrastructure and deploy everything manually before introducing Terraform and Jenkins.

## Components and ports

| Component | Port | Database |
|---|---:|---|
| API Gateway | 8080 | None |
| Employee Service | 8081 | peoplesphere_employees |
| Attendance Service | 8082 | peoplesphere_attendance |
| Payroll Service | 8083 | peoplesphere_payroll |
| Notification Service | 8084 | peoplesphere_notifications |
| MySQL | 3306 | Four schemas above |
| Nginx frontend | 80 | None |

## Important scope and safety notes

- This is a DevOps training application, not production HR/payroll software. Do not use real employee or salary data.
- Payroll calculations are deliberately basic demonstration logic, not legally compliant payroll processing.
- Keep MySQL private. Do not expose port 3306 to the public internet. Permit it only from the application security group.
- Do not expose service ports 8081–8084 or gateway port 8080 publicly when Nginx is the public entry point. Allow only required traffic from the gateway/frontend tier.
- Replace the sample DB password. Use environment files with restrictive permissions; do not commit secrets.
- `ddl-auto=update` is convenient for this lab. For production, use controlled schema migrations such as Flyway or Liquibase.

## Build each backend component

Use Java 21 and Maven on a build machine. From each directory below:

```bash
mvn clean package -DskipTests
```

Directories: `employee-service`, `attendance-service`, `payroll-service`, `notification-service`, `api-gateway`.
The executable artifact is created under each module's `target/` directory.

## Database setup

1. Install MySQL on your private database EC2.
2. Copy `database/create_databases.sql` to the DB host.
3. Replace `REPLACE_WITH_STRONG_PASSWORD` before execution.
4. Run the SQL as a MySQL administrator.
5. Configure each service's `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` environment variables. Use the DB private IP in `DB_URL`.

Example for Employee Service:

```bash
export DB_URL='jdbc:mysql://DB_PRIVATE_IP:3306/peoplesphere_employees?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC'
export DB_USERNAME='peoplesphere_app'
export DB_PASSWORD='YOUR_STRONG_PASSWORD'
java -jar employee-service-1.0.0.jar
```

Repeat with the matching schema for each service.

## Start the API Gateway

Set service URLs to private IPs or internal DNS names reachable from the gateway host:

```bash
export EMPLOYEE_SERVICE_URL='http://EMPLOYEE_PRIVATE_IP:8081'
export ATTENDANCE_SERVICE_URL='http://ATTENDANCE_PRIVATE_IP:8082'
export PAYROLL_SERVICE_URL='http://PAYROLL_PRIVATE_IP:8083'
export NOTIFICATION_SERVICE_URL='http://NOTIFICATION_PRIVATE_IP:8084'
java -jar api-gateway-1.0.0.jar
```

## Frontend

On a build machine with Node.js installed:

```bash
cd frontend
npm install
VITE_API_BASE= npm run build
```

The `dist/` directory contains static files. Copy its contents to `/var/www/peoplesphere` on the frontend/Nginx host and use `deployment/nginx/peoplesphere.conf` as the Nginx site configuration. The Nginx host should proxy `/api/` to the API Gateway. For a separate gateway host, change the `proxy_pass` destination to its private address.

For local development only, set `VITE_API_BASE=http://localhost:8080` and run `npm run dev`. For the Nginx deployment, build with an empty `VITE_API_BASE` so the browser calls the same-origin `/api/...` path.

## Health checks and API endpoints

- `GET /actuator/health` on each backend service and gateway.
- `GET /api/employees` — list employees.
- `POST /api/employees` — create employee. Example JSON:

```json
{"firstName":"Asha","lastName":"Rao","email":"asha@example.test","department":"Engineering","jobTitle":"Platform Engineer","annualSalary":900000,"status":"ACTIVE"}
```

- `GET /api/attendance`, `POST /api/attendance` — attendance records. Example: `{"employeeId":1,"attendanceDate":"2026-11-18","status":"PRESENT","notes":"Office"}`
- `GET /api/payroll`, `POST /api/payroll` — payroll records. Example: `{"employeeId":1,"payPeriod":"2026-11","grossPay":75000,"deductions":8000}`. `netPay` is calculated as grossPay minus deductions.
- `GET /api/notifications`, `POST /api/notifications` — notification records. Example: `{"employeeId":1,"channel":"EMAIL","subject":"Welcome","message":"Welcome to PeopleSphere"}`

## Recommended manual deployment sequence

1. Draw the architecture and decide subnet placement/security-group sources.
2. Create VPC, subnets, route tables and internet/NAT paths as required.
3. Create security groups with least-privilege rules.
4. Create private MySQL host, databases, user and grants.
5. Create application hosts and install Java 21, Maven (build host only), Node.js (frontend build host only), and Nginx (frontend host).
6. Build and transfer JAR artifacts; configure environment files; run each service manually first.
7. Validate service-to-DB and gateway-to-service connectivity with `curl`, `nc`, logs and Actuator.
8. Configure systemd only after each manual `java -jar` run works.
9. Build frontend, deploy static assets and configure Nginx.
10. Access the public frontend using its DNS name or public IP; verify employee creation and listing.
11. Practice failure scenarios and recovery before moving to Terraform and Jenkins.

See `docs/MANUAL_DEPLOYMENT_PLAN.md` for a suggested staged exercise.
