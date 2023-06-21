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
            <small>题目管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/pages/home/home.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${pageContext.request.contextPath}/store/question?operation=list"><i class="fa fa-dashboard"></i> 题目管理</a></li>
            <li class="active">题目审核</li>
        </ol>
    </section>
    <section class="content">
        <div class="box-body">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab-form" data-toggle="tab">审核题目</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <form id="editForm" action="${ctx}/store/question?operation=edit" method="post" enctype="multipart/form-data">

                        <input type="hidden" name="id" value="${question.id}">
                        <input type="hidden" name="companyId" value="${question.companyId}">
                        <input type="hidden" name="catalogId" value="${question.catalogId}">
                        <input type="hidden" name="remark" value="${question.remark}">
                        <input type="hidden" name="subject" value="${question.subject}">
                        <input type="hidden" name="analysis" value="${question.analysis}">
                        <input type="hidden" name="type" value="${question.type}">
                        <input type="hidden" name="difficulty" value="${question.difficulty}">
                        <input type="hidden" name="isClassic" value="${question.isClassic}">
                        <input type="hidden" name="state" value="${question.state}">
                        <input type="hidden" name="reviewStatus" value="${question.reviewStatus}">

                        <div class="tab-pane active" id="tab-form">
                            <div class="row data-type">

                                <div class="col-md-2 title">所属企业</div>
                                <div class="col-md-4 data" >
                                    <select class="form-control" name="companyId" disabled="disabled">
                                        <c:forEach items="${companyList}" var="item">
                                            <option value="${item.id}" ${question.companyId eq item.id ? 'selected' : ''}>${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-md-2 title">所属类别</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="catalogId" disabled="disabled">
                                        <c:forEach items="${catalogList}" var="item">
                                            <option value="${item.id}" ${question.catalogId eq item.id ? 'selected' : ''}>${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-md-2 title rowHeight2x">题目简介</div>
                                <div class="col-md-10 data rowHeight2x">
                                    <textarea class="form-control" rows="3" name="remark" disabled="disabled">${question.remark}</textarea>
                                </div>


                                <div class="col-md-2 title rowHeight2x">题干</div>
                                <div class="col-md-10 data rowHeight2x">
                                    <textarea class="form-control" rows="3" name="subject" disabled="disabled">${question.subject}</textarea>
                                </div>

                                <%--在表单中添加文件上传向--%>
                                <div class="col-md-2 title" ${empty question.picture?"":"style='height: 250px;line-height: 250px'"}>题干图片</div>
                                <div class="col-md-10 data " ${empty question.picture?"":"style='height: 250px;'"}>
                                    <input type="file" class="form-control" placeholder="题干图片" name="picture" disabled="disabled">
                                    <c:if test="${not empty question.picture}">
                                        <img src="${pageContext.request.contextPath}/upload/${question.picture}">                            <%--隐藏域，用来提交图片的文件名称--%>
                                        <input type="hidden" name="picture" value="${question.picture}">
                                    </c:if>
                                </div>


                                <div class="col-md-2 title rowHeight2x">题目分析</div>
                                <div class="col-md-10 data rowHeight2x">
                                    <textarea class="form-control" rows="3" name="analysis" disabled="disabled">${question.analysis}</textarea>
                                </div>

                                <div class="col-md-2 title">题目类型</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="type" disabled="disabled">
                                        <option value="">请选择</option>
                                        <option value="1" ${question.type eq '1' ? 'selected' : ''}>单选题</option>
                                        <option value="2" ${question.type eq '2' ? 'selected' : ''} >多选题</option>
                                        <option value="3" ${question.type eq '3' ? 'selected' : ''}>简答题</option>
                                    </select>
                                </div>

                                <div class="col-md-2 title">难易程度</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="difficulty" disabled="disabled">
                                        <option value="">请选择</option>
                                        <option value="1" ${question.difficulty eq '1' ? 'selected' : ''}>★</option>
                                        <option value="2" ${question.difficulty eq '2' ? 'selected' : ''}>★★</option>
                                        <option value="3" ${question.difficulty eq '3' ? 'selected' : ''}>★★★</option>
                                        <option value="4" ${question.difficulty eq '4' ? 'selected' : ''}>★★★★</option>
                                        <option value="5" ${question.difficulty eq '5' ? 'selected' : ''}>★★★★★</option>
                                    </select>
                                </div>

                                <div class="col-md-2 title">是否经典</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="isClassic" disabled="disabled">
                                        <option value="">请选择</option>
                                        <option value="1" ${question.isClassic eq '1' ? 'selected' : ''}>经典题</option>
                                        <option value="0" ${question.isClassic eq '0' ? 'selected' : ''}>普通题</option>
                                    </select>
                                </div>

                                <div class="col-md-2 title">题目状态</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="state" disabled="disabled">
                                        <option value="">请选择</option>
                                        <option value="1" ${question.state eq '1' ? 'selected' : ''}>启用</option>
                                        <option value="0" ${question.state eq '0' ? 'selected' : ''}>禁用</option>
                                    </select>
                                </div>
                                <div class="col-md-2 title">审核状态</div>
                                <div class="col-md-10 data">
                                    <select class="form-control" name="reviewStatus">
                                        <option value="">请选择</option>
                                        <option value="-1" ${question.reviewStatus eq '-1' ? 'selected' : ''}>审核不通过</option>
                                        <option value="0" ${question.reviewStatus eq '0' ? 'selected' : ''}>待审核</option>
                                        <option value="1" ${question.reviewStatus eq '1' ? 'selected' : ''}>审核通过</option>
                                    </select>
                                </div>
                            </div>
                            <!--工具栏-->
                            <div class="box-tools text-center">
                                <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
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