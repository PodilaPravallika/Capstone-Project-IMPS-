<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>New/Edit Policy</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f4f7fa;
      margin: 0;
      padding: 0;
    }

    .container {
      max-width: 500px;
      margin: 50px auto;
      background: #fff;
      padding: 25px;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    h2 {
      text-align: center;
      color: #2c3e50;
      margin-bottom: 20px;
    }

    a.back-link {
      display: inline-block;
      margin-bottom: 15px;
      text-decoration: none;
      color: #3498db;
      font-weight: bold;
    }

    form label {
      display: block;
      margin-top: 10px;
      font-weight: bold;
      color: #555;
    }

    form input, form select {
      width: 100%;
      padding: 10px;
      margin-top: 5px;
      border: 1px solid #ccc;
      border-radius: 6px;
      font-size: 14px;
    }

    button {
      margin-top: 20px;
      width: 100%;
      padding: 12px;
      border: none;
      background: #3498db;
      color: #fff;
      font-size: 16px;
      border-radius: 6px;
      cursor: pointer;
      transition: 0.3s;
    }

    button:hover {
      background: #2980b9;
    }
  </style>
</head>
<body>
  <div class="container">
    <a href="${pageContext.request.contextPath}/policies" class="back-link">← Back to Policies</a>
    <h2>
      <c:choose>
        <c:when test="${not empty policy}">Edit Policy</c:when>
        <c:otherwise>New Policy</c:otherwise>
      </c:choose>
    </h2>
    <form method="post" action="${pageContext.request.contextPath}/policies">
      <input type="hidden" name="id" value="${policy.id}">

      <label>Type:</label>
      <select name="type">
        <option ${policy.type=='LIFE'?'selected':''}>LIFE</option>
        <option ${policy.type=='HEALTH'?'selected':''}>HEALTH</option>
        <option ${policy.type=='VEHICLE'?'selected':''}>VEHICLE</option>
      </select>

      <label>Holder Name:</label>
      <input name="holderName" required value="${policy.holderName}">

      <label>Age:</label>
      <input type="number" name="age" min="1" max="120" required value="${policy.age}">

      <label>Coverage Amount:</label>
      <input type="number" name="coverageAmount" step="0.01" required value="${policy.coverageAmount}">

      <label>Start Date:</label>
      <input type="date" name="startDate" required value="${policy.startDate}">



      <button type="submit">Save Policy</button>
    </form>
  </div>
</body>
</html>
