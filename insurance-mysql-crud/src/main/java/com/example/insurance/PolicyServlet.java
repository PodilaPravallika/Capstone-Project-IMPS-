package com.example.insurance;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/policies/*")
public class PolicyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        try (Connection c = DbUtil.getConnection()) {
            if (action == null || "/".equals(action)) {
                // READ all
                List<Policy> policies = new ArrayList<>();
                try (PreparedStatement ps = c.prepareStatement("SELECT * FROM policies");
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Policy p = new Policy();
                        p.setId(rs.getLong("id"));
                        p.setType(rs.getString("type"));
                        p.setHolderName(rs.getString("holderName"));
                        p.setAge(rs.getInt("age"));
                        p.setCoverageAmount(rs.getDouble("coverageAmount"));
                        p.setPremium(rs.getDouble("premium"));
                        p.setStartDate(rs.getDate("startDate") != null ? rs.getDate("startDate").toLocalDate() : null);
                        p.setEndDate(rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null);
                        p.setStatus(rs.getString("status"));
                        policies.add(p);
                    }
                }
                req.setAttribute("policies", policies);
                req.getRequestDispatcher("/policies.jsp").forward(req, resp);
                return;
            } else if (action.startsWith("/new")) {
                req.getRequestDispatcher("/policy_form.jsp").forward(req, resp);
                return;
            } else if (action.startsWith("/edit")) {
                long id = Long.parseLong(req.getParameter("id"));
                Policy p = null;
                try (PreparedStatement ps = c.prepareStatement("SELECT * FROM policies WHERE id=?")) {
                    ps.setLong(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            p = new Policy();
                            p.setId(rs.getLong("id"));
                            p.setType(rs.getString("type"));
                            p.setHolderName(rs.getString("holderName"));
                            p.setAge(rs.getInt("age"));
                            p.setCoverageAmount(rs.getDouble("coverageAmount"));
                            p.setPremium(rs.getDouble("premium"));
                            p.setStartDate(rs.getDate("startDate") != null ? rs.getDate("startDate").toLocalDate() : null);
                            p.setEndDate(rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null);
                            p.setStatus(rs.getString("status"));
                        }
                    }
                }
                req.setAttribute("policy", p);
                req.getRequestDispatcher("/policy_form.jsp").forward(req, resp);
                return;
            } else if (action.startsWith("/delete")) {
                long id = Long.parseLong(req.getParameter("id"));
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM policies WHERE id=?")) {
                    ps.setLong(1, id);
                    ps.executeUpdate();
                }
                resp.sendRedirect(req.getContextPath() + "/policies");
                return;
            } else if (action.startsWith("/renew")) {
                long id = Long.parseLong(req.getParameter("id"));
                try (PreparedStatement ps = c.prepareStatement("UPDATE policies SET startDate=?, endDate=?, status=? WHERE id=?")) {
                    LocalDate now = LocalDate.now();
                    ps.setDate(1, Date.valueOf(now));
                    ps.setDate(2, Date.valueOf(now.plusYears(1)));
                    ps.setString(3, "ACTIVE");
                    ps.setLong(4, id);
                    ps.executeUpdate();
                }
                resp.sendRedirect(req.getContextPath() + "/policies");
                return;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/policies");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String type = req.getParameter("type");
        String holderName = req.getParameter("holderName");
        int age = Integer.parseInt(req.getParameter("age"));
        double coverage = Double.parseDouble(req.getParameter("coverageAmount"));
        String startDateStr = req.getParameter("startDate");
        LocalDate start = (startDateStr != null && !startDateStr.isEmpty())
                ? LocalDate.parse(startDateStr)
                : LocalDate.now();
        double premium = PremiumCalc.calculate(type, age, coverage);
        try (Connection c = DbUtil.getConnection()) {
            if (id == null || id.isEmpty()) {
                // CREATE
                try (PreparedStatement ps = c.prepareStatement(
                        "INSERT INTO policies(type,holderName,age,coverageAmount,premium,startDate,endDate,status) VALUES (?,?,?,?,?,?,?,?)")) {
                    ps.setString(1, type);
                    ps.setString(2, holderName);
                    ps.setInt(3, age);
                    ps.setDouble(4, coverage);
                    ps.setDouble(5, premium);
                    ps.setDate(6, Date.valueOf(start));
                    ps.setDate(7, Date.valueOf(start.plusYears(1)));
                    ps.setString(8, "ACTIVE");
                    ps.executeUpdate();
                }
            } else {
                // UPDATE
                try (PreparedStatement ps = c.prepareStatement(
                        "UPDATE policies SET type=?, holderName=?, age=?, coverageAmount=?, premium=?, startDate=?, endDate=?, status=? WHERE id=?")) {
                    ps.setString(1, type);
                    ps.setString(2, holderName);
                    ps.setInt(3, age);
                    ps.setDouble(4, coverage);
                    ps.setDouble(5, premium);
                    ps.setDate(6, Date.valueOf(start));
                    ps.setDate(7, Date.valueOf(start.plusYears(1)));
                    ps.setString(8, "ACTIVE");
                    ps.setLong(9, Long.parseLong(id));
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/policies");
    }
}
