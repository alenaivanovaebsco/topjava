<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<div id="message">
</div>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="${pageContext.request.contextPath}/meals?commandName=addMealView">Add Meal</a></p>
<table class="table table-hover">
    <thead>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="m">
        <tr class='${m.excess}'>
            <td>${m.dateTime}</td>
            <td>${m.description}</td>
            <td>${m.calories}</td>
            <td><a href="${pageContext.request.contextPath}/meals?commandName=edit&mealId=<c:out value="${m.id}"/>">Update</a></td>
            <td><a href="${pageContext.request.contextPath}/meals?commandName=delete&mealId=<c:out value="${m.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>