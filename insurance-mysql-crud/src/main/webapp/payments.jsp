<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Payments</title>
  <style>
    body { font-family: Arial, sans-serif; background: #f4f7f9; margin: 0; padding: 0; }
    .container { width: 90%; max-width: 1000px; margin: 30px auto; background: #fff;
                 padding: 25px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
    h2, h3 { color: #333; border-bottom: 2px solid #007BFF; padding-bottom: 5px; }
    a.dashboard-link { display: inline-block; margin-bottom: 15px; text-decoration: none;
                       color: #007BFF; font-weight: bold; }
    form { margin-bottom: 25px; padding: 15px; background: #f9f9f9; border: 1px solid #ddd; border-radius: 6px; }
    form label { display: inline-block; width: 120px; font-weight: bold; margin-top: 10px; }
    form select, form input { padding: 8px; margin: 6px 0; width: calc(100% - 140px); max-width: 350px;
                              border: 1px solid #ccc; border-radius: 4px; }
    button { background: #007BFF; color: white; padding: 10px 18px; border: none; border-radius: 5px;
             cursor: pointer; margin-top: 10px; font-size: 14px; }
    button:hover { background: #0056b3; }
    table { width: 100%; border-collapse: collapse; margin-top: 15px; }
    table th, table td { padding: 10px; border: 1px solid #ddd; text-align: center; }
    table th { background: #007BFF; color: white; }
    table tr:nth-child(even) { background: #f9f9f9; }
    table tr:hover { background: #f1f1f1; }
    .delete-link { color: red; font-weight: bold; text-decoration: none; }
    .delete-link:hover { text-decoration: underline; }
    .msg { font-weight: bold; margin-bottom: 10px; }
    .error { color: red; }
    .success { color: green; }
  </style>
</head>
<body>
  <div class="container">
    <a class="dashboard-link" href="${pageContext.request.contextPath}/dashboard">← Back to Dashboard</a>
    
    <!-- Messages -->
    <c:if test="${not empty error}">
      <div class="msg error">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
      <div class="msg success">${success}</div>
    </c:if>

    <h2>Simulate Payment</h2>
    <form method="post" action="${pageContext.request.contextPath}/payments">
      <label>Policy:</label>
      <select name="policyId" required>
        <c:forEach var="p" items="${policies}">
          <option value="${p.id}">${p.id} - ${p.holderName} - ${p.type}</option>
        </c:forEach>
      </select><br>

      <label>Amount:</label>
      <input type="number" name="amount" step="0.01" required><br>

      <label>Mode:</label>
      <select name="mode" required>
        <option value="CARD">Card</option>
        <option value="UPI">UPI</option>
        <option value="NETBANKING">Net Banking</option>
      </select><br>

      <label>Success:</label>
      <select name="success">
        <option value="true">true</option>
        <option value="false">false</option>
      </select><br>

      <button type="submit">💳 Pay</button>
    </form>

    <h3>Payment History</h3>
    <table>
      <tr>
        <th>ID</th>
        <th>Policy</th>
        <th>Holder</th>
        <th>Amount</th>
        <th>Mode</th>
        <th>Status</th>
        <th>Reference</th>
        <th>Created</th>
        <th>Action</th>
      </tr>
      <c:if test="${empty payments}">
        <tr><td colspan="9">No payments found.</td></tr>
      </c:if>
      <c:forEach var="p" items="${payments}">
        <tr>
          <td>${p.id}</td>
          <td>${p.policyId}</td>
          <td>${p.policyHolder}</td>
          <td>${p.amount}</td>
          <td>${p.mode}</td>
          <td>${p.status}</td>
          <td>${p.reference}</td>
          <td>${p.createdAt}</td>
          <td><a class="delete-link" href="${pageContext.request.contextPath}/payments/delete?id=${p.id}" onclick="return confirm('Delete payment?')">Delete</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>
