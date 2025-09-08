package com.example.insurance;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/PremiumServlet")
public class PremiumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int age = Integer.parseInt(request.getParameter("age"));
            double coverage = Double.parseDouble(request.getParameter("coverage"));
            String policyType = request.getParameter("policyType");

            // 🔹 Validation checks
            if (age <= 0) {
                request.setAttribute("premium", "Error: Invalid age entered");
                RequestDispatcher rd = request.getRequestDispatcher("premium_calculation.jsp");
                rd.forward(request, response);
                return;
            }

            if (coverage <= 0) {
                request.setAttribute("premium", "Error: Invalid coverage amount");
                RequestDispatcher rd = request.getRequestDispatcher("premium_calculation.jsp");
                rd.forward(request, response);
                return;
            }

            // 🔹 Use your existing PremiumCalc utility
            double premium = PremiumCalc.calculate(policyType, age, coverage);

            // 🔹 Send result back to same JSP
            request.setAttribute("premium", "Calculated Premium: ₹ " + premium);
            RequestDispatcher rd = request.getRequestDispatcher("premium_calculation.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("premium", "Error: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("premium_calculation.jsp");
            rd.forward(request, response);
        }
    }
}
