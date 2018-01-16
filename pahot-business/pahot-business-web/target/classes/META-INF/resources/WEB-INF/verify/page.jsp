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
    <title>用户申请审核</title>
    <link href="../resources/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/static/index/index.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

    <script src="../resources/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../resources/static/app.js" type="text/javascript"></script>
    <script src="../resources/layer/layer.js" type="text/javascript"></script>
    <script src="../resources/static/verify/page.js" type="text/javascript"></script>

    <style>
        .layer_pop_win{
            width:450px;
        }
        .layer_pop_win table{
            width:100%;
            border-collapse: collapse;
        }
        .layer_pop_win table tr td{
            width:250px;
            padding:10px 10px;
            border:1px solid #eaeaea;
        }
        .layer_pop_win table tr td:first-child{
            width:150px;
            text-align: right;
            vertical-align: middle;
        }
        .search-content{
            padding:6px 0;
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
    <div style="display: inline-block;vertical-align: top;margin-left: 10px;">
        <input type="text" id="txtKey" value="" style="width:200px;height: 22px;"/>
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

<div class="layer_pop_win_box" style="display: none;">
    <div class="layer_pop_win">
        <table>
            <tbody>
            <tr>
                <td>商家名称：</td>
                <td>张三</td>
            </tr>
            <tr>
                <td>入驻类型：</td>
                <td>张三</td>
            </tr>
            <tr>
                <td>手机号码：</td>
                <td>张三</td>
            </tr>
            <tr>
                <td>用户名称：</td>
                <td>张三</td>
            </tr>
            <tr>
                <td>身份证号码：</td>
                <td>张三</td>
            </tr><tr>
                <td>商家地址：</td>
                <td>张三</td>
            </tr><tr>
                <td>店铺名称：</td>
                <td>张三</td>
            </tr><tr>
                <td>店铺地址：</td>
                <td>张三</td>
            </tr><tr>
                <td>店铺描述：</td>
                <td>张三</td>
            </tr><tr>
                <td>经营范围描述：</td>
                <td>张三</td>
            </tr><tr>
                <td>申请提交时间：</td>
                <td>张三</td>
            </tr><tr>
                <td>备注：</td>
                <td>张三</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
