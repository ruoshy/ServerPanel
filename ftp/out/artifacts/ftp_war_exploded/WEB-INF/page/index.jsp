<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2019-04-01
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>

<html>
<head>
    <title>$Title$</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<ul id="list">
</ul>
<script>
    var list = document.getElementById("list");
    $.ajax({
        type: "get",
        async: true,
        url: "./glist",
        data: {dp: "/"},
        scriptCharset: 'ISO-8859-1',
        success: function (data) {
            var json = JSON.parse(data);
            var index, li;
            for (index in json.directory) {
                li = document.createElement("li");
                li.textContent = json.directory[index];
                list.appendChild(li);
            }
            for (index in json.file) {
                li = document.createElement("li");
                li.textContent = json.file[index];
                list.appendChild(li);
            }
        }
    });
</script>
</body>
</html>
