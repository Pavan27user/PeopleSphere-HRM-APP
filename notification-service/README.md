# Notification Service

Port: `8084`  
Database: `peoplesphere_notifications`  
Purpose: Notification history.

Build: `mvn clean package -DskipTests`  
Run: `java -jar target/notification-service-1.0.0.jar`

Required environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`. Configure secrets outside source control. Health: `http://localhost:8084/actuator/health`.
