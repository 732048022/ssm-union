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
新增书籍
<form action="${pageContext.request.contextPath}/book/queryBook" method="post">
    <input type="submit" value="提交">    ${msg}
</form>
</body>
</html>
