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
                系统管理
                <small>部门管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/pages/home/home.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/system/dept?operation=list"><i class="fa fa-dashboard"></i> 部门管理</a></li>
                <li class="active">部门表单</li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">编辑部门</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/system/dept?operation=save" method="post">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">部门名称</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="部门名称" name="name" value="${dept.name}">
                                    </div>
                                    <div class="col-md-2 title">所属部门</div>
                                    <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="parentId">
                                            <option value="">请选择</option>
                                            <c:forEach items="${deptList}" var="item">
                                                <option ${dept.parent.id == item.id ?'selected':''} value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2 title">状态</div>
                                    <div class="col-md-10 data">
                                        <div class="form-group form-inline">
                                            <div class="radio"><label><input type="radio" ${dept.state==0?'checked':''} name="state" value="0">停用</label></div>
                                            <div class="radio"><label><input type="radio" ${dept.state==1?'checked':''} name="state" value="1">启用</label></div>
                                        </div>
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