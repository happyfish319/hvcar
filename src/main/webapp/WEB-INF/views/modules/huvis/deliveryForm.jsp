<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>发货单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/huvis/delivery/list">配送列表</a></li>
		<li class="active"><a href="${ctx}/huvis/delivery/form?id=${hvDeliveryMst.id}">配送单修改</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="hvDeliveryMst" action="${ctx}/huvis/delivery/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">发货单号：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.orderId}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆序号：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.carNoCls}" type="text" readonly />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">办事处：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.plNm}" type="text" readonly />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出库类别：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.titoCd}" type="text" readonly />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">省份：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.provinceNm}" type="text" readonly />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货单位：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.delPlace}" type="text" readonly />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.empNm}" type="text" readonly />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货日期：</label>
			<div class="controls">
				<input name="outDay" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hvDeliveryMst.outDay}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货地址：</label>
			<div class="controls">
				<form:input path="delAddr" htmlEscape="false"  maxlength="500" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用预警:</label>
			<div class="controls">
				<form:select path="warnYn" class="input-medium">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警间隔时间（分钟）：</label>
			<div class="controls" >
				<form:select path="warnOptionTime" class="input-medium">
					<form:options items="${fns:getDictList('warn_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警间隔距离（公里）：</label>
			<div class="controls">
				<form:select path="warnDistance"  class="input-medium">
					<form:options items="${fns:getDictList('warn_distance')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea  path="remarks" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物流公司:</label>
			<div class="controls">
                <sys:treeselect id="office" name="logisticsCd" value="${hvDeliveryMst.logisticsCd}" labelName="office.name" labelValue="${hvDeliveryMst.logisticsNm}"
					title="部门" url="/sys/office/treeData?type=2"  notAllowSelectParent="true"/>
			</div>
		</div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>车号</th><th>司机</th><th>联系电话</th></thead>
		<tbody>
			<tr>
				<td>${hvDeliveryMst.carNo}</td>
				<td>${hvDeliveryMst.drvName}</td>
				<td>${hvDeliveryMst.drvPhone}</td>
			</tr>
		</tbody>
	</table>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>产品名称</th><th>LOT</th><th>等级</th><th>每包重量</th><th>数量</th><th>重量单位</th><th>总重量</th><th>备注</th></thead>
		<tbody>
		<c:forEach items="${hvDeliveryMst.hvDeliveryCarDtl}" var="hvDeliveryCarDtl" varStatus="status">
			<tr>
				<input type="hidden" value="${hvDeliveryCarDtl.id}" name="hvDeliveryCarDtl[${status.index}].id" />
				<td><input type="text" class="input-mini" value="${hvDeliveryCarDtl.lotNm}"    	name="hvDeliveryCarDtl[${status.index}].lotNm"	maxlength="50"/></td>
				<td><input type="text" class="input-mini" value="${hvDeliveryCarDtl.lot}"  name="hvDeliveryCarDtl[${status.index}].lot"    maxlength="50"/></td>
				<td><input type="text" class="input-mini" value="${hvDeliveryCarDtl.grade}" name="hvDeliveryCarDtl[${status.index}].grade"     maxlength="50"/></td>
				<td><input type="text" class="input-mini" value="${hvDeliveryCarDtl.balWgt}" name="hvDeliveryCarDtl[${status.index}].balWgt"    maxlength="50"/></td>
				<td><input type="text" class="input-mini" value="${hvDeliveryCarDtl.balCnt}"  name="hvDeliveryCarDtl[${status.index}].balCnt"   maxlength="50"/></td>
				<td><input type="text" class="input-mini" value="${hvDeliveryCarDtl.qtyUnit}" name="hvDeliveryCarDtl[${status.index}].qtyUnit"   maxlength="50"/></td>
				<td><input type="text" class="input-mini" value="${hvDeliveryCarDtl.totalQty}" name="hvDeliveryCarDtl[${status.index}].totalQty"   maxlength="50"/></td>
				<td><input type="text" class="input-mini" value="${hvDeliveryCarDtl.rmk}"     name="hvDeliveryCarDtl[${status.index}].rmk"   maxlength="50"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>