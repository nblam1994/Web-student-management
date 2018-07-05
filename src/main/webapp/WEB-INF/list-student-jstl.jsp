<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>



<c:forEach items="${studentList}" var="item">
    ID: ${item.studentId}
    <br />
    name: ${item.studentName}
    <br />
    class: ${item.className}
    <br />
    score: ${item.score}
    <br />
    <a href="/account-edit/${item.studentId}">Change password</a>
    <br />
</c:forEach>


