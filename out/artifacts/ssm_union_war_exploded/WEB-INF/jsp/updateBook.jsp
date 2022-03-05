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
<form action="${pageContext.request.contextPath}/book/updateBook" method="post">
    <div>
        <label >id</label>
        <input type="text" name="bookID">
    </div>
    <div>
        <label >书籍名称</label>
        <input type="text" name="bookName">
    </div>
    <div>
        <label >书籍数量</label>
        <input type="text" name="bookCounts">
    </div>
    <div>
        <label >书籍描述</label>
        <input type="text" name="detail">
    </div>

    <input type="submit" value="提交">

</form>
</body>
</html>
