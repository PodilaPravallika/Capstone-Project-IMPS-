<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Policies - Insurance App</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f4f6f9;
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
        .navbar a {
            color: #ecf0f1;
            text-decoration: none;
            font-weight: bold;
        }
        .container {
            padding: 30px;
        }
        h2 {
            margin-bottom: 20px;
            color: #34495e;
        }
        .btn {
            display: inline-block;
            padding: 10px 18px;
            background: #2980b9;
            color: white;
            border-radius: 6px;
            text-decoration: none;
            font-size: 14px;
            transition: 0.3s;
        }
        .btn:hover {
            background: #1f6391;
        }
        .btn-danger {
            background: #e74c3c;
        }
        .btn-danger:hover {
            background: #c0392b;
        }
        .table-container {
            margin-top: 20px;
            overflow-x: auto;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background: #2980b9;
            color: white;
        }
        tr:hover {
            background: #f1f1f1;
        }
        td a {
            margin: 0 4px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <div><a href="${pageContext.request.contextPath}/dashboard">← Back to Dashboard</a></div>
        <div>Insurance Policies</div>
    </div>

    <!-- Page Content -->
    <div class="container">
        <h2>Manage Policies</h2>
        <a class="btn" href="${pageContext.request.contextPath}/policies/new">+ New Policy</a>

        <div class="table-container">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Holder</th>
                    <th>Age</th>
                    <th>Coverage</th>
                    <th>Premium</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="p" items="${policies}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.type}</td>
                        <td>${p.holderName}</td>
                        <td>${p.age}</td>
                        <td>${p.coverageAmount}</td>
                        <td>${p.premium}</td>
                        <td>${p.startDate}</td>
                        <td>${p.endDate}</td>
                        <td>${p.status}</td>
                        <td>
                            <a class="btn" href="${pageContext.request.contextPath}/policies/edit?id=${p.id}">Edit</a>
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/policies/delete?id=${p.id}" onclick="return confirm('Delete this policy?')">Delete</a>
                            <a class="btn" href="${pageContext.request.contextPath}/policies/renew?id=${p.id}">Renew</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
