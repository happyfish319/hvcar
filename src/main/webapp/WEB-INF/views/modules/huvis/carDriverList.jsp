<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆司机管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/huvis/logisticsCar/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/huvis/logisticsCar/list">车辆司机列表</a></li>
		<li><a href="${ctx}/huvis/logisticsCar/form">车辆司机添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hvLogisticsCarDtl" action="${ctx}/huvis/logisticsCar/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			 <li><label>物流公司：</label><sys:treeselect id="office" name="office.id" value="${hvLogisticsCarDtl.office.id}" labelName="office.name" labelValue="${hvLogisticsCarDtl.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
			 <li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="drvName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>物流公司</th><th>车牌号</th><th>驾驶员姓名</th><th>驾驶员手机</th><th>车辆吨位</th><th>车辆类型</th><th>车辆状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="hvLogisticsCarDtl">
			<tr>
				<td>${hvLogisticsCarDtl.office.name}</td>
				<td>${hvLogisticsCarDtl.carNo}</td>
				<td>${hvLogisticsCarDtl.drvName}</td>
				<td>${hvLogisticsCarDtl.drvTel}</td>
				<td>${hvLogisticsCarDtl.carTon}</td>
				<td>${hvLogisticsCarDtl.carTypeNm}</td>
				<td>${hvLogisticsCarDtl.carStateNm}</td> 
				<td>
    				<a href="${ctx}/huvis/logisticsCar/form?id=${hvLogisticsCarDtl.id}">修改</a>
					<a href="${ctx}/huvis/logisticsCar/delete?id=${hvLogisticsCarDtl.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>