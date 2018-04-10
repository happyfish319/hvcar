<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流公司</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/huvis/logisticsComp/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/huvis/logisticsComp/list">物流公司列表</a></li>
		<li><a href="${ctx}/huvis/logisticsComp/form">物流公司添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hvLogisticsCompMst" action="${ctx}/huvis/logisticsComp/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<li><label>物流公司：</label>
		<sys:treeselect id="office" name="office.id"  value="${hvLogisticsCompMst.office.id}" labelName="office.name" labelValue="${hvLogisticsCompMst.office.name}" 
				title="物流公司" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
		<label>简称：</label><form:input path="shortNm" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>公司名称</th><th>简称</th><th>持有车辆</th><th>联系人</th><th>联系地址</th><th>备注</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="hvLogisticsCompMst">
			<tr>
				<td>${hvLogisticsCompMst.office.name}</td>
				<td>${hvLogisticsCompMst.shortNm}</td>
				<td>${hvLogisticsCompMst.carCnt}</td>
				<td>${hvLogisticsCompMst.linkMan}</td>
				<td>${hvLogisticsCompMst.addr}</td>
				<td>${hvLogisticsCompMst.remarks}</td>
				<td>
    				<a href="${ctx}/huvis/logisticsComp/form?id=${hvLogisticsCompMst.id}">修改</a>
					<a href="${ctx}/huvis/logisticsComp/delete?id=${hvLogisticsCompMst.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>