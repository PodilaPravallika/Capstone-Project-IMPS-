package com.example.insurance;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/claims/*")
public class ClaimServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        try (Connection c = DbUtil.getConnection()) {
            if (action == null || "/".equals(action)) {
                List<Claim> list = new ArrayList<>();
                try (PreparedStatement ps = c.prepareStatement("SELECT c.*, p.holderName FROM claims c LEFT JOIN policies p ON c.policy_id=p.id");
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Claim cl = new Claim();
                        cl.setId(rs.getLong("id"));
                        cl.setPolicyId(rs.getLong("policy_id"));
                        cl.setDescription(rs.getString("description"));
                        cl.setAmount(rs.getDouble("amount"));
                        cl.setStatus(rs.getString("status"));
                        cl.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                        cl.setPolicyHolder(rs.getString("holderName"));
                        list.add(cl);
                    }
                }
                // policies for dropdown
                List<Policy> policies = new ArrayList<>();
                try (PreparedStatement ps2 = c.prepareStatement("SELECT id, holderName, type FROM policies");
                     ResultSet rs2 = ps2.executeQuery()) {
                    while (rs2.next()) {
                        Policy p = new Policy();
                        p.setId(rs2.getLong("id")); p.setHolderName(rs2.getString("holderName")); p.setType(rs2.getString("type"));
                        policies.add(p);
                    }
                }
                req.setAttribute("claims", list); req.setAttribute("policies", policies);
                req.getRequestDispatcher("/claims.jsp").forward(req, resp);
                return;
            } else if (action.startsWith("/delete")) {
                long id = Long.parseLong(req.getParameter("id"));
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM claims WHERE id=?")) {
                    ps.setLong(1, id); ps.executeUpdate();
                }
                resp.sendRedirect(req.getContextPath() + "/claims");
                return;
            }
        } catch (Exception e) { throw new ServletException(e); }
        resp.sendRedirect(req.getContextPath() + "/claims");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long policyId = Long.parseLong(req.getParameter("policyId"));
        String desc = req.getParameter("description"); double amount = Double.parseDouble(req.getParameter("amount"));
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO claims(policy_id,description,amount,status,createdAt) VALUES (?,?,?,?,CURRENT_TIMESTAMP)")) {
            ps.setLong(1, policyId); ps.setString(2, desc); ps.setDouble(3, amount); ps.setString(4, "SUBMITTED"); ps.executeUpdate();
        } catch (Exception e) { throw new ServletException(e); }
        resp.sendRedirect(req.getContextPath() + "/claims");
    }
}
