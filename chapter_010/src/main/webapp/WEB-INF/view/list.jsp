<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список объявлений</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script>
        function filterParams() {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i=0;i<vars.length;i++) {
                var pair = vars[i].split("=");
                if (pair[0] == 'today') {
                    document.getElementById("for_today").checked = true;
                }
                if (pair[0] == 'carbrand') {
                    var br = document.getElementById("carbrand");
                    br.options[pair[1]].selected = true;
                }
            }
        }
    </script>
</head>
<body onload="filterParams()">
<div class = "container">
    <div class="page-header"><h1>Список объявлений</h1></div>

    <a class="btn btn-default" href="${pageContext.request.contextPath}/logout">Выйти</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/create">Разместить объявление</a> <br/><br/>

    <div class="form-group row">
        <div class="col-lg-3">
            <form method="get" action="">
                <input id="for_today" name="today" type="checkbox"/>
                <label for="for_today">Обновления за сегодня</label>
                <br/>
                <label for="carbrand">Марка авто</label>
                <select id="carbrand" class="form-control" name="carbrand">
                    <option value=""></option>
                    <c:forEach items="${carbrands}" var="carbrand">
                        <option value="${carbrand.id}">${carbrand.brand}</option>
                    </c:forEach>
                </select>
                <br/>
                <input class="btn btn-default mb-2" type="submit" value="Применить"/>
            </form>
        </div>
    </div>

    <c:forEach items="${ads}" var="ad">
        <a class="btn btn-default" href="${pageContext.request.contextPath}/update/${ad.id}">Обновить</a>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/delete/${ad.id}" onclick="return confirm('Вы уверены?')">Удалить</a> <br/>

        <br/> <img src="${pageContext.request.contextPath}/uploads/${ad.id}.jpg" alt="no-image" class="img-responsive" style="max-height:200px"><br/>
        <c:if test="${ad.sold == true}">
            Продано! <br/>
        </c:if>
        последнее обновление: <fmt:formatDate value="${ad.created}" pattern="d MMMM yyyy, H:mm:ss"/> <br/>
        марка: ${ad.carbrand.name} <br/>
        модель: ${ad.model} <br/>
        год: ${ad.year} <br/>
        кузов: ${ad.cartype.type} <br/>
        цена: ${ad.price} руб. <br/>
        описание: ${ad.description} <br/>
        контакты для связи: ${ad.user.name} ${ad.user.phone} <br/>
        <hr/>
    </c:forEach>
</div>
</body>
</html>