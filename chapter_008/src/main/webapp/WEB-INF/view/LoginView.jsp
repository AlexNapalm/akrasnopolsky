<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %>
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
</form>
</body>
</html>
