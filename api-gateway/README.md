# API Gateway
Port 8080. Routes `/api/employees/**`, `/api/attendance/**`, `/api/payroll/**`, and `/api/notifications/**` to the corresponding services. Set `*_SERVICE_URL` environment variables to private service URLs before starting in AWS. Health: `/actuator/health`.
