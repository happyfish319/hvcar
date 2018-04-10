<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收货公司</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/huvis/custComp/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/huvis/custComp/list">收货公司列表</a></li>
		<li><a href="${ctx}/huvis/custComp/form">收货公司添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hvCustCompMst" action="${ctx}/huvis/custComp/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<label>公司名称：</label><form:input path="custCompNm" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>公司名称</th><th>公司编号</th><th>简称</th><th>联系人</th><th>联系电话</th><th>联系地址</th><th>备注</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="hvCustCompMst">
			<tr>
				<td>${hvCustCompMst.custCompNm}</td>
				<td>${hvCustCompMst.custCompId}</td>
				<td>${hvCustCompMst.shortNm}</td>
				<td>${hvCustCompMst.linkMan}</td>
				<td>${hvCustCompMst.tel1}</td>
				<td>${hvCustCompMst.addr}</td>
				<td>${hvCustCompMst.remarks}</td>
				<td>
    				<a href="${ctx}/huvis/custComp/form?id=${hvCustCompMst.id}">修改</a>
					<a href="${ctx}/huvis/custComp/delete?id=${hvCustCompMst.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>