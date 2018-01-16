<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>消息列表</title>
    <link href="../resources/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/static/index/index.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

    <script src="../resources/static/app.js"></script>
    <script type="text/javascript" src="../resources/layer/layer.js"></script>
    <script src="../resources/static/message/list.js"></script>

    <style>
        .search-content {
            padding: 5px;
        }
        .l-form-container > ul{
            display: inline-block;
        }
    </style>
    <script>
      var hrightsCodes = ${hrightsCodes};
    </script>
</head>
<body style="padding:0px;background:#fff;">
<div class="l-loading" style="display:block" id="pageloading"></div>
<div class="l-clear"></div>
<div class="search-content">
    <div>
        <form id="form1" class="liger-form" data-validate="{}">
            <div class="fields">
                <input data-type="text" data-label="消息编号" data-name="mesageid" data-width="100" validate="{required:false}" />
                <input data-type="text" data-label="消息来源" data-name="source" data-width="100" validate="{required:false}" />
                <input data-type="text" data-label="队列名称" data-name="queuename" data-width="100"  validate="{required:false}"/>
                <li data-label="是否死亡" data-type="select" data-name="isdead" data-textField="Code" data-width="100" validate="{required:false}" >
                    <input class="editor" data-data="getCountryData(1)" data-textField="Name" data-valueField="Code" />
                </li>
                <li data-label="消息状态" data-type="select" data-name="state" data-textField="Code" data-width="100" validate="{required:false}" >
                    <input class="editor" data-data="getCountryData(2)" data-textField="Name" data-valueField="Code" />
                </li>
            </div>

        </form>
        <div style="display: inline-block">
            <div class="l-button" onclick="f_search(1)" style="width: 150px;">
                <div class="l-button-l"></div>
                <div class="l-button-r"></div>
                <span>搜索</span>
            </div>
        </div>
    </div>

    <div>
        <form id="search2" class="liger-form" data-validate="{}">
            <div class="fields">
                <input data-type="text" data-label="队列名称" data-name="queuename"  validate="{required:true}"/>
                <li data-label="批量大小" data-type="select" data-name="size" data-textField="Code" data-width="350" validate="{required:true}" >
                    <input class="editor" data-data="getCountryData(3)" data-textField="Name" data-valueField="Code" />
                </li>
            </div>
        </form>
        <div style="display: inline-block">
            <div class="l-button" onclick="f_search(2)" style="width: 150px;">
                <div class="l-button-l"></div>
                <div class="l-button-r"></div>
                <span>重发</span>
            </div>
        </div>
    </div>


</div>
<div id="maingrid"></div>

</body>
</html>
