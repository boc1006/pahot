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
    <link href="../resources/static/index/index.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/icon/iconfont.css" rel="stylesheet" type="text/css" />
    <script src="../resources/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

    <script src="../resources/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="../resources/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>

    <script src="../resources/static/app.js"></script>
    <script type="text/javascript" src="../resources/layer/layer.js"></script>
    <script src="../resources/static/menu/page.js"></script>
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
        <ul id="systemList" style="margin-top:3px;"></ul>
    </div>
    <div position="center" title="菜单信息">
        <ul id="menuListTree" style="margin-top:3px;"></ul>
    </div>
    <div position="right" title="操作信息">
        <div id="rightList"></div>
    </div>
</div>
<div class="addMenuContent" style="display: block;">
    <div>
        <form id="ff" method="post" class="liger-form" data-validate="{}">
            <ul>
                <li class="l-fieldcontainer l-fieldcontainer-first">
                    <ul>
                        <li style="width:90px;text-align:left;">选中菜单：</li>
                        <li style="width:180px;text-align:left;">
                            <div class="l-text" style="width: 178px;">
                                <input type="text"
                                       id="selectPName"
                                       name="pname"
                                       class="l-text-field"
                                       readonly="readonly"
                                       style="width: 174px;">
                                <div class="l-text-l"></div>
                                <div class="l-text-r"></div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
            <div class="fields" >
                <input data-type="text" data-label="菜单名称" data-name="name" validate="{required:true,maxlength:15}" data-width="350" />
                <input data-type="text" data-label="URL地址" data-name="url" validate="{required:true,maxlength：120}" data-width="350" />
                <li data-label="菜单类型" data-type="select" data-name="mtype" data-textField="mtypec" data-width="350" validate="{required:true}">
                    <input class="editor" data-data="getCountryData(1)" data-textField="Name" data-valueField="Code"/>
                </li>
                <li data-label="业务类型" data-type="select" data-name="btype" data-textField="btypec" data-width="350" validate="{required:true}">
                    <input class="editor" data-data="getCountryData(2)" data-textField="Name" data-valueField="Code"/>
                </li>
                <li data-label="弹窗类型" data-type="select" data-name="jump" data-textField="jumpc" data-width="350" validate="{required:true}">
                    <input class="editor" data-data="getCountryData(3)" data-textField="Name" data-valueField="Code"/>
                </li>
                <input data-type="int" data-label="系统排序" data-name="sort" validate="{required:true,min:1,max:127}"/>
            </div>
        </form>
    </div>

</div>

<div class="addfunContent" style="display: block;">
    <div>
        <form id="addfunFrom" method="post" class="liger-form" data-validate="{}">
            <div class="fields">
                <input data-type="text" data-label="操作名称" data-name="name" validate="{required:true}"/>
                <input data-type="text" data-label="CODE" data-name="code" validate="{required:true}"/>
                <li data-label="权限类型" data-type="select" data-name="type" data-textField="Code" data-width="350" validate="{required:true}">
                    <input class="editor" data-data="getCountryData(4)" data-textField="Name" data-valueField="Code"/>
                </li>
                <input data-type="int" data-label="排序" data-name="sort" validate="{required:true,min:1,max:127}"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
