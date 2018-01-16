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
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link href="<%=basePath%>resources/icon/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/static/error/404.css" rel="stylesheet" type="text/css" />

    <title>500</title>
</head>
<body>
<section class="container-fluid page-404 minWP text-c">
    <p class="error-title fy-iconfont"><i class="Hui-iconfont va-m fy-icon-bad" style="font-size:80px;font-style: normal;"></i>
        <span class="va-m"> 404</span>
    </p>
    <p class="error-description">不好意思，服务器出错了~</p>
    <p class="error-info">您可以：
        <a href="javascript:;" onclick="history.go(-1)" class="c-primary">&lt; 返回上一页</a>
    </p>
</section>
</body>
</html>
