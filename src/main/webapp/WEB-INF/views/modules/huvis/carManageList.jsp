<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/huvis/delivery/carManageList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/huvis/delivery/carManageList">车辆列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hvDeliveryMst" action="${ctx}/huvis/delivery/carManageList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>车牌号：</label><form:input path="carNo" htmlEscape="false" value="" maxlength="50" class="input-medium"/>
			   <label>发货日期：</label><input name="outDay" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hvDeliveryMst.outDay}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<label>车辆状态:</label>
				 <form:select path="state" class="input-medium">
				 	<form:option value="4" label="运输中"/>
				 	<form:option value="5" label="运输完成"/>
				</form:select> </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>发货单号</th><th>车牌号</th><th>驾驶员</th><th>驾驶员电话</th><th>收货地址</th><th>发货时间</th><th>到达时间</th><th>预警</th><th>状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="hvDeliveryMst">
			<c:choose> 
		     <c:when test="${hvDeliveryMst.state == 4 && hvDeliveryMst.warnCnt>0}" >    <!--如果 --> 
				<tr style="color:red">
			 </c:when>      
		     <c:otherwise>  <!--否则 -->    
				<tr>
		  	</c:otherwise> 
		</c:choose> 
				<td>${hvDeliveryMst.orderId}</td>
				<td>${hvDeliveryMst.carNo}</td>
				<td>${hvDeliveryMst.drvName}</td>
				<td>${hvDeliveryMst.drvPhone}</td>
				<td>${hvDeliveryMst.delAddr}</td>
				<td><fmt:formatDate value="${hvDeliveryMst.outDay}" type="both" dateStyle="full"/></td>
				<td>${hvDeliveryMst.endTime}</td>
				<td>${hvDeliveryMst.warnCnt}</td>
				<td>${hvDeliveryMst.stateNm}</td>
				<td>
    				<a href="${ctx}/huvis/delivery/carManageDtl?id=${hvDeliveryMst.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>