<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Add</title>
</head>

<body>




<form action="/account-edit" method="POST">


ID: <input type="text" name="Id" value="${Id}">
<br />
username: <input type="text" name="Username" value="${Username}">
<br />
password: <input type="password" name="Password" value="${Password}">
<br />
<input type="submit" value="Submit" />

</form>

</body>
</html>