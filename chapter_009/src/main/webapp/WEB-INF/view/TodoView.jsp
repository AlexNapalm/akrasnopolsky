<%--
  Created by IntelliJ IDEA.
  User: Napalm
  Date: 06.05.2018
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.job4j.models.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>TODO list</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <div>
        Add new task <b/>
        <form action="${pageContext.servletContext.contextPath}/add" method="post">
            <input type="text" name="description">
            <input type="submit" value="add new task">
        </form>
    </div>
    <div>
        Tasks
        <table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
            <tr>
                <th>id</th>
                <th>task</th>
                <th>done</th>
                <th>created</th>
            </tr>
            <c:forEach items="${items}" var="item">
            <tr>
                <td>
                    <input type="text" name="id" value=<c:out value="${item.id}"></c:out>>
                </td>
                <td>
                    <input type="text" name="description" value=<c:out value="${item.desc}"></c:out>>
                </td>
                <td>
                    <input type="text" name="done" value=<c:out value="${item.done}"></c:out>>
                </td>
                <td>
                    <input type="text" name="created" value=<c:out value="${item.created}"></c:out>>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
