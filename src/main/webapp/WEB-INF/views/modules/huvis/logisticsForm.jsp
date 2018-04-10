<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流公司管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					tel1 : {  
			           // required : true,  
			            minlength : 11,  
			            // 自定义方法：校验手机号在数据库中是否存在  
			            // checkPhoneExist : true,  
			            isMobile : true  
			        },  
			        tel1 : {  
			            //required : true,  
			            minlength : 11,  
			            // 自定义方法：校验手机号在数据库中是否存在  
			            // checkPhoneExist : true,  
			            isMobile : true  
			        }
			        ,"office.name" : {  
			            required : true,  
			        }
			}, 
	        messages : {  
	        	tel1 : {  
	               // required : "请输入手机号",  
	                minlength : "确认手机不能小于11个字符",  
	                isMobile : "请正确填写您的手机号码"  
	            },
	            tel2 : {  
	               // required : "请输入手机号",  
	                minlength : "确认手机不能小于11个字符",  
	                isMobile : "请正确填写您的手机号码"  
	            }
	            ,
	            "office.name" : {  
	                required : "请选择物流公司",  
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
		<li><a href="${ctx}/huvis/logisticsComp/list">物流公司列表</a></li>
		<li class="active"><a href="${ctx}/huvis/logisticsComp/form?id=${hvLogisticsCompMst.id}">物流公司${not empty hvLogisticsCompMst.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="hvLogisticsCompMst" action="${ctx}/huvis/logisticsComp/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<!-- <div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
                <input  value="物流公司" class="input-large" type="text" readonly />
			</div>
		</div> -->
		<div class="control-group">
			<label class="control-label">物流公司:</label>
			<div class="controls">
                <sys:treeselect id="office" name="office.id" value="${hvLogisticsCompMst.office.id}" labelName="office.name" labelValue="${hvLogisticsCompMst.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">简称：</label>
			<div class="controls">
				<form:input path="shortNm" htmlEscape="false"  maxlength="50" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="linkMan" htmlEscape="false"  maxlength="50" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话1：</label>
			<div class="controls">
				<form:input path="tel1" htmlEscape="false"  maxlength="11" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话2：</label>
			<div class="controls">
				<form:input path="tel2" htmlEscape="false"  maxlength="11" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系地址：</label>
			<div class="controls">
				<form:input path="addr" htmlEscape="false" maxlength="500" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge"/>
			</div>
		</div>
		
		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>