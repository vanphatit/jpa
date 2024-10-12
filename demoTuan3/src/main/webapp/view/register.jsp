<%--
  Created by IntelliJ IDEA.
  User: vanph
  Date: 9/14/2024
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f3f3;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .main {
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            padding: 20px;
            width: 300px;
        }

        .main h2 {
            color: #4caf50;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
            width: 100%;
            margin-bottom: 15px;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button[type="submit"] {
            padding: 15px;
            border-radius: 10px;
            border: none;
            background-color: #4caf50;
            color: white;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
    </style>
</head>

<body>
<div class="main">
    <h2>Registration Form</h2>
    <c:if test="${error !=null}">
        <h3 class="alert alertdanger">${error}</h3>
    </c:if>
    <form action="/register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required />

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required />

        <label for="repassword">Re-type Password:</label>
        <input type="password" id="repassword" name="repassword" required />

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" maxlength="10"
               pattern="[0-9]{10}"
               title="Please enter a valid phone number (10 digits)" required />

        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="admin">
                Admin
            </option>
            <option value="User">
                User
            </option>
        </select>

        <button type="submit">
            Submit
        </button>
    </form>
</div>
</body>

</html>

