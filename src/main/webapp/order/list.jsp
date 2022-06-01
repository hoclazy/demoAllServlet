<%--
  Created by IntelliJ IDEA.
  User: Thinkpad t440s
  Date: 5/30/2022
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<form action="/order/create.jsp">--%>
<%--    <button>Thêm</button>--%>
<%--</form>--%>
<a href="/orders?act=create">tao</a>
<c:forEach items="${orders}" var="od">
    <h1>${od.id},${od.time},${od.total},${od.customerd.id},${od.customerd.name}, </h1>
</c:forEach>
</body>
</html>



