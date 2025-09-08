<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Claims Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        header {
            background: #007bff;
            color: white;
            padding: 15px 30px;
            font-size: 22px;
            font-weight: bold;
        }

        .container {
            width: 90%;
            max-width: 1100px;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        a:hover {
            color: #0056b3;
        }

        h2, h3 {
            margin-top: 0;
            color: #333;
        }

        form {
            margin-bottom: 30px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input, select, button {
            padding: 8px 12px;
            width: 100%;
            max-width: 400px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            font-weight: bold;
        }

        button:hover {
            background: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background: #f9f9f9;
        }

        .action-btn {
            color: red;
            font-weight: bold;
        }

        .action-btn:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <header>Insurance Management - Claims</header>
    <div class="container">
        <a href="${pageContext.request.contextPath}/dashboard">← Back to Dashboard</a>

        <h2>Submit a New Claim</h2>
        <form method="post" action="${pageContext.request.contextPath}/claims">
            <label for="policyId">Policy</label>
            <select name="policyId" id="policyId" required>
                <c:forEach var="p" items="${policies}">
                    <option value="${p.id}">${p.id} - ${p.holderName} - ${p.type}</option>
                </c:forEach>
            </select>

            <label for="description">Description</label>
            <input type="text" name="description" id="description" required>

            <label for="amount">Claim Amount</label>
            <input type="number" name="amount" id="amount" step="0.01" required>

            <button type="submit">Submit Claim</button>
        </form>

        <h3>Existing Claims</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Policy</th>
                <th>Holder</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Created</th>
                <th>Action</th>
            </tr>
            <c:forEach var="c" items="${claims}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.policyId}</td>
                    <td>${c.policyHolder}</td>
                    <td>${c.description}</td>
                    <td>${c.amount}</td>
                    <td>${c.status}</td>
                    <td>${c.createdAt}</td>
                    <td>
                        <a class="action-btn" href="${pageContext.request.contextPath}/claims/delete?id=${c.id}" 
                           onclick="return confirm('Delete this claim?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
