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
    <title>菜单管理</title>
    <link href="../resources/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/icon/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="../resources/static/index/index.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

    <script src="../resources/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="../resources/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>

    <link href="../resources/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/ztree/js/jquery.ztree.all.min.js" type="text/javascript"></script>

    <script src="../resources/static/app.js"></script>
    <script type="text/javascript" src="../resources/layer/layer.js"></script>
    <script src="../resources/static/role/index.js"></script>
    <script>
      var hrightsCodes = ${hrightsCodes};
    </script>
    <style>
        .rightItem {
            padding: 5px 10px 5px;
        }
    </style>
</head>
<body style="padding:0px;background:#fff;">
<div id="layout">
    <div position="left" title="系统信息">
        <ul id="systemMenuList" style="margin-top:3px;"></ul>
    </div>
    <div position="center" title="菜单信息">
        <div class="l-button" onclick="f_submit()" id="submitData" style="width: 150px;display: none;">
            <div class="l-button-l"></div>
            <div class="l-button-r"></div>
            <span>授权</span>
        </div>
        <ul id="rightList" class="ztree" style="margin-top:3px;"></ul>
    </div>
</div>
<div class="addMenuContent" style="display: block;">
    <div>
        <form id="ff" method="post" class="liger-form" data-validate="{}">
            <div class="fields">
                <input data-type="text" data-label="角色名称" data-name="name" validate="{required:true,maxlength:30}"/>
                <input data-type="int" data-label="排序号" data-name="sort" validate="{required:true,min:1,max:127}"/>
                <input data-type="date" data-label="过期时间" data-name="validity" validate="{required:false}"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
