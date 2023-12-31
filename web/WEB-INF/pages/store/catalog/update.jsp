<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            题库管理
            <small>题目类型管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/pages/home/home.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${pageContext.request.contextPath}/store/catalog?operation=list"><i class="fa fa-dashboard"></i> 题目类型管理</a></li>
            <li class="active">题目类型表单</li>
        </ol>
    </section>
    <section class="content">
        <div class="box-body">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab-form" data-toggle="tab">编辑目录</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <form id="editForm" action="${ctx}/store/catalog?operation=edit" method="post">
                        <input type="hidden" name="id" value="${catalog.id}">
                        <input type="hidden" id="courseName" name="courseName" value="${catalog.course.name}">
                        <div class="tab-pane active" id="tab-form">
                            <div class="row data-type">
                                <div class="col-md-2 title">所属学科</div>
                                <div class="col-md-10 data line-height36">
                                    <select class="form-control" name="courseId" onchange="document.getElementById('courseName').value=this.options[this.selectedIndex].text">
                                        <option value="">请选择</option>
                                        <c:forEach items="${courseList}" var="item">
                                            <option ${catalog.courseId == item.id ?'selected':''} value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-2 title">目录名称</div>
                                <div class="col-md-10 data">
                                    <input type="text" class="form-control" placeholder="目录名称" name="name" value="${catalog.name}">
                                </div>
                                <div class="col-md-2 title">目录简介</div>
                                <div class="col-md-10 data line-height36">
                                    <input type="text" class="form-control" placeholder="目录简介" name="remark" value="${catalog.remark}">
                                </div>
                                <div class="col-md-2 title">状态</div>
                                <div class="col-md-10 data line-height36">
                                    <select class="form-control" name="state">
                                        <option value="">请选择</option>
                                        <option value="1" ${catalog.state == "1" ? 'selected':''}>启用</option>
                                        <option value="0" ${catalog.state == "0" ? 'selected':''}>禁用</option>
                                    </select>
                                </div>
                                <div class="col-md-2 title"></div>
                                <div class="col-md-10 data text-center">
                                    <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>

</html>


