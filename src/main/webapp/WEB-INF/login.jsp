<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "com.lam.StudentManagement.student.*" %>
<%@ page import= "java.util.List,java.util.ArrayList,java.util.Iterator,java.lang.Iterable"%>

<html>
<head>
    <title>Add</title>
</head>

        <body>
        <h1>Login</h1>
        <form action="/login" method="POST">
            ID: <input type="text" name="id"> <br/>
            username: <input type="text" name="username"> <br/>
            password: <input type="text" name="password"> <br/>
            remember me: <input type="checkbox" name="checked"> <br/>
            <input type="submit" value="Submit">
        </form>


</body>
</html>


