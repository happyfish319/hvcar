<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>派车管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/huvis/delivery/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/huvis/delivery/carStateList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/huvis/delivery/carStateList">派车列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hvDeliveryMst" action="${ctx}/huvis/delivery/carStateList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<%-- <li><label>归属公司：</label><sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" 
				title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/></li>
			<li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="clearfix"></li>
			<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li> 
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>--%>
			<li><label>车牌号：</label><form:input path="carNo" htmlEscape="false" maxlength="50" class="input-medium"/>
				<label>车辆状态:</label>
				 <form:select path="state" class="input-medium">
				 	<form:option value="2" label="待进厂"/>
				 	<form:option value="3" label="待出厂"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>发货单号</th><th>车牌号</th><th>驾驶员</th><th>驾驶员电话</th><th>收货地址</th><th>进厂时间</th><th>出厂时间</th><th>状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="hvDeliveryMst">
			<tr>
				<td>${hvDeliveryMst.orderId}</td>
				<td>${hvDeliveryMst.carNo}</td>
				<td>${hvDeliveryMst.drvName}</td>
				<td>${hvDeliveryMst.drvPhone}</td>
				<td>${hvDeliveryMst.delAddr}</td>
				<td>${hvDeliveryMst.factoryInTime}</td>
				<td>${hvDeliveryMst.factoryOutTime}</td>
				<td>${hvDeliveryMst.stateNm}</td>
				<td>
				<c:if test="${hvDeliveryMst.state == '2'}">
				<a href="${ctx}/huvis/delivery/carStateSave?id=${hvDeliveryMst.id}&state=3">进厂</a>
				</c:if>
				<c:if test="${hvDeliveryMst.state == '3'}">
				<a href="${ctx}/huvis/delivery/carStateSave?id=${hvDeliveryMst.id}&state=4">出厂</a>
				</c:if></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>