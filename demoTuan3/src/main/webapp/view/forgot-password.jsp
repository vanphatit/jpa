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
  <title>Reset password Form</title>
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
  <h2>Reset password Form</h2>
  <c:if test="${error !=null}">
    <h3 class="alert alertdanger">${error}</h3>
  </c:if>
  <form action="/forgot-password" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required />

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required />
    <label for="password">New password:</label>
    <input type="password" id="password" name="password"
           pattern="^(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])\S{8,}$"
           title="Password must contain at least one number,
                           one alphabet, one symbol, and be at
                           least 8 characters long" required />

    <label for="repassword">Re-type Password:</label>
    <input type="password" id="repassword" name="repassword" required />
    <button type="submit">
      Submit
    </button>
  </form>
</div>
</body>

</html>

