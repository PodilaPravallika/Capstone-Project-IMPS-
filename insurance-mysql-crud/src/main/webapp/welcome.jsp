<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome - Insurance App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #6dd5fa, #2980b9, #ffffff);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }
        .welcome-container {
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.2);
            width: 400px;
        }
        h1 {
            color: #2c3e50;
            margin-bottom: 15px;
        }
        p {
            font-size: 16px;
            color: #555;
        }
        button {
            margin-top: 20px;
            padding: 12px 20px;
            background: #2980b9;
            color: #fff;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background: #1f6391;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1>Welcome to Insurance App</h1>
        <p>Manage your insurance policies, claims, and more with ease.</p>
        <button onclick="window.location.href='login.jsp'">Go to Login</button>
    </div>
</body>
</html>
