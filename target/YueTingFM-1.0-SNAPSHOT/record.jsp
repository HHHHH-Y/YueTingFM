<%--
  Created by IntelliJ IDEA.
  User: Huyue
  Date: 2020/8/22
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>悦听FM | 录制音频</title>
    <link rel="stylesheet" href="test.css">
</head>
<body>
    <div>
        <div id="startButton" class="button">开始采集</div>
        <div id="recordButton" class="button">开始录制</div>
        <div id="stopButton" class="button">停止录制</div>
        <div id="submitButton" class="button">上传</div>
        <h2>试听</h2>
        <audio id="preview" controls></audio>
    </div>

    <div>
        <pre id="log"></pre>
    </div>

    <script charset="utf-8" src="/js/record.js"></script>
</body>
</html>
