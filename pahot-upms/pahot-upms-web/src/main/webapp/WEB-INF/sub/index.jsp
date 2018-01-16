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
    <title>分布式事务消息订阅</title>
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
    <script src="../resources/static/sub/index.js"></script>
    <script type="text/javascript" src="../resources/layer/layer.js"></script>


    <style>
        .search-content {
            padding: 5px;
        }
        .l-form-container > ul{
            display: inline-block;
            margin-left: 10px;
        }
    </style>
    <script>
      var hrightsCodes = ${hrightsCodes};
    </script>
</head>
<body style="padding:0px;background:#fff;">
<div class="l-loading" style="display:block" id="pageloading"></div>
<div class="l-clear"></div>
<div class="search-content l-form-container">
    <%--
        //id subname queuename
    --%>
    <%--<div style="display: inline-block">--%>
        <%--订阅编号: <input type="text" id="s_id"/>--%>
    <%--</div>--%>
    <%--<div style="display: inline-block">--%>
        <%--订阅名称:--%>
        <%--<input type="text" class="l-text-field" id="s_subname" value="" style="width:200px"/>--%>
    <%--</div>--%>
    <ul>
        <li class="l-fieldcontainer l-fieldcontainer-first" >
            <ul>
                <li style="width:90px;text-align:left;float: left;height: 26px;line-height: 24px;">订阅编号:</li>
                <li style="width:180px;text-align:left;float: left;">
                    <div class="l-text" style="width: 178px;">
                        <input type="text" id="s_id" name="source" class="l-text-field" style="width: 174px;">
                        <div class="l-text-l"></div>
                        <div class="l-text-r"></div>
                    </div>
                </li>
            </ul>
        </li>
    </ul>
        <ul>
            <li class="l-fieldcontainer l-fieldcontainer-first" >
                <ul>
                    <li style="width:90px;text-align:left;float: left;height: 26px;line-height: 24px;">订阅名称:</li>
                    <li style="width:180px;text-align:left;float: left;">
                        <div class="l-text" style="width: 178px;">
                            <input type="text" id="s_subname" name="source" class="l-text-field" style="width: 174px;">
                            <div class="l-text-l"></div>
                            <div class="l-text-r"></div>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
        <ul>
            <li class="l-fieldcontainer l-fieldcontainer-first" >
                <ul>
                    <li style="width:90px;text-align:left;float: left;height: 26px;line-height: 24px;">消息队列名称:</li>
                    <li style="width:180px;text-align:left;float: left;">
                        <div class="l-text" style="width: 178px;">
                            <input type="text" id="s_queuename" name="source" class="l-text-field" style="width: 174px;">
                            <div class="l-text-l"></div>
                            <div class="l-text-r"></div>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
    <%--<div style="display: inline-block">--%>
        <%--消息队列名称: <input type="text" id="s_queuename" value="" style="width:200px"/>--%>
    <%--</div>--%>
    <div style="display: inline-block;vertical-align: top;margin-left: 10px;">
        <input type="button" class="l-button" id="s_search" value="搜     索" onclick="f_search()" style="width:200px;height: 26px;"/>
    </div>
</div>
<div id="maingrid"></div>
<div id="target1" style="width:350px; margin:3px; display:block;">
    <form id="form1" class="liger-form" data-validate="{}">
        <div class="fields">
            <input data-type="text" data-label="订阅编号" data-name="id1"
                   validate="{required:true,digits:true,max:2147483646}"/>

            <input data-type="text" data-label="订阅名称" data-name="subname" validate="{required:true,maxlength:60}"/>
            <div data-type="select" data-label="数据类型" data-name="type" validate="{required:true}">
                <input class="editor" data-data="[{text: 'JSON', id: 1}, {text: 'XML', id: 2}]" id="dataTypeList"
                       data-textField="text" data-valueField="id"/>
            </div>
            <input data-type="text" data-label="来源编号" data-name="source" validate="{required:true,maxlength:30}"/>
            <input data-type="textarea" data-label="队列名称" data-name="queuename"
                   validate="{required:true,maxlength:500}"/>
            <input data-type="textarea" data-label="延迟(分钟)" data-name="delays"
                   validate="{required:true,maxlength:500}"/>
            <input data-type="text" data-label="数据源标识" data-name="dbid" validate="{required:true,maxlength:32}"/>
            <input data-type="text" data-label="备注" data-name="remark" validate="{required:true,maxlength:120}"/>
        </div>
    </form>
</div>

</body>
</html>
