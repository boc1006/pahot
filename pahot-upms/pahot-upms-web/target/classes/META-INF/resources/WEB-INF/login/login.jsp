<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link href="resources/static/login/login.css" rel="stylesheet" type="text/css" />
	<link href="resources/icon/iconfont.css" rel="stylesheet" type="text/css" />

	<title>后台登录</title>
</head>
<body class="fy-iconfont">
<div class="loginWraper">
	<div id="loginform" class="loginBox">
		<form class="form form-horizontal" action="#" type="post">
			<div class="row cl logo-content">
				<img class="logo" src="resources/images/login/img_logo.png" alt="logo">
				<span>耙货联盟后台登录系统</span>
			</div>
			<div class="row cl user-content">
				<label class="form-label"><i class="fy-icon-yonghu"></i></label>
				<div class="formControls">
					<input id="username" name="" type="text" placeholder="账户" class="input-text size-L">
				</div>
			</div>
			<div class="row cl pwd-content ">
				<label class="form-label"><i class="fy-icon-pwd"></i></label>
				<div class="formControls">
					<input id="password" name="" type="password" placeholder="密码" class="input-text size-L">
				</div>
			</div>
			<div class="row cl">
				<div class="formControls txtCenter">
					<input id="submitButton" name="login" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
					<%--<input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">--%>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="resources/lib/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="resources/layer/layer.js"></script>
<script type="text/javascript" src="resources/static/login/login.js"></script>
</body>
</html>