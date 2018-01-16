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
<html>
<head>
    <title>组织机构管理</title>
    <link href="../resources/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/static/index/index.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/lib/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="../resources/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <%--ztree--%>
    <link href="../resources/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/ztree/js/jquery.ztree.all.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="../resources/layer/layer.js"></script>

    <script src="../resources/static/app.js"></script>
    <script src="../resources/static/org/orgAndUser.js"></script>
    <style>
        .l-form li {padding-top: 4px;padding-bottom: 4px;}
        .control {margin-right: 10px;}
        .l-toolbar {background: #D7D7D7;height: 33px;border: 1px solid #EFF7F7;border-top: 1px solid #EFF7F7;}
    </style>
    <script>
      var hrightsCodes = ${hrightsCodes};
    </script>
</head>
<body>
<div id="layout">
    <div position="left" title="组织机构">
        <ul id="orgData" style="margin-top:3px;"></ul>
    </div>
    <div position="center" title="用户列表">
        <div id="toolBar"></div>
        <div id="usersData"></div>
    </div>
    <div id="addOrg">
        <form id="addOrgForm" class="liger-form" data-validate="{}"></form>
    </div>
    <div id="addMember">
        <form id="addMemberForm" class="liger-form" data-validate="{}"></form>
    </div>
</div>
<div id="checkUserRoleWin" style="display: none;">
    <ul id="sysRoleList" class="ztree" style=""></ul>
</div>
<input type="text" id="test6" name="test6"/>


<script>
  var zTreeObj;

  function openWinUserRole(uid) {
    var height = $(window).height();
    if(height < 600){
      height = height * 0.8;
    }
    layer.open({
      type: 1,
      title: "选择角色",
      content: $("#checkUserRoleWin"),
      area: ["400px", height +"px"],
      success: function () {
        $.post("../role/getsysroleuser.sec", {id: uid}, function (data) {
          if (!data.status) {
            layer.msg(data.info);
            return;
          }
          for (var i = 0; i < data.data.length; i++) {
            data.data[i].nocheck = true;
            data.data[i].open = true;
          }
          initZtree(data.data,uid);
        });
      }
    });
  }

  function initZtree(data,uid) {
    var setting = {
      check: {
        enable: true
      },
      callback: {
        beforeCheck: function (treeId, treeNode) {
          var state;
          if (treeNode.checked == false) {
            state = 1;
          } else {
            state = 0;
          }
          var isCheck;
          $.ajax({
            url: '../role/updaterolelink.sec',
            data: {
              id: treeNode.dataid,
              uid: zTreeObj.globalUid,
              sid: treeNode.sId,
              rid: treeNode.id,
              state: state
            },
            cache: false,
            async: false,
            type: "post",
            dataType: 'json/xml/html',
            success: function (result) {
            },
            error: function (err) {
              if (err.readyState == 4 && err.status == 200) {
                var rst = JSON.parse(err.responseText);
                if (rst.status) {
                  isCheck = true;
                  state == 0 ? treeNode.dataid = null : treeNode.dataid = rst.data;
                } else {
                  isCheck = false;
                }
              }
            }
          })
        }
      }
    };

    zTreeObj = $.fn.zTree.init($("#sysRoleList"), setting, data);
    zTreeObj.globalUid = uid;
  }

  function addRoleLinkUser(data) {
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "updatestate.sec",
      type: "post",
      data: data,
      success: function (data) {
        //grid.reload();
        manager.close();
        if (data.status) {
          layer.closeAll();
        } else {
          $.ligerDialog.waitting(data.info);
          setTimeout(function () {
            $.ligerDialog.closeWaitting();
          }, 1000);
        }
      },
      error: function (err) {
        $.ligerDialog.waitting("授权失败");
        setTimeout(function () {
          $.ligerDialog.closeWaitting();
        }, 1000);
      }
    })
  }

</script>

</body>
</html>
