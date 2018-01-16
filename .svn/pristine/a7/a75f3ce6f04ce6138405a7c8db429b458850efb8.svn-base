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
    <title>系统变量管理</title>
    <link href="../resources/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/static/index/index.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

    <script src="../resources/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="../resources/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../resources/static/app.js"></script>
    <script src="../resources/static/sysvar/index.js"></script>
    <script>
        var hrightsCodes = ${hrightsCodes};
    </script>
</head>

<body style="padding:0px;background:#fff;">
<div class="l-loading" style="display:block" id="pageloading"></div>
<div class="l-clear"></div>
<div id="maingrid"></div>

<div id="target1" style="width:350px; margin:3px; display:block;">
    <form id="setting" class="liger-form" data-validate="{}">
        <div class="fields">

            <li data-label="系统编号:" data-type="select" data-name="sid" data-textField="name" data-width="350"
                validate="{required:true}">
                <input class="editor" data-data="getCountryData()" data-textField="name" data-valueField="id"/>
            </li>

            <%--           <input data-type="text" data-label="系统编号:" data-name="sid" validate="{required:true,maxlength:6}" />--%>
            <input data-type="text" data-label="变量KEY:" data-name="setKey" validate="{required:true,maxlength:100}"/>
            <input data-type="text" data-label="变量VALUE:" data-name="setValue"
                   validate="{required:true,maxlength:1000}"/>
            <input data-type="text" data-label="变量描述:" data-name="setDesc" validate="{required:false,maxlength:255}"/>
            <li data-label="编辑状态:" data-type="select" data-name="editMode" data-textField="Code" data-width="350" validate="{required:false}">
                <input class="editor" data-data="getEditModeData()" data-textField="Name" data-valueField="Code"/>
            </li>
        </div>
    </form>
</div>

</body>
</html>
