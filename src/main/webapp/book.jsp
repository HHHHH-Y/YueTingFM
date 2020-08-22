<%@ page import="huyue.service.BookService" %>
<%@ page import="huyue.model.Book" %>
<%@ page import="huyue.model.User" %>
<%@ page import="huyue.model.Section" %><%--
  Created by IntelliJ IDEA.
  User: Huyue
  Date: 2020/8/22
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>

<%-- 展示书籍的详情页面(包括书籍名称, 以及章节名称) --%>
<%-- 将提交后的数据直接返回到界面上 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private BookService bookService;

    public void jspInit() {
        bookService = new BookService();
    }
%>
<%
    // 根据 url 中的 bid 获取书籍信息以及章节信息
    int bid = Integer.parseInt(request.getParameter("bid"));
    Book book = bookService.get(bid);
    if (book == null) {
        response.sendError(404, "没有这本书");
        return;
    }
    // 获取当前登录用户信息
    User user = (User) session.getAttribute("user");

%>
<html>
<head>
    <meta charset="utf-8">
    <title>悦听FM | 书籍详情</title>
</head>
<body>
<h1><%=book.title %>
</h1>
<p>上传者: <%= book.user.username %>
</p>

<!--  如果当前用户就是书籍的上传用户, 允许用户进行添加新章节 -->
<% if (user != null && book.user.equals(user)) { %>
<h2>添加新章节</h2>
<form method="post" action="post-section">
    <input type="hidden" name="bid" value="<%=book.bid %>">
    <div>
        <label for="name">
            章节名称:
            <input type="text" id="name" name="name">
        </label>
    </div>
    <div>
        <button type="submit">提交</button>
    </div>
</form>
<% } %>


<h2>章节列表</h2>
<ol>
    <% for (Section section : book.sections) {%>
    <%-- 说明有音频信息 --%>
    <% if (section.uuid != null) {%>
    <li>
        <%=section.name %>
        <a>播放</a>
    </li>
    <% } else if (user != null && book.user.equals(user)) {%>   <%-- 判断用户是否是这本书的上传者 --%>
    <li>
        <%=section.name %>
        <a href="record.jsp?sid=<%=section.sid%>">录制声音</a>
    </li>
    <% } %>
    <% } %>
</ol>
</body>
</html>
