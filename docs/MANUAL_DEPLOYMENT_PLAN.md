# Manual deployment plan

## Phase 1 — Architecture and AWS foundation

Decide whether to use separate EC2 instances or consolidate application services. For the learning exercise, separate logical tiers are recommended: public frontend/reverse proxy, private application tier, private database tier. Draw traffic flows before creating security groups.

## Phase 2 — Database first

Install MySQL, create the four schemas and restricted user, configure bind-address, and permit TCP 3306 only from the application security group. Verify from an application host using `nc -zv DB_PRIVATE_IP 3306` and the MySQL client.

## Phase 3 — One service at a time

Start with Employee Service. Build the JAR, transfer it, configure environment variables, run `java -jar`, inspect logs, test `/actuator/health`, then test `POST /api/employees` and `GET /api/employees`. Repeat for Attendance, Payroll and Notification.

## Phase 4 — Gateway

Configure gateway service URLs using private addresses. Start gateway and test each route from the gateway host with curl.

## Phase 5 — Frontend

Build React with `VITE_API_BASE=/api`, deploy `dist/` to Nginx, configure reverse proxy to gateway, and open the site from your browser. Do not expose backend ports publicly.

## Phase 6 — Operational readiness

Create systemd units, log rotation, health checks, backups and a rollback procedure. Test: invalid DB password, blocked 3306, stopped MySQL, stopped microservice, wrong gateway URL, full disk and failed frontend build.

## Phase 7 — Automation

Only after manual deployment is repeatable: codify infrastructure with Terraform, then create Jenkins pipelines for build, artifact storage, deployment, smoke tests and rollback.
