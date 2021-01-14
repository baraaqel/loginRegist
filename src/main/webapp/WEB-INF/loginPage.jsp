<%--
  Created by IntelliJ IDEA.
  User: REZOO FAMILY
  Date: 1/14/2021
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
<h1>Login</h1>
<form method="post" action="/login">
<p><c:out value="${error}" /></p>
    <p>
        <label for="email">Email</label>
        <input type="text" id="email" name="email"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="submit" value="Login!"/>
</form>
</body>
</html>