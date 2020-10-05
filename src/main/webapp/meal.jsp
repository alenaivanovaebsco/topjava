<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<html lang="ru">
<head>
    <title></title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<div class="form-style-2">
    <form method="POST" action="${pageContext.request.contextPath}/meals?commandName=addMeal">
        <input type="text" hidden="true" name="id" value="<c:out value="${meal.id}" />"/>
        <div class="form-style-2 input-field">
            <label>Date and Time</label>
            <input type="datetime-local" id="dateTime" name="dateTime" value="<c:out value="${meal.dateTime}" />">
        </div>
        <div class="form-style-2 input-field">
            <label>Calories</label>
            <input type="text" class="form-control" id="calories" name="calories"
                   value="<c:out value="${meal.calories}" />" required>
        </div>
        <div class="form-style-2 input-field">
            <label>Description</label>
            <input type="text" class="form-control" id="description" name="description"
                   value="<c:out value="${meal.description}" />" required>
        </div>
        <div class="form-style-2 input[type=submit]">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>
</body>
</html>
