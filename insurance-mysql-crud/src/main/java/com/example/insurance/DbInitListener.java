package com.example.insurance;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.Statement;

@WebListener
public class DbInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection c = DbUtil.getConnection(); Statement s = c.createStatement()) {

            // USERS table
            s.execute("""
                CREATE TABLE IF NOT EXISTS users (
                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                  username VARCHAR(100) UNIQUE NOT NULL,
                  password VARCHAR(100) NOT NULL,
                  role VARCHAR(50)
                )
            """);

            // POLICIES table
            s.execute("""
                CREATE TABLE IF NOT EXISTS policies (
                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                  type VARCHAR(50),
                  holderName VARCHAR(200),
                  age INT,
                  coverageAmount DOUBLE,
                  premium DOUBLE,
                  startDate DATE,
                  endDate DATE,
                  status VARCHAR(50)
                )
            """);

            // CLAIMS table
            s.execute("""
                CREATE TABLE IF NOT EXISTS claims (
                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                  policy_id BIGINT,
                  description VARCHAR(1000),
                  amount DOUBLE,
                  status VARCHAR(50),
                  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                  FOREIGN KEY (policy_id) REFERENCES policies(id) ON DELETE CASCADE
                )
            """);

            // PAYMENTS table
            s.execute("""
                CREATE TABLE IF NOT EXISTS payments (
                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                  policy_id BIGINT,
                  amount DOUBLE,
                  mode VARCHAR(20) NOT NULL,
                  status VARCHAR(50),
                  reference VARCHAR(200),
                  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                  FOREIGN KEY (policy_id) REFERENCES policies(id) ON DELETE CASCADE
                )
            """);

            // Insert default admin user if not exists
            s.execute("""
                INSERT INTO users(username,password,role)
                SELECT 'admin','admin123','ADMIN'
                FROM DUAL
                WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='admin')
            """);

            System.out.println("✅ Database initialized successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
