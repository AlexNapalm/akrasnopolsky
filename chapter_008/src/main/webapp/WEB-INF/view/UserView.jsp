<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Napalm
  Date: 06.04.2018
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Control panel</title>
</head>
<body>

<br/>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>password</th>
        <th>email</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <form action="${pageContext.servletContext.contextPath}/edit" method="post">
            <td>
                <input type="text" name="login" value=<c:out value="${user.login}"></c:out>>
            </td>
            <td>
                <input type="password" name="password" value=<c:out value="${user.password}"></c:out>>
            </td>
            <td>
                <input type="text" name="email" value=<c:out value="${user.email}"></c:out>>
                <input type="submit" value="edit">
            </td>
        </form>
    </tr>
    </c:forEach>
</table>

</body>
</html>
