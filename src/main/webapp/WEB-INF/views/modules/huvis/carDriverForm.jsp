<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				/* rules: {
					carNo: {remote: "${ctx}/huvis/logisticsCar/checkCarNo?oldCarNo=" + encodeURIComponent('${hvLogisticsCarDtl.carNo}')}
				},
				messages: {
					carNo: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				}, */
				rules: {
					drvTel : {  
			            //required : true,  
			            minlength : 11,  
			            // 自定义方法：校验手机号在数据库中是否存在  
			            // checkPhoneExist : true,  
			            isMobile : true  
			        }
				}, 
		        messages : {  
		        	drvTel : {  
	               // required : "请输入手机号",  
	                minlength : "确认手机不能小于11个字符",  
	                isMobile : "请正确填写您的手机号码"  
	            }
	        }, 
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
		<li><a href="${ctx}/huvis/logisticsCar/list">车辆司机列表</a></li>
		 <li class="active"><a href="${ctx}/huvis/logisticsCar/form?id=${hvLogisticsCarDtl.id}">车辆司机${not empty hvLogisticsCarDtl.id?'修改':'添加'}</a></li> 
	</ul><br/>
	<form:form id="inputForm" modelAttribute="hvLogisticsCarDtl" action="${ctx}/huvis/logisticsCar/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">物流公司:</label>
			<div class="controls">
                <sys:treeselect id="office" name="office.id" value="${hvLogisticsCarDtl.office.id}" labelName="office.name" labelValue="${hvLogisticsCarDtl.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车牌号:</label>
			<div class="controls">
				<form:input path="carNo" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">司机姓名:</label>
			<div class="controls">
				<form:input path="drvName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="drvTel" htmlEscape="false" maxlength="11" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">驾驶证号码:</label>
			<div class="controls">
				<form:input path="drvId" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">驾驶证类型:</label>
			<div class="controls">
				<form:select path="drvType" class="input-medium">
					<form:options items="${fns:getDictList('drv_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆吨位:</label>
			<div class="controls">
				<form:input path="carTon" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆类型:</label>
			<div class="controls">
				<form:select path="carType" class="input-medium">
					<form:options items="${fns:getDictList('car_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆状态:</label>
			<div class="controls">
				<form:select path="carState" class="input-medium">
					<form:options items="${fns:getDictList('car_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年检时间:</label>
			<div class="controls">
				<input name="checkDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hvLogisticsCarDtl.checkDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可以使用:</label>
			<div class="controls">
				<form:select path="useYn">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="2000" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>