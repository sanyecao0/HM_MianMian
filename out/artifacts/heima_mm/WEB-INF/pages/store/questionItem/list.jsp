﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->

    <script type="text/javascript">
        function deleteById(id) {
            var questionId = '${questionId}';
            if(confirm("你确认要删除此条记录吗？")) {
                window.location.href="${ctx}/store/questionItem?operation=delete&questionId=${questionId}&id="+id;
            }
        }
    </script>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            题库管理
            <small>题目的选项</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/pages/home/home.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${pageContext.request.contextPath}/store/questionItem?operation=list"><i class="fa fa-dashboard"></i> 题库管理</a></li>
            <li class="active">选项添加及列表</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">新增选项</div>
            <form id="editForm" action="${ctx}/store/questionItem?operation=${empty questionItem.id?'save':'edit'}" method="post">
                <%--问题id要发送给服务器，修改完成之后要根据问题id重新查询选项--%>
                <input type="hidden" name="questionId" value="${questionId}">
                <%--选项的id发给服务器，服务器的修改是根据选项id修改该选项,这个值在修改的时候有用，添加的时候没有值--%>
                <input type="hidden" name="id" value="${questionItem.id}">

                <div class="row data-type" style="margin: 0px">

                    <div class="col-md-2 title">选项内容</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="选项内容" name="content" value="${questionItem.content}">
                    </div>

                    <div class="col-md-2 title">是否正确答案</div>
                    <div class="col-md-4 data">
                        <select class="form-control" name="isRight">
                            <option value="">请选择</option>
                            <option value="1" ${questionItem.isRight == "1" ? "selected" : ""}>正确答案</option>
                            <option value="0" ${questionItem.isRight == "0" ? "selected" : ""}>错误选项</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>

    <!-- 正文区域 /-->

    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">选项列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--工具栏-->
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>


                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td class="tableHeader">选项内容</td>
                            <td class="tableHeader">是否正确答案</td>
                            <td class="tableHeader">操作</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody" >
                        <c:forEach items="${questionItemList}" var="questionItem" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td>${questionItem.content}</td>
                                <td>${questionItem.isRight eq "1" ? "正确答案" : "错误答案"}</td>
                                <td>
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/questionItem?operation=toEdit&questionId=${questionId}&id=${questionItem.id}"'>编辑</button>
                                    <button type="button" class="btn bg-olive btn-xs" onclick="deleteById('${questionItem.id}')">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--数据列表/-->
                    <!--工具栏/-->
                </div>
                <!-- 数据表格 /-->
            </div>
        </div>

    </section>

</div>
<!-- 内容区域 /-->
</body>

</html>