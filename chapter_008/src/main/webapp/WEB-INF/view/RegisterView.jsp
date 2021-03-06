<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new user</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script>
        function validateForm() {
            var error_message = document.getElementById("error");
            var email_field = document.getElementById("email").value;
            if(document.userForm.login.value == "" || document.userForm.password.value == "") {
                error_message.className = "alert alert-danger";
                error_message.innerHTML = "<strong>Warning!</strong> Both fields, login and password should be filled";
                return;
            }
            if(email_field != "") {
                if(email_field.indexOf("@") == -1 || email_field.indexOf(".") == -1) {
                    error_message.className = "alert alert-danger";
                    error_message.innerHTML = "<strong>Warning!</strong> Please enter correct e-mail";
                    return;
                }
            }
            document.userForm.submit();
        }
        function updateCities() {
            var country = document.userForm.country.value;
            var city_select = document.userForm.city;
            $.ajax('./cities_by_country?country=' + country, {
                method: 'get',
                complete: function (data) {
                    var cities = JSON.parse(data.responseText);
                    city_select.innerText = "";
                    console.log(cities);
                    for(var i = 0; i != cities.length; ++i) {
                        city_select.options[i] = new Option(cities[i].name, cities[i].name);
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class = "container">
    <div class="page-header"><h1>Create new user</h1></div>
    <div id="error">
        <c:if test="${error != null}">
            <div class="alert alert-danger"><strong>Warning!</strong> ${error}</div>
        </c:if>
    </div>
    <form name="userForm" onsubmit="validateForm(); return(false);" action="${pageContext.servletContext.contextPath}/register" method=post>
        <div class="row">
            <div class="col-xs-4">
                <label for="login">Login:</label>
                <input id="login" class="form-control" name="login" type="text">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="pass">Password:</label>
                <input id="pass" class="form-control" name="password" type="password">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="email">E-mail:</label>
                <input id="email" class="form-control" name="email" type="text">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="role">Role:</label>
                <select id="role" class="form-control" name="role">
                    <c:forEach items="${roles}" var="each">
                        <option>${each.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="country">Country:</label>
                <select id="country" class="form-control" name="country" onchange="updateCities()">
                    <option></option>
                    <c:forEach items="${countries}" var="each_country">
                        <option>${each_country.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="city">City:</label>
                <select id="city" class="form-control" name="city"></select>
            </div>
        </div>
        <br/>
        <input class="btn btn-default" type="submit" value="Submit">
    </form>
</div>
</body>
</html>
