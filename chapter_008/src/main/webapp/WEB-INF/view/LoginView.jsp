<%@ page import="ru.job4j.models.User" %>
<%@ page import="ru.job4j.servlets.DbController" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Napalm
  Date: 11.04.2018
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login : <input type="text" name="login"><br/>
    Password : <input type="password" name="password"><br/>
    <input type="submit" value="sign in">
    <br/><a href="${pageContext.servletContext.contextPath}/register">Register</a>
</form>
</body>
</html>
