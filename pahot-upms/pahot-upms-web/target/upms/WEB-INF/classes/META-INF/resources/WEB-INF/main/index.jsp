<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<title>耙货运营管理系统</title>
	<link href="resources/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="resources/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
	<link href="resources/static/index/index.css" rel="stylesheet" type="text/css" />
	<link href="resources/icon/iconfont.css" rel="stylesheet" type="text/css" />
	<script src="resources/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

	<script type="text/javascript" src="resources/layer/layer.js"></script>

	<script src="resources/lib/json2.js"></script>
    <script src="resources/static/app.js"></script>
    <script src="resources/static/index/index.js"></script>
</head>
<body style="padding:0px;background:#EAEEF5;">
<script>
	var menuData = ${menu};
	var userId = "${userId}";
</script>
<div id="psw">
	<form id="pswForm"></form>
</div>
<div id="pageloading"></div>
<div id="topmenu" class="l-topmenu fy-iconfont" style="background: url(resources/images/login/login_bg.jpg);padding: 5px;">
	<div class="fy-icon-logo welcome-head">欢迎您！ ${username}</div>
	<div class="l-topmenu-welcome" style="margin-top: 5px;">
		<%--<label> 皮肤切换：</label>--%>
		<%--<select id="skinSelect">--%>
			<%--<option value="aqua">默认</option>--%>
			<%--<option value="silvery">Silvery</option>--%>
			<%--<option value="gray">Gray</option>--%>
			<%--<option value="gray2014">Gray2014</option>--%>
		<%--</select>--%>
		<%--<a href="index.aspx" class="l-link2">服务器版本</a>--%>
		<%--<span class="space">|</span>--%>
		<%--<a href="http://www.ligerui.com/pay.html" class="l-link2" target="_blank">捐赠</a>--%>
		<%--<span class="space">|</span>--%>
			<a href="javascript:updataPsw();" class="l-link2" style="margin-right: 10px;">修改密码</a>
		<a href="javascript:logout();" class="l-link2">退出</a>
	</div>
</div>
<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; ">
	<div position="left"  title="菜单列表" id="accordion1">
		<c:forEach items="${menuOnTree}" var="item" varStatus="status">
            <div title="${item.name}" class="l-scroll" url="${item.url}">
                <ul id="menu${item.id}" style="margin-top:3px;"></ul>
            </div>
		</c:forEach>
	</div>
	<div position="center" id="framecenter">
		<div tabid="home" title="我的主页" style="height:300px" >
			<iframe frameborder="0" name="home" id="home" src="welcome.htm"></iframe>
		</div>
	</div>

</div>
<div  style="height:35px; line-height:32px; text-align:center;background: #363636;color: #FFF;">
	Copyright © 2017-2020 www.pahot.cn
</div>
<div style="display:none"></div>
</body>
</html>
