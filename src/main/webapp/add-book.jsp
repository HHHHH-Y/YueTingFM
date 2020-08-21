<%@ page import="huyue.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Huyue
  Date: 2020/8/22
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 展示上传书籍页面, 要求用户必须是登录用户, 才可以看见这个页面
    // 从 session 中获取用户信息
    User user = (User) session.getAttribute("user");
    // 如果用户信息不存在, 则跳转至登录页面
    if(user == null) {
        response.sendRedirect("/login.html");
        return;
    }
%>
<html>
<head>
    <title>悦听FM | 上传书籍</title>
</head>
<body>
    <form method="post" action="/post-book">
        <div>
            <label for="title">
                书籍标题:
                <input type="text" id="title" name="title">
            </label>
        </div>
        <div>
            <button type="submit">上传</button>
        </div>
    </form>
</body>
</html>
