# Attendance Service

Port: `8082`  
Database: `peoplesphere_attendance`  
Purpose: Attendance tracking.

Build: `mvn clean package -DskipTests`  
Run: `java -jar target/attendance-service-1.0.0.jar`

Required environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`. Configure secrets outside source control. Health: `http://localhost:8082/actuator/health`.
