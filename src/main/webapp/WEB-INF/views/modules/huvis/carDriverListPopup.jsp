<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆司机管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var orderId = '<%= request.getParameter("orderId") %>';
	var checkId = '';
		$(document).ready(function() {
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/huvis/logisticsCar/carDriverListPopup");
			$("#searchForm").submit();
	    	return false;
	    }
		function getCheckId(id){
				checkId = id;
		}
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="hvLogisticsCarDtl" action="${ctx}/huvis/logisticsCar/carDriverListPopup" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			 <li><label>所属公司：</label><sys:treeselect id="office" name="office.id" value="${hvLogisticsCarDtl.office.id}" labelName="office.name" labelValue="${hvLogisticsCarDtl.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
			 <li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="drvName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<input id = "carId" type="hidden" />
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>选择</th><th>物流公司</th><th>车牌号</th><th>驾驶员姓名</th><th>驾驶员手机</th><th>车辆吨位</th><th>车辆类型</th><th>车辆状态</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="hvLogisticsCarDtl">
			<tr>
				<td><input type="radio" name="checkId"  onclick="getCheckId('${hvLogisticsCarDtl.id}');" ></td>
				<td>${hvLogisticsCarDtl.office.name}</td>
				<td>${hvLogisticsCarDtl.carNo}</td>
				<td>${hvLogisticsCarDtl.drvName}</td>
				<td>${hvLogisticsCarDtl.drvTel}</td>
				<td>${hvLogisticsCarDtl.carTon}</td>
				<td>${hvLogisticsCarDtl.carType}</td>
				<td>${hvLogisticsCarDtl.carState}</td> 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>