package com.example.insurance;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import java.util.ArrayList;

@WebServlet("/payments/*")
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // "/delete" or null

        // Delete payment
        if (path != null && path.equals("/delete")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                try (Connection c = DbUtil.getConnection();
                     PreparedStatement ps = c.prepareStatement("DELETE FROM payments WHERE id=?")) {
                    ps.setLong(1, Long.parseLong(idStr));
                    ps.executeUpdate();
                    req.setAttribute("success", "Payment deleted successfully.");
                } catch (Exception e) {
                    req.setAttribute("error", "Failed to delete payment.");
                }
            }
            resp.sendRedirect(req.getContextPath() + "/payments");
            return;
        }

        // Show payments list
        try (var c = DbUtil.getConnection();
             var ps = c.prepareStatement(
                "SELECT pm.*, p.holderName FROM payments pm LEFT JOIN policies p ON pm.policy_id=p.id ORDER BY pm.createdAt DESC");
             var rs = ps.executeQuery()) {

            var list = new ArrayList<Payment>();
            while (rs.next()) {
                Payment p = new Payment();
                p.setId(rs.getLong("id"));
                p.setPolicyId(rs.getLong("policy_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setMode(rs.getString("mode"));
                p.setStatus(rs.getString("status"));
                p.setReference(rs.getString("reference"));
                p.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                p.setPolicyHolder(rs.getString("holderName"));
                list.add(p);
            }

            // policies for form
            var policies = new ArrayList<Policy>();
            try (var ps2 = c.prepareStatement("SELECT id, holderName, type FROM policies");
                 var rs2 = ps2.executeQuery()) {
                while (rs2.next()) {
                    Policy p = new Policy();
                    p.setId(rs2.getLong("id"));
                    p.setHolderName(rs2.getString("holderName"));
                    p.setType(rs2.getString("type"));
                    policies.add(p);
                }
            }

            req.setAttribute("payments", list);
            req.setAttribute("policies", policies);
            req.getRequestDispatcher("/payments.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long policyId = Long.parseLong(req.getParameter("policyId"));
            double amount = Double.parseDouble(req.getParameter("amount"));
            String mode = req.getParameter("mode");
            boolean success = Boolean.parseBoolean(req.getParameter("success"));

            if (amount <= 0) {
                req.setAttribute("error", "Payment amount must be greater than zero.");
                doGet(req, resp);
                return;
            }

            String status = success ? "SUCCESS" : "FAILED";
            String ref = "PMT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            try (Connection c = DbUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO payments(policy_id,amount,mode,status,reference,createdAt) VALUES (?,?,?,?,?,CURRENT_TIMESTAMP)")) {
                ps.setLong(1, policyId);
                ps.setDouble(2, amount);
                ps.setString(3, mode);
                ps.setString(4, status);
                ps.setString(5, ref);
                ps.executeUpdate();
            }

            req.setAttribute("success", "Payment processed successfully. Reference: " + ref);
            resp.sendRedirect(req.getContextPath() + "/payments");

        } catch (Exception e) {
            req.setAttribute("error", "Error processing payment: " + e.getMessage());
            doGet(req, resp);
        }
    }
}
