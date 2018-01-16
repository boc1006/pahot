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
    <title>商品分类管理</title>
    <link href="../resources/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="../resources/icon/iconfont.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../resources/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="../resources/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../resources/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../resources/static/app.js"></script>
    <script type="text/javascript" src="../resources/layer/layer.js"></script>
    <script src="../resources/static/detail/detail.js"></script>
    <script src="../resources/static/detail/attr.js"></script>
    <script>
      var hrightsCodes = ${hrightsCodes};
    </script>
    <style>
        .attr_child {
            overflow: hidden;
            border-bottom: 1px solid #e5e5e5;
        }

        .attr_child > li {
            float: left;
        }

        .attr_typename {
            width: 25%;
            text-align: left;
            line-height: 80px;
        }

        .attr_typename > div {
            padding-right: 20px;
            border-left: 1px dashed #f5f5f5;
        }

        .attr_typename > div > div {
            display: inline-block;
        }

        .attr_typename div.tent {
            width: 60%;
            text-align: center;
        }

        .attr_typename div.tent .text {
            max-width: 100%;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        .attr_typename div.icons {
            width: 37%;
            height: 80px;
            overflow: hidden;
            vertical-align: top;
            text-align: center;
            display: none;
        }

        .attr_typename:hover div.icons {
            display: inline-block;
        }

        .attr_child_content {
            width: 75%;
        }

        .attr_child_name {
            display: inline-block;
            width: 19.5%;
            line-height: 40px;
            box-sizing: border-box;
            border-right: 1px dashed #f5f5f5;
        }

        .attr_child_name > div {
            display: inline-block;
        }

        .attr_child_name > div.tent {
            width: 60%;
            text-align: center;
        }

        .attr_child_name > div.icons {
            width: 37%;
            height: 40px;
            overflow: hidden;
            vertical-align: top;
            text-align: center;
            display: none;
        }

        .attr_child_name:hover > div.icons {
            display: inline-block;
        }

        .attrEditContent {
            width: 95%;
            font-size: 12px;
            border-top: 1px solid #e5e5e5;
            box-sizing: border-box;
            margin: 20px 2.5% 0;
        }

        .attrEditContent > li {
            overflow: hidden;

        }

        .attr_1_child_1, .attr_1_child_2, .attr_1_child_3, .attr_1_child_4, .attr_1_child_5 {
            border-bottom: 1px dashed #e5e5e5;
        }

        .attr_2_child_1, .attr_2_child_2, .attr_2_child_3, .attr_2_child_4, .attr_2_child_5 {
            border-bottom: 1px dashed #e5e5e5;
        }

        .attr_3_child_1, .attr_3_child_2, .attr_3_child_3, .attr_3_child_4, .attr_3_child_5 {
            border-bottom: 1px dashed #e5e5e5;
        }

        .delete_i {
            font-style: normal;
            font-size: 16px;
            margin-right: 5px;
            cursor: pointer;
        }

        .edit {
            right: 21px;
        }

        .heid {
            display: none;
        }

        .edit_input {
            height: 35px;
            line-height: 30px;
            border: 1px solid #f5f5f5;
            box-sizing: border-box;
            width: 100%;
        }

        .disabled {
            color: #aaa;
        }
    </style>
</head>
<body style="padding:0px;background:#fff;">
<div id="layout">
    <div id="goods" position="left" title="商品分类">
        <ul id="goodsTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center" title="分类属性">
        <%--<div id="maingrid"></div>--%>
        <ul class="attrEditContent fy-iconfont" style="display: none;">
            <li class="attr_1">
                <ul class="attr_child">
                    <li class="attr_typename attr_1_typename">
                        <div data-id="0">
                            <div class="tent">
                                <span class="text"> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                            </div>
                        </div>
                    </li>
                    <li class="attr_child_content">
                        <div class="attr_child_name attr_1_child_1">
                            <div class="tent">
                                <span class="text"> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_2">
                            <div class="tent">
                                <span class="text heid"></span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_3">
                            <div class="tent">
                                <span class="text heid"></span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_4">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_5">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_6">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_7">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_8">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_9">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_1_child_10">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
            <li class="attr_2">
                <ul class="attr_child">
                    <li class="attr_typename attr_2_typename">
                        <div data-id="1">
                            <div class="tent">
                                <span class="text"> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                            </div>
                        </div>
                    </li>
                    <li class="attr_child_content">
                        <div class="attr_child_name attr_2_child_1">
                            <div class="tent">
                                <span class="text"> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_2">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_3">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_4">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_5">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_6">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_7">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_8">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_9">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_2_child_10">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
            <li class="attr_3">
                <ul class="attr_child">
                    <li class="attr_typename attr_3_typename">
                        <div data-id="2">
                            <div class="tent">
                                <span class="text"> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                            </div>
                        </div>
                    </li>
                    <li class="attr_child_content">
                        <div class="attr_child_name attr_3_child_1">
                            <div class="tent">
                                <span class="text"> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_2">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_3">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_4">
                            <div class="tent">
                                <span class="text heid"> </span>
                                <input type="text" class="edit_input">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_5">
                            <div class="tent">
                                <span class="text "> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_6">
                            <div class="tent">
                                <span class="text "> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_7">
                            <div class="tent">
                                <span class="text "> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_8">
                            <div class="tent">
                                <span class="text "> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_9">
                            <div class="tent">
                                <span class="text "> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                        <div class="attr_child_name attr_3_child_10">
                            <div class="tent">
                                <span class="text"> </span>
                                <input type="text" class="edit_input heid">
                            </div>
                            <div class="icons">
                                <i data-rights="EDIT" class="delete_i edit fy-icon-edit"></i>
                                <i data-rights="DELETE" class="delete_i fy-icon-close"></i>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div id="addGoodsform" class="liger-form">
    <span style="display: inline-block;width: 86px;">所属分类</span>
    <div class="l-text-wrapper" style="display: inline-block;vertical-align: middle;">
        <div class="l-text l-text-combobox" style="width: 180px;">
            <input id="addParentId" type="text">
        </div>
    </div>
    <div class="fields">
        <input data-type="text" data-label="名称" data-name="name"/>
        <input data-type="int" data-label="排序" data-name="sort"/>
    </div>
</div>
<div id="updataGoodsform" class="liger-form">
    <span style="display: inline-block;width: 86px;">所属分类</span>
    <div class="l-text-wrapper" style="display: inline-block;vertical-align: middle;">
        <div class="l-text l-text-combobox" style="width: 180px;">
            <input id="updataParentId" type="text">
        </div>
    </div>
    <div class="fields">
        <input data-type="text" data-label="名称" data-name="name"/>
        <input data-type="int" data-label="排序" data-name="sort"/>
    </div>
</div>
</body>
</html>
