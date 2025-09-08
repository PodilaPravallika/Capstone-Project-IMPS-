# Insurance JSP+Servlet+JDBC (MySQL + CRUD)

## Setup
1. Install MySQL and create a database:
   ```sql
   CREATE DATABASE insurance_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
2. Update DB credentials in `src/main/java/com/example/insurance/DbUtil.java` (USER and PASS).

3. Build & run with Jetty (development):
   ```bash
   mvn clean package
   mvn jetty:run
   ```
   Visit: http://localhost:8080/login (demo credential: admin / admin123)

## Features
- MySQL-backed CRUD for Policies, Claims and Payments
- Servlets + JSP + basic POJOs
- Premium calculation in `PremiumCalc`
- DB initialization listener seeds an `admin` user
