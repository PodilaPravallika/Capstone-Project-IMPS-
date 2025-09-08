<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Insurance App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #2980b9, #6dd5fa, #ffffff);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.2);
            width: 350px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #2c3e50;
        }
        input {
            width: 90%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 14px;
        }
        button {
            width: 95%;
            padding: 12px;
            background: #2980b9;
            border: none;
            color: white;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s;
        }
        button:hover {
            background: #1f6391;
        }
        .error {
            color: red;
            margin-top: 10px;
        }
        .demo-info {
            margin-top: 15px;
            font-size: 13px;
            color: #555;
            background: #f1f1f1;
            padding: 8px;
            border-radius: 6px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Insurance App Login</h2>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <input name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <div class="demo-info">Demo Login → <b>admin / admin123</b></div>
    </div>
</body>
</html>
