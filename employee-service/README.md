# Employee Service

Port: `8081`  
Database: `peoplesphere_employees`  
Purpose: Employee management.

Build: `mvn clean package -DskipTests`  
Run: `java -jar target/employee-service-1.0.0.jar`

Required environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`. Configure secrets outside source control. Health: `http://localhost:8081/actuator/health`.
