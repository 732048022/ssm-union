<%--
  Created by IntelliJ IDEA.
  User: twt
  Date: 2022/1/12
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
</head>
<body>
<h1>书籍展示</h1>
<a href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
<a href="${pageContext.request.contextPath}/book/toUpdateBook">修改书籍</a>
<a href="${pageContext.request.contextPath}/book/toDeleteBook">删除书籍</a>
<a href="${pageContext.request.contextPath}/book/toQueryAllBooks">查询书籍</a>
</body>
</html>
