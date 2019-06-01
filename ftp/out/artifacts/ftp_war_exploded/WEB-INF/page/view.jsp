<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2019-04-01
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ftp</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <style>
        #list img {
            margin-right: 10px;
        }

        #list ul {
            margin: 5px 5px 5px;
        }

        #list li {
            overflow: hidden;
            white-space: nowrap;
            cursor: pointer;
            min-height: 60px;
        }
    </style>
</head>
<body id="snd">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="#" onclick="sub()">ousky</a>
</nav>

<div class="card" style="width: 200px;height: 100%;padding: 0;float: left;margin: 2px 5px 0 5px">
    <form method="post" id="sub" enctype="multipart/form-data">
        <a class="btn btn-primary"
           style="width: 190px;overflow: hidden;color: white;margin-top: 5px;margin-left: 5px">
            <input type="file" name="upFile" class="custom-file-input"
                   style="position: absolute;left: 0;top: 0;width: 190px;cursor: pointer;margin-top: 5px;margin-left: 5px"
                   onchange="sub()">
            上传
        </a>
    </form>
</div>

<ol id="pt" class="breadcrumb" style="margin: 2px 5px 0 205px">
    <li class="breadcrumb-item">
        Home
    </li>
</ol>

<%--<c:choose>--%>
<%--<c:when test="${entry.value}">--%>
<%--<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAMpJREFUeNpi/P//P8NAAiaGAQYD7gAWdIFPpxIoMS8ZiOcQqfY5SD0LFT1jAsRTuaU0GZjZuAgq/vPto+S3V3fmUssBokC8llNEjp2ZFWjk/1+Eg56TE0RJsgCD3BPImAvikGs7IzMLAxuvKAMrNz9ZaWAul5iKJAsXPwUBAMzK//9AaDIcIAkODiKCbbQcGHXAqANGHTDqgFEHjDpg1AGjDqCVA57/+f6Z7hZD7XwBahOmfHv1ANQqlqCzG54CcRrjaOd0oB0AEGAAscwsxMSUtNsAAAAASUVORK5CYII=">--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAVJJREFUeNpi/P//P8NAAhZkzrVr1zyB1FwglqSiHSZAfBZZQEtLC7sDQJZLSUlJcnNzU8Xm27dvg6jVQByqqqp6FpsaJjS+JCcnJ8O/f/+ogkFATk5uJ8gRQMcYE+MAqgN2dvYMeXn5g0DmGmyOYKJHQmNjY0tQUFA4gc0RLLS0mI+PD5YOQCACSp8BYka6OEBUVJRBXFwcW8IkLQSevnqLU05aTBivXlhiJKocINcSSgATwwADiqOA0pAbjYLRKBiNgtEoGHXA4MoFE+fsooulM/twt4pBzScMDY3VmRhi9a3TweIgGp86mFoY+PXrF4ocI3LHJL1own9sDkC2ANkwZDFsjkFXC3PAzL4CRqqlAWyWj6xcgBwFFDuAi5PtH6FGJK40QYwjQGaD7MCZCKfOWVt/5/7Lum/ff9Ekarg42f+qKIo1Z6cEN2J1wEAAgAADALEDt/dBJDrPAAAAAElFTkSuQmCC">--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>

<ul id="list" class="list-group">
    <c:forEach items="${directory}" var="entry">
        <li class="list-group-item list-group-item-action" onclick="ap(this)">
            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAMpJREFUeNpi/P//P8NAAiaGAQYD7gAWdIFPpxIoMS8ZiOcQqfY5SD0LFT1jAsRTuaU0GZjZuAgq/vPto+S3V3fmUssBokC8llNEjp2ZFWjk/1+Eg56TE0RJsgCD3BPImAvikGs7IzMLAxuvKAMrNz9ZaWAul5iKJAsXPwUBAMzK//9AaDIcIAkODiKCbbQcGHXAqANGHTDqgFEHjDpg1AGjDqCVA57/+f6Z7hZD7XwBahOmfHv1ANQqlqCzG54CcRrjaOd0oB0AEGAAscwsxMSUtNsAAAAASUVORK5CYII=">
                ${entry}
        </li>
    </c:forEach>
    <c:forEach items="${file}" var="entry">
        <li class="list-group-item list-group-item-action">
            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAVJJREFUeNpi/P//P8NAAhZkzrVr1zyB1FwglqSiHSZAfBZZQEtLC7sDQJZLSUlJcnNzU8Xm27dvg6jVQByqqqp6FpsaJjS+JCcnJ8O/f/+ogkFATk5uJ8gRQMcYE+MAqgN2dvYMeXn5g0DmGmyOYKJHQmNjY0tQUFA4gc0RLLS0mI+PD5YOQCACSp8BYka6OEBUVJRBXFwcW8IkLQSevnqLU05aTBivXlhiJKocINcSSgATwwADiqOA0pAbjYLRKBiNgtEoGHXA4MoFE+fsooulM/twt4pBzScMDY3VmRhi9a3TweIgGp86mFoY+PXrF4ocI3LHJL1own9sDkC2ANkwZDFsjkFXC3PAzL4CRqqlAWyWj6xcgBwFFDuAi5PtH6FGJK40QYwjQGaD7MCZCKfOWVt/5/7Lum/ff9Ekarg42f+qKIo1Z6cEN2J1wEAAgAADALEDt/dBJDrPAAAAAElFTkSuQmCC">
                ${entry}
        </li>
    </c:forEach>
</ul>
</body>
<script>
    function sub() {
        var form = new FormData(document.forms[0]);
        $.ajax({
            type: "post",
            async: true,
            url: "./upload?dp=" + path.join(''),
            data: form,
            processData: false,
            contentType: false,
            scriptCharset: 'ISO-8859-1',
            success: function (data) {
                // if (data === "true")
                //     alert(true);
                // else
                //     alert(false);
                openDirectory();
            }
        });
    }

    // 处理点击逻辑
    function ap(obj) {
        path.push(obj.textContent.trim() + "/");
        changeNv(obj.textContent);
        openDirectory();
        console.log(path.join(''));
    }

    // 处理地址跳转逻辑
    function jump(obj) {
        path = path.slice(0, obj.index + 1);
        changeNv(obj.textContent);
        openDirectory();
    }

    // 变化容器内导航标签
    function changeNv() {
        var pt = document.getElementById("pt");
        pt.innerHTML = ""; // 清空容器内容

        // 容器内添加内容
        for (var i = 0; i < path.length - 1; i++) {
            pt.appendChild(addNav(i, true));
        }
        pt.appendChild(addNav(path.length - 1), false);
    }

    // 添加导航标签
    function addNav(n, at) {
        var node = document.createElement("li");
        node.setAttribute("class", "breadcrumb-item");
        if (at) {
            var a = document.createElement("a");
            a.href = "#";
            a.setAttribute("onclick", "jump(this)");
            if (path[n] === "/")
                a.textContent = "Home";
            else
                a.textContent = path[n].replace("/", "");
            a.index = n;
            node.appendChild(a);
        } else {
            if (path[n] === "/")
                node.textContent = "Home";
            else
                node.textContent = path[n].replace("/", "");
        }
        return node;
    }

    // 添加内容
    function openDirectory() {
        list = document.getElementById("list");
        list.innerHTML = "";
        $.ajax({
            type: "get",
            async: true,
            url: "./glist",
            data: {dp: path.join('')},
            scriptCharset: 'ISO-8859-1',
            success: function (data) {
                var json = JSON.parse(data);
                var di = document.createElement("img");
                di.src = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAMpJREFUeNpi/P//P8NAAiaGAQYD7gAWdIFPpxIoMS8ZiOcQqfY5SD0LFT1jAsRTuaU0GZjZuAgq/vPto+S3V3fmUssBokC8llNEjp2ZFWjk/1+Eg56TE0RJsgCD3BPImAvikGs7IzMLAxuvKAMrNz9ZaWAul5iKJAsXPwUBAMzK//9AaDIcIAkODiKCbbQcGHXAqANGHTDqgFEHjDpg1AGjDqCVA57/+f6Z7hZD7XwBahOmfHv1ANQqlqCzG54CcRrjaOd0oB0AEGAAscwsxMSUtNsAAAAASUVORK5CYII=';
                var fi = document.createElement("img");
                fi.src = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAVJJREFUeNpi/P//P8NAAhZkzrVr1zyB1FwglqSiHSZAfBZZQEtLC7sDQJZLSUlJcnNzU8Xm27dvg6jVQByqqqp6FpsaJjS+JCcnJ8O/f/+ogkFATk5uJ8gRQMcYE+MAqgN2dvYMeXn5g0DmGmyOYKJHQmNjY0tQUFA4gc0RLLS0mI+PD5YOQCACSp8BYka6OEBUVJRBXFwcW8IkLQSevnqLU05aTBivXlhiJKocINcSSgATwwADiqOA0pAbjYLRKBiNgtEoGHXA4MoFE+fsooulM/twt4pBzScMDY3VmRhi9a3TweIgGp86mFoY+PXrF4ocI3LHJL1own9sDkC2ANkwZDFsjkFXC3PAzL4CRqqlAWyWj6xcgBwFFDuAi5PtH6FGJK40QYwjQGaD7MCZCKfOWVt/5/7Lum/ff9Ekarg42f+qKIo1Z6cEN2J1wEAAgAADALEDt/dBJDrPAAAAAElFTkSuQmCC'
                addLisrLi(di, json.directory, 'd');
                addLisrLi(fi, json.file);
            }
        });

        function addLisrLi(img, json, type) {
            var index, li;
            for (index in json) {
                li = document.createElement("li");
                li.setAttribute("class", "list-group-item list-group-item-action");
                if (type === "d")
                    li.setAttribute("onclick", "ap(this)");
                li.appendChild(img);
                li.innerHTML = li.innerHTML + json[index];
                list.appendChild(li);
            }
        }
    }
</script>
<script>
    // 处理拖入文件逻辑
    var list;
    var path = ["/"];
    var snd = document.getElementById("snd");

    snd.addEventListener("dragenter", function (e) {
        e.stopPropagation();
        e.preventDefault();
    }, false);

    snd.addEventListener("dragover", function (e) {
        e.stopPropagation();
        e.preventDefault();
    }, false);

    snd.addEventListener("drop", function (e) {
        e.stopPropagation();
        e.preventDefault();

        var dt = e.dataTransfer;
        var files = dt.files;

        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var form = new FormData();
            form.append("upFile", file);
            $.ajax({
                type: "post",
                async: true,
                url: "./upload?dp=" + path.join(''),
                data: form,
                processData: false,
                contentType: false,
                scriptCharset: 'ISO-8859-1',
                success: function (data) {
                    // if (data === "true")
                    //     alert(true);
                    // else
                    //     alert(false);
                    openDirectory();
                }
            });
        }
    }, false);
</script>
</html>
