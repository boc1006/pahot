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
    <title>系统管理</title>
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
    <script src="../resources/static/sys/sysconfig.js"></script>

    <script>
        var hrightsCodes = ${hrightsCodes};
    </script>
</head>
<body style="padding:0px;background:#fff;">
<div class="l-loading" style="display:block" id="pageloading"></div>
<div class="l-clear"></div>
<div id="maingrid"></div>

<div id="target1" style="width:350px; margin:3px; display:block;">
    <form id="form1" class="liger-form" data-validate="{}">
        <div class="fields">
            <input data-type="text" data-label="系统id" data-name="sysid" validate="{required:true,maxlength:10}" />
            <input data-type="text" data-label="系统名称" data-name="name" validate="{required:true,maxlength:15}" />
            <input data-type="text" data-label="URL地址" data-name="url"  validate="{required:true}"/>
            <li data-label="系统类型" data-type="select" data-name="type" data-textField="Code" data-width="350" validate="{required:true}" >
                <input class="editor" data-data="getCountryData()" data-textField="Name" data-valueField="Code" />
            </li>
            <input data-type="int" data-label="系统排序" data-precision="1" data-name="sort"  validate="{required:true,min:1,max:127}"/>
            <input data-type="textarea" data-label="系统说明" data-name="remark"  validate="{required:false}"/>
        </div>
    </form>
</div>

</body>
</html>
