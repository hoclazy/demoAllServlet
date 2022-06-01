<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thinkpad t440s
  Date: 5/30/2022
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${customers}" var="cs">
    <h1>${cs.id}, ${cs.name}, ${cs.age}</h1>
</c:forEach>
</body>
</html>
