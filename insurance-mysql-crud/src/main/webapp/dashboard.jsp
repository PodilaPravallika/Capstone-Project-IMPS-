<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Insurance App</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(120deg, #6dd5fa, #2980b9);
            color: #2c3e50;
        }
        .navbar {
            background: #2c3e50;
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar h2 {
            margin: 0;
            font-size: 20px;
        }
        .dashboard-container {
            padding: 40px;
            display: flex;
            justify-content: center;
        }
        .card {
            background: white;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0,0,0,0.15);
            width: 400px;
            text-align: center;
        }
        .card h3 {
            margin-bottom: 25px;
            font-size: 22px;
            color: #34495e;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            margin: 12px 0;
        }
        a {
            display: block;
            text-decoration: none;
            background: #2980b9;
            color: white;
            padding: 12px;
            border-radius: 8px;
            font-size: 16px;
            transition: 0.3s;
        }
        a:hover {
            background: #1f6391;
        }
    </style>
</head>
<body>
    <!-- Top Navigation -->
    <div class="navbar">
        <h2>Insurance Dashboard</h2>
        <div>Welcome, <b>${sessionScope.user}</b> (<i>${sessionScope.role}</i>)</div>
    </div>

    <!-- Dashboard Content -->
    <div class="dashboard-container">
        <div class="card">
            <h3>Quick Links</h3>
            <ul>
                <li><a href="${pageContext.request.contextPath}/policies">Manage Policies</a></li>
                <li><a href="${pageContext.request.contextPath}/claims">Submit/View Claims</a></li>
                <li><a href="${pageContext.request.contextPath}/payments">Payments</a></li>
                
                <!-- 🔹 New Features Added -->
                <li><a href="${pageContext.request.contextPath}/premium_calculation.jsp">Calculate Premium</a></li>
                


                <li><a href="${pageContext.request.contextPath}/login?logout=true">Logout</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
