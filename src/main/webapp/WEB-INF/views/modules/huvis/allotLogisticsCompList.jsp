<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配送列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/huvis/delivery/allotCompList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/huvis/delivery/allotCompList">配送列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hvDeliveryMst" action="${ctx}/huvis/delivery/allotCompList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>发货单号：</label><form:input path="orderId" htmlEscape="false" maxlength="50" class="input-medium"/>
			<label>发货日期：</label><input name="outDay" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hvDeliveryMst.outDay}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>发货单号</th><th>申请日期</th><th>物流公司</th><th>收货单位</th><th>收货地址</th><th>支社</th><th>状态</th><th>备注</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="hvDeliveryMst">
			<tr>
				<td>${hvDeliveryMst.orderId}</td>
				<td><fmt:formatDate value="${hvDeliveryMst.outDay}" type="both" dateStyle="full"/></td>
				<td>${hvDeliveryMst.logisticsNm}</td>
				<td>${hvDeliveryMst.delPlace}</td>
				<td>${hvDeliveryMst.delAddr}</td>
				<td>${hvDeliveryMst.plNm}</td>
				<td>${hvDeliveryMst.stateNm}</td>
				<td>${hvDeliveryMst.remarks}</td>
				<td>
    				<a href="${ctx}/huvis/delivery/allotCompForm?id=${hvDeliveryMst.id}">分配物流公司</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>