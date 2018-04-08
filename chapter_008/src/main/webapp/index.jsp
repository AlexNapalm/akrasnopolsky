<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %><%--
  Created by IntelliJ IDEA.
  User: Napalm
  Date: 06.04.2018
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/create" method="post">
    Login : <input type="text" name="login"><br/>
    Email : <input type="text" name="email"><br/>
    <input type="submit" value="create">
</form>
<br/>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>email</th>
    </tr>
    <% for (User user : UserStore.INSTANCE.getAllUsers()) {%>
    <tr>
        <form action="<%=request.getContextPath()%>/edit" method="post">
            <td>
                <input type="text" name="login" value=<%=user.getLogin()%>>
            </td>
            <td>
                <input type="text" name="email" value=<%=user.getEmail()%>>
                <input type="submit" value="edit">
            </td>
            <td><button formaction='<%=request.getContextPath()%>/delete'> delete </button></td>
        </form>
    </tr>
    <% } %>
</table>

</body>
</html>
