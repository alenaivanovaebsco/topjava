<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<html lang="ru">
<head>
    <title>Add new meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add new meal:
    </div>
    <form method="POST" action="${pageContext.request.contextPath}/meals?commandName=addMeal">
        <div class="form-style-2 input-field">
            <label>Date and Time</label>
            <input type="datetime-local" class="form-control" id="dateTime" name="dateTime" required>
        </div>
        <div class="form-style-2 input-field">
            <label>Calories</label>
            <input type="text" class="form-control" id="calories" name="calories" required>
        </div>
        <div class="form-style-2 input-field">
            <label>Description</label>
            <select id="description" class="form-control" name="description">
                <option value="Завтрак"> Завтрак</option>
                <option value="Обед"> Обед</option>
                <option value="Ужин"> Ужин</option>
                <option value="Другое"> Другое</option>
            </select>
        </div>
        <div class="form-style-2 input[type=submit]">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>
</body>
</html>
