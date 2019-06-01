<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2019-04-05
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <style>
        .list-group-item input {
            width: 50px;
        }

        .file_name {
            display: inline-block;
            width: 30%;
        }

        .file_size {
            display: inline-block;
            width: 15%;
        }

        .file_time {
            display: inline-block;
            width: 15%;
        }

        .list-group-item a {
            display: inline-block;
            margin: 0 6px;
            opacity: 0;
            cursor: pointer;
        }

        .list-group-item:hover a {
            opacity: 1;
            color: #007bff !important;
        }

        .list-group-item:hover {
            background: ghostwhite;
        }

        #file_input {
            display: none;
        }

        #file_list {
            padding: 0;
            margin: 10px auto;
            width: 450px;
            height: 360px;
            box-shadow: 0 0 3px #ddd;
            list-style: none;
            overflow: auto;
            border-radius: 10px;
        }

        #file_list li {
            line-height: 40px;
            margin: 3px 15px;
        }

        #file_list span {
            font-size: 13px;
            display: inline-block;
        }

        #path a {
            color: #007bff;
            cursor: pointer;
        }
    </style>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<div id="content" style="padding: 3px 10px 0 190px;">
    <ol class="breadcrumb" style="background: white;margin: 0" id="path">
    </ol>
    <div class="card-body" style="background: white;padding: 10px;margin-top: 2px">
        <button type="button" class="btn btn-outline-primary btn-sm" data-toggle="modal" data-target="#upload"
                onclick="delete_file_list()">上传
        </button>
        <button type="button" class="btn btn-outline-primary btn-sm" data-toggle="dropdown">新建</button>
        <button type="button" class="btn btn-outline-primary btn-sm" id="paste" style="display: none">粘贴</button>

        <div class="dropdown-menu">
            <a class="dropdown-item" href="#" style="font-size: 13px;color: black" onclick="open_file_handle('f')"
               data-toggle="modal" data-target="#file_handle">空白文件</a>
            <a class="dropdown-item" href="#" style="font-size: 13px" onclick="open_file_handle('d')"
               data-toggle="modal"
               data-target="#file_handle">文件夹</a>
        </div>
    </div>
    <div class="card-body"
         style="background: white;padding: 10px 10px 20px 10px;margin-top: 2px;font-size: 12px;min-height: 500px;">
        <li class="list-group-item" style="background: #eee">
            <input type="checkbox" value="file_checkbox">
            <span class="file_name">文件名</span>
            <span class="file_size">大小</span>
            <span class="file_time">修改时间</span>
        </li>
        <ul class="list-group" id="files">
        </ul>
    </div>
</div>
<%--上传 start--%>
<div class="modal fade" id="upload" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top: 80px">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background: whitesmoke;">
                <button type="button" class="btn btn-primary btn-sm" onclick="addfiles()">添加文件</button>
                <form id="upload_form" enctype="multipart/form-data">
                    <input type="file" name="upFile" id="file_input" multiple="multiple" autocomplete="off"
                           onchange="addfilesto(this)">
                </form>
            </div>
            <div class="modal-body">
                <ul id="file_list">
                </ul>
            </div>
            <div class="modal-footer" style="background: whitesmoke">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="uploadfile()">
                    上传
                </button>
            </div>
        </div>
    </div>
</div>
<%--上传 end--%>
<%--新建 start--%>
<div class="modal fade align-items-center" id="file_handle" tabindex="-1" role="dialog" aria-hidden="true"
     style="margin-top: 100px">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 320px;height: 180px">
            <div class="modal-header" style="background: whitesmoke">

            </div>
            <div class="modal-body" style="height: 100px">
                <input type="text" class="form-control"
                       style="width: 280px;margin: 10px auto;font-size: 14px">
            </div>
            <div class="modal-footer" style="background: whitesmoke">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="file_handle()">
                </button>
            </div>
        </div>
    </div>
</div>
<%--新建 end--%>
<%--编辑 start--%>
<div class="modal fade align-items-center" id="editFile" tabindex="-1" role="dialog" aria-hidden="true"
     style="">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 1000px;">
            <div class="modal-header" id="edit-bt" style="background: whitesmoke">

            </div>
            <div class="modal-body" style="padding: 0;margin: 5px auto">
                <div style="height: 35px;">
                    <select class="form-control" id="editSize" style="width: 50px;height: 30px;float: right">
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                        <option selected="selected">13</option>
                        <option>14</option>
                        <option>15</option>
                        <option>16</option>
                        <option>17</option>
                        <option>18</option>
                    </select>
                </div>
                <textarea id="edit-content" style="height: 570px;width: 960px;font-size: 13px"></textarea>
            </div>
            <div class="modal-footer" style="background: whitesmoke;">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="savecontent()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<%--编辑 end--%>

<%--警告 start--%>
<div class="modal fade align-items-center" id="warning" tabindex="-1" role="dialog" aria-hidden="true"
     style="margin-top: 100px">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 350px">
            <div class="modal-header" style="border: 0">
                删除
            </div>
            <div class="modal-body" style="height: 60px;font-size: 15px">
            </div>
            <div class="modal-footer" style="border: 0">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">
                    确认
                </button>
            </div>
        </div>
    </div>
</div>
<%--警告 end--%>
</body>
<script>
    var path = '${path}';
    getfiles();
    // 模态框处理
    var modalWidth = $("#editFile").width();
    var left = "-" + parseInt(modalWidth) / 3 + "px";
    $("#editFile").css({"margin-left": left});

    function getpaths() {
        var pathNode = document.getElementById("path");
        pathNode.innerHTML = "";
        var pathArray = path.split('/');
        for (var index in pathArray) {
            if (index < pathArray.length - 1) {
                var a = document.createElement('a');
                a.setAttribute('onclick', 'jumppath(' + index + ')');
                if (pathArray[index] === '')
                    a.innerHTML = '根目录';
                else
                    a.innerHTML = pathArray[index];

                var li = document.createElement('li');
                li.setAttribute('class', 'breadcrumb-item');
                li.appendChild(a);
                pathNode.appendChild(li);
            } else {
                pathNode.innerHTML += "<li class=\"breadcrumb-item active\">" + pathArray[index] + "</li>";
            }
        }
    }

    function jumppath(n) {
        if (undefined !== n) {
            var p = path.split('/').slice(0, n + 1);
            path = "";
            for (i in p) {
                path += '/' + p[i];
            }
            path = path.replace('//', '/');
            getfiles();
        }
    }

    function getfiles(obj) {
        if (undefined !== obj) {
            path += '/' + obj.textContent;
        }
        path = path.replace('//', '/');
        document.cookie = "path=" + path;
        $.ajax({
            type: "post",
            async: true,
            url: "./getfiles",
            scriptCharset: 'ISO-8859-1',
            success: function (data) {
                files_size = 0;
                var json = JSON.parse(data);
                var files = document.getElementById("files");
                files.innerHTML = "";
                for (var index in json) {
                    var type = json[index].name.split('.');
                    if (type.length >= 2) {
                        type = type[1];
                    } else {
                        type = "";
                    }
                    if (json[index].directory === true) {
                        files.innerHTML += "<li class=\"list-group-item\">" +
                            "<input type=\"checkbox\" value=\"file_checkbox\">" +
                            "<span class=\"file_name\" style='cursor: pointer' onclick='getfiles(this)'>" +
                            "<img style=\"margin-right: 10px\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAMpJREFUeNpi/P//P8NAAiaGAQYD7gAWdIFPpxIoMS8ZiOcQqfY5SD0LFT1jAsRTuaU0GZjZuAgq/vPto+S3V3fmUssBokC8llNEjp2ZFWjk/1+Eg56TE0RJsgCD3BPImAvikGs7IzMLAxuvKAMrNz9ZaWAul5iKJAsXPwUBAMzK//9AaDIcIAkODiKCbbQcGHXAqANGHTDqgFEHjDpg1AGjDqCVA57/+f6Z7hZD7XwBahOmfHv1ANQqlqCzG54CcRrjaOd0oB0AEGAAscwsxMSUtNsAAAAASUVORK5CYII=\"/>"
                            + json[index].name + "</span>" +
                            "<span class=\"file_size\">" + json[index].size + "KB</span>" +
                            "<span class=\"file_time\">" + json[index].lastModified + "</span>" +
                            "<div class=\"file_selecr\" style=\"display: inline-block;margin-left: 30px\">" +
                            "<a onclick='file_paste(\"" + json[index].name + "\",\"" + 'copy' + "\")'>复制</a>" +
                            "<a onclick='file_paste(\"" + json[index].name + "\",\"" + 'shear' + "\")'>剪切</a>" +
                            "<a onclick='open_file_handle(\"m\",\"" + json[index].name + "\")'>重命名</a>" +
                            "<a onclick='tozip(\"" + json[index].name + "\")'>压缩</a>" +
                            "<a>下载</a>" +
                            "<a onclick='remove(\"" + json[index].name + "\")'>删除</a>" +
                            "</div>" +
                            "</li>";
                    } else if (type === 'zip') {
                        files.innerHTML += "<li class=\"list-group-item\">" +
                            "<input type=\"checkbox\" value=\"file_checkbox\">" +
                            "<span class=\"file_name\" style='cursor: pointer' onclick='getfiles(this)'>" +
                            "<img style=\"margin-right: 10px\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAYFJREFUeNpi/P//P8NAAhZkzrVr1zyB1FwglqSiHSZAfBZZQEtLC7sDQJZLSUlJcnNzU8Xm27dvg6jVQByqqqp6FpsaJjS+JCcnJ8O/f/+ogkFATk5uJ8gRQMcYE+MAqgN2dvYMeXn5g0DmGmyOYKJHQmNjY0tQUFA4gc0RLLS0mI+PD5YOQCACSp8BYka6OEBUVJRBXFwcW8IkPgQ+r2lk+PPsJnl5XEqdgTeknvhyABsAWS6Ytwyr3PtJUTjlYPKEAEWJEGQ5MZbQzAGEQoDmDhgNAWITGk0dQCkYcAewUJoIh38IgIpTchMai6Qa5Q4gVJYP+SgYcAcwIjfLHxZo0qWNLj/hOp4GCQs7bo0121D4D1u8wGIwGlkcd/3+k/xyANlgdMcgy8McRbM0QIoFVC8J8VkOCxVSHMdCTZ+TEyooDvjPKfCP8ddXJgZGJqISIskW/v8HtIP/H85seGdWYT3L/eN1jN8/0qR8+M8l8PePgkWzSlp/I1YHDAQACDAAtKS/DHmsv9AAAAAASUVORK5CYII=\"/>"
                            + json[index].name + "</span>" +
                            "<span class=\"file_size\">" + json[index].size + "KB</span>" +
                            "<span class=\"file_time\">" + json[index].lastModified + "</span>" +
                            "<div class=\"file_selecr\" style=\"display: inline-block;margin-left: 30px\">" +
                            "<a onclick='file_paste(\"" + json[index].name + "\",\"" + 'copy' + "\")'>复制</a>" +
                            "<a onclick='file_paste(\"" + json[index].name + "\",\"" + 'shear' + "\")'>剪切</a>" +
                            "<a onclick='open_file_handle(\"m\",\"" + json[index].name + "\")'>重命名</a>" +
                            "<a onclick='tofiles(\"" + json[index].name + "\")'>解压</a>" +
                            "<a>下载</a>" +
                            "<a onclick='remove(\"" + json[index].name + "\")'>删除</a>" +
                            "</div>" +
                            "</li>";
                    } else {
                        files.innerHTML += "<li class=\"list-group-item\">" +
                            "<input type=\"checkbox\" value=\"file_checkbox\">" +
                            "<span class=\"file_name\">" +
                            "<img style=\"margin-right: 5px\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAOVJREFUeNpi/P//P8NAAhZkzrVr1zyB1FwglqSiHSZAfBZZQEtLC7sDQJZLSUlJcnNzU8Xm27dvg6jVQByqqqp6FpsaJjS+JCcnJ8O/f/+ogkFATk5uJ8gRQMcYE+MAqgN2dvYMeXn5g0DmGmyOYKJHQmNjY0tQUFA4gc0RLLS0mI+PD5YOQCACSp8BYka6OEBUVJRBXFwcW8KkTwiAACwx4gJMDAMMRh0w6oBRB4w6YNQBow4YdcCoA0YdMOgc8Pzbt280swxq9gt8reKU58+fgzqnEjRyw1MgTkMWYBzo7jlAgAEAzk5sMbucHicAAAAASUVORK5CYII=\"/>"
                            + json[index].name + "</span>" +
                            "<span class=\"file_size\">" + json[index].size + "KB</span>" +
                            "<span class=\"file_time\">" + json[index].lastModified + "</span>" +
                            "<div class=\"file_selecr\" style=\"display: inline-block;margin-left: 30px\">" +
                            "<a onclick='openedit(\"" + json[index].name + "\")'>编辑</a>" +
                            "<a onclick='file_paste(\"" + json[index].name + "\",\"" + 'copy' + "\")'>复制</a>" +
                            "<a onclick='file_paste(\"" + json[index].name + "\",\"" + 'shear' + "\")'>剪切</a>" +
                            "<a onclick='open_file_handle(\"m\",\"" + json[index].name + "\")'>重命名</a>" +
                            "<a onclick='tozip(\"" + json[index].name + "\")'>压缩</a>" +
                            "<a>下载</a>" +
                            "<a onclick='remove(\"" + json[index].name + "\")'>删除</a>" +
                            "</div>" +
                            "</li>";
                    }
                }
                getpaths();
            }
        });
    }

    function addfiles() {
        $('#file_input').trigger('click');
    }

    function delete_file_list() {
        document.getElementById('file_list').innerHTML = '';
    }

    function addfilesto(obj) {
        delete_file_list();
        for (var i = 0; i < obj.files.length; i++) {
            var file_list = document.getElementById('file_list');
            file_list.innerHTML += '<li>' +
                '<span style="width: 230px">' + obj.files[i].name + '</span>' +
                '<span style="width: 110px;color: gray">' + parseInt(obj.files[i].size / 1000) + 'KB</span>' +
                '<span style="width: 80px;color: cornflowerblue">等待上传</span>' +
                '</li>';
        }
    }

    function file_handle() {
        var fd;
        var cusname = "";
        var div = document.getElementById('file_handle').children[0].children[0];
        if (div.children[1].children[0].placeholder === '文件名称') {
            fd = 'f';
        } else if (div.children[1].children[0].placeholder === '文件夹名称') {
            fd = 'd';
        } else {
            fd = 'm';
            cusname = div.children[1].children[0].placeholder;
        }
        if (div.children[1].children[0].value.length !== 0) {
            $.ajax({
                type: "post",
                async: true,
                url: "./filehandle",
                data: {name: div.children[1].children[0].value, cusname: cusname, type: fd},
                scriptCharset: 'ISO-8859-1',
                success: function (data) {
                    if (data === '1') {
                        getfiles();
                    }
                }
            });
        }
    }

    function open_file_handle(fd, name) {
        var title = $('#file_handle .modal-header')[0];
        var val = $('#file_handle .form-control')[0];
        var bt = $('#file_handle .btn-primary')[0];
        if (fd === 'f') {
            title.innerHTML = '<span>新建文件</span>';
            val.placeholder = '文件名称';
            val.value = '';
            $('#file_handle .btn-primary')[0].innerHTML = '新建';
        } else if (fd === 'd') {
            title.innerHTML = '<span>新建文件夹</span>';
            val.placeholder = '文件夹名称';
            val.value = '';
            bt.innerHTML = '新建';
        } else if (fd === 'm') {
            title.innerHTML = '<span>重命名</span>';
            val.placeholder = name;
            val.value = name;
            bt.innerHTML = '修改';
            $('#file_handle').modal('show');
        }
    }

    var complete;

    function uploadfile() {
        var file_list = document.getElementById('file_list');
        var input = document.getElementById('upload_form').children[0].files;
        complete = 0;
        for (var i = 0; i < input.length; i++) {
            uploadajax(i, input, file_list);
        }
    }

    function uploadajax(i, input, file_list) {
        var form = new FormData();
        form.append('upFile', input[i]);
        $.ajax({
            type: "post",
            async: true,
            url: "./upload",
            data: form,
            processData: false,
            contentType: false,
            scriptCharset: 'ISO-8859-1',
            xhr: function () {
                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener('progress', function (e) {
                    file_list.children[i].children[2].innerHTML = parseInt(e.loaded / e.total * 100) + '%';
                });
                return xhr;
            },
            success: function (data) {
                ++complete;
                if (complete === input.length)
                    getfiles();
            }
        });
    }

    function openedit(name) {
        $.ajax({
            type: "get",
            async: true,
            url: "./getcontent",
            data: {name: name},
            scriptCharset: 'ISO-8859-1',
            success: function (data) {
                var json = JSON.parse(data);
                $('#edit-bt')[0].innerHTML = json.name;
                $('#edit-content')[0].value = json.content;
                $('#editFile').modal('show');
            }
        });
    }

    function savecontent() {
        $.ajax({
            type: "post",
            async: true,
            url: "./setcontent",
            data: {name: $('#edit-bt')[0].innerHTML, content: $('#edit-content')[0].value},
            scriptCharset: 'ISO-8859-1',
            success: function (data) {
                var json = JSON.parse(data);
                if (json[0] === true)
                    $('#editFile').modal('hide')

            }
        });
    }

    function remove(name, flag) {
        if (undefined !== flag) {
            $.ajax({
                type: "get",
                async: true,
                url: "./remove",
                data: {name: name},
                scriptCharset: 'ISO-8859-1',
                success: function (data) {
                    getfiles();
                }
            });
        } else {
            var body = $('#warning .modal-body')[0];
            var bt = $('#warning .btn-primary')[0];
            body.innerHTML = "您确定要将 " + getCookie('path') + "/" + name + " 删除？";
            bt.setAttribute("onclick", "remove(\"" + name + "\",1)");
            $('#warning').modal('show');
        }
    }

    function tozip(name) {
        $.ajax({
            type: "get",
            async: true,
            url: "./tozip",
            data: {name: name},
            scriptCharset: 'ISO-8859-1',
            success: function (data) {
                getfiles();
            }
        });
    }

    function tofiles(name) {
        $.ajax({
            type: "get",
            async: true,
            url: "./tofiles",
            data: {name: name},
            scriptCharset: 'ISO-8859-1',
            success: function (data) {
                getfiles();
            }
        });
    }

    function file_paste(pname, type, flag) {
        if (undefined !== flag) {
            if (flag === 0) {
                var body = $('#warning .modal-body')[0];
                var bt = $('#warning .btn-primary')[0];
                body.innerHTML = "您确定要将 " + pname + " 移动到" + getCookie('path') + "？";
                bt.setAttribute("onclick", "file_paste(\"" + pname + "\",\"" + type + "\",1)");
                $('#warning').modal('show');
            } else {
                $("#paste").css("display", "none");
                $.ajax({
                    type: "get",
                    async: true,
                    url: "./paste",
                    data: {path: pname, name: pname.split('/')[pname.split('/').length - 1], type: type},
                    scriptCharset: 'ISO-8859-1',
                    success: function (data) {
                        getfiles();
                    }
                });
            }
        } else {
            var paste_path = getCookie('path') + '/' + pname;
            $("#paste").css("display", "inline");
            $("#paste")[0].setAttribute("onclick", "file_paste(\"" + paste_path + "\",\"" + type + "\",0)");
        }
    }

    $("#editSize").on("change", function () {
        $('#edit-content')[0].style.fontSize = this.value;
    });

</script>

<script>
    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg)) {
            return unescape(arr[2]);
        } else {
            return null;
        }
    }

    function delCookie(name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null) {
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
        }
    }
</script>
</html>