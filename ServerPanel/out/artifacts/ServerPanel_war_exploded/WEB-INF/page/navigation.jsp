<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2019-04-05
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<style>
    #nav {
        list-style: none;
        background: #222;
        height: 100%;
        padding: 0;
        width: 180px;
        float: left;
        min-height: 600px;
    }

    #nav li {
        height: 50px;
        line-height: 50px;
    }

    #nav li:hover {
        background: #333;
    }

    #nav a {
        color: white;
        font-size: 15px;
        display: block;
        padding-left: 35px;
    }

    #nav a:hover, #nav a:visited {
        color: white;
        text-decoration: none;
    }

    #nav a:focus {
        text-decoration: none
    }

    body {
        background: #eee;
    }
</style>
<ul id="nav" style="position: fixed;top: 0;">
    <li></li>
    <li><a href="index">首页</a></li>
    <li><a href="#">监控</a></li>
    <li><a href="#">网站</a></li>
    <li><a href="#">安全</a></li>
    <li><a href="files">文件</a></li>
    <li><a href="#">数据库</a></li>
    <li><a href="#">软件</a></li>
    <li><a href="#">面板设置</a></li>
    <li><a href="#">退出</a></li>
</ul>