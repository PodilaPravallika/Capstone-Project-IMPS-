package com.example.insurance;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("true".equals(req.getParameter("logout"))) {
            HttpSession s = req.getSession(false); if (s != null) s.invalidate();
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT password FROM users WHERE username=?")) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getString(1).equals(password)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", username);
                    resp.sendRedirect(req.getContextPath() + "/dashboard");
                    return;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.setAttribute("error", "Invalid credentials");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
