<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配送列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出发货单数据吗？","系统提示",function(v,h,f){
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
			$("#searchForm").attr("action","${ctx}/huvis/delivery/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/huvis/delivery/list">配送列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hvDeliveryMst" action="${ctx}/huvis/delivery/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>发货单号：</label><form:input path="orderId" htmlEscape="false" maxlength="50" class="input-medium"/>
			<label>发货日期：</label><input name="outDay" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hvDeliveryMst.outDay}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>发货单号</th><th>发货时间</th><th>办事处</th><th>出库类别</th><th>序号</th><th>计划件数</th><th>车号</th><th>司机姓名</th>
		<th>手机电话</th><th>省份</th><th>地址</th><th>收货单位</th><th>批号</th><th>LOT</th><th>型号</th><th>等级</th><th>每包重量（kg)</th>
		<th>包数</th><th>总重量(T)</th><th>运输公司</th><th>重量(T)</th><th>单价(元/吨)</th><th>合计(元)</th><th>产品运费</th><th>销售</th><th>备注</th></tr></thead>
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
    				<a href="${ctx}/huvis/delivery/form?id=${hvDeliveryMst.id}">修改</a>
    				<a href="${ctx}/huvis/delivery/delete?id=${hvDeliveryMst.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>