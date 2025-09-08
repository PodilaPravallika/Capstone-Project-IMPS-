<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Premium Calculation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f1f1;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0,0,0,0.15);
            width: 420px;
            text-align: center;
        }
        input, select {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
        }
        button {
            background: #2980b9;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        button:hover {
            background: #1f6391;
        }
        .result {
            margin-top: 20px;
            font-size: 18px;
            font-weight: bold;
        }
        .success {
            color: green;
        }
        .error {
            color: red;
        }
        a {
            display: inline-block;
            margin-top: 15px;
            text-decoration: none;
            color: #2980b9;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Premium Calculator</h2>
        <form action="PremiumServlet" method="post">
            <input type="number" name="age" placeholder="Enter Age" required><br>
            <input type="number" name="coverage" placeholder="Coverage Amount" required><br>
            <select name="policyType" required>
                <option value="">-- Select Policy Type --</option>
                <option value="LIFE">Life Insurance</option>
                <option value="HEALTH">Health Insurance</option>
                <option value="VEHICLE">Vehicle Insurance</option>
            </select><br>
            <button type="submit">Calculate Premium</button>
        </form>

        <!-- Result Display -->
        <div class="result">
            <%
                Object premiumObj = request.getAttribute("premium");
                if (premiumObj != null) {
                    String premiumMsg = premiumObj.toString();
                    if (premiumMsg.startsWith("Error")) {
                        out.print("<div class='error'>" + premiumMsg + "</div>");
                    } else {
                        out.print("<div class='success'>" + premiumMsg + "</div>");
                    }
                }
            %>
        </div>

        <a href="dashboard.jsp">⬅ Back to Dashboard</a>
    </div>
</body>
</html>
