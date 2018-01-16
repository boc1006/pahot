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
    <title>数据字典管理</title>
    <link href="../resources/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/static/index/index.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

    <script src="../resources/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="../resources/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../resources/static/app.js"></script>
    <script src="../resources/static/code/index.js"></script>

    <style>
        .search-content {
            padding: 5px;
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
    <div style="display: inline-block">
        <input type="text" id="systemList"/>
    </div>
    <div style="display: inline-block">
        <input type="text" id="txtKey" value="" style="width:200px"/>
    </div>
    <div style="display: inline-block">
        <div class="l-button" onclick="f_search()" style="width: 150px;">搜索
            <div class="l-button-l"></div>
            <div class="l-button-r"></div>
            <span>搜索</span>
        </div>
    </div>
</div>
<div id="maingrid"></div>
<div id="target1" style="width:350px; margin:3px; display:block;">
    <form id="form1" class="liger-form" data-validate="{}">
        <div class="fields">
            <li data-label="系统编号" data-type="select" data-name="type" data-textField="name" data-width="350" validate="{required:true}">
                <input class="editor" data-data="getCountryData()" data-textField="name" data-valueField="sid"/>
            </li>
            <input data-type="text" data-label="对照字段" data-name="field" validate="{required:true,maxlength:15}"/>
            <input data-type="text" data-label="对照字段名称" data-name="fieldname" validate="{required:true}"/>
            <input data-type="text" data-label="代码" data-name="code" validate="{required:true}"/>
            <input data-type="text" data-label="代码描述" data-name="codedesc" validate="{required:true}"/>
            <input data-type="int" data-label="排序" data-name="sort" validate="{required:true}"/>
            <input data-type="textarea" data-label="备注" data-name="remark" validate="{required:false}"/>
        </div>
    </form>
</div>

</body>
</html>
