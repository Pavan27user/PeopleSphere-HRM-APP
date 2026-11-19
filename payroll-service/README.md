# Payroll Service

Port: `8083`  
Database: `peoplesphere_payroll`  
Purpose: Payroll management.

Build: `mvn clean package -DskipTests`  
Run: `java -jar target/payroll-service-1.0.0.jar`

Required environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`. Configure secrets outside source control. Health: `http://localhost:8083/actuator/health`.
