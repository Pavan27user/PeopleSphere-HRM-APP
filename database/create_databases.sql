CREATE DATABASE IF NOT EXISTS peoplesphere_employees CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS peoplesphere_attendance CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS peoplesphere_payroll CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS peoplesphere_notifications CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- Create a least-privilege application user. Replace the password before running.
CREATE USER IF NOT EXISTS 'peoplesphere_app'@'%' IDENTIFIED BY 'REPLACE_WITH_STRONG_PASSWORD';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, INDEX ON peoplesphere_employees.* TO 'peoplesphere_app'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, INDEX ON peoplesphere_attendance.* TO 'peoplesphere_app'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, INDEX ON peoplesphere_payroll.* TO 'peoplesphere_app'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, INDEX ON peoplesphere_notifications.* TO 'peoplesphere_app'@'%';
FLUSH PRIVILEGES;
