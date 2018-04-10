<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流公司管理</title>
	<meta name="decorator" content="default"/>
	  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=aGf0sLjv7DOonc61fINdATGez2d3O8xB"></script>  
	  <style type="text/css">  
            #map_canvas {  
                width: 100%;  
                height: 600px;  
            }  
        </style>  
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
			
			 $(".pimg").click(function(){  
		            var _this = $(this);//将当前的pimg元素作为_this传入函数  
		            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);  
		        });  
		      
		});
	</script>
	
	<script>  
    function imgShow(outerdiv, innerdiv, bigimg, _this){  
        var src = _this.attr("src");//获取当前点击的pimg元素中的src属性  
        $(bigimg).attr("src", src);//设置#bigimg元素的src属性  
      
            /*获取当前点击图片的真实大小，并显示弹出层及大图*/  
        $("<img/>").attr("src", src).load(function(){  
            var windowW = $(window).width();//获取当前窗口宽度  
            var windowH = $(window).height();//获取当前窗口高度  
            var realWidth = this.width;//获取图片真实宽度  
            var realHeight = this.height;//获取图片真实高度  
            var imgWidth, imgHeight;  
            var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
              
            if(realHeight>windowH*scale) {//判断图片高度  
                imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
                imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
                if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
                    imgWidth = windowW*scale;//再对宽度进行缩放  
                }  
            } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
                imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
                            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
            } else {//如果图片真实高度和宽度都符合要求，高宽不变  
                imgWidth = realWidth;  
                imgHeight = realHeight;  
            }  
                    $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
              
            var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
            var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
            $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
            $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
        });  
          
        $(outerdiv).click(function(){//再次点击淡出消失弹出层  
            $(this).fadeOut("fast");  
        });  
    }  
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/huvis/delivery/carManageList">车辆列表</a></li>
		<li class="active"><a href="${ctx}/huvis/delivery/carManageDtl?id==${hvDeliveryMst.id}">车辆详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="hvDeliveryMst" action="#" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">发货日期：</label>
			<div class="controls">
				<input id="weightDate" name="weightDate" type="text" readonly="readonly" maxlength="60" class="input-xxlarge"
					value="<fmt:formatDate value="${hvDeliveryMst.outDay}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物流公司：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.logisticsNm}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货单位：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.delPlace}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货地址：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.delAddr}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货单号：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.orderId}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车牌号：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.carNo}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">司机：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.drvName}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.drvPhone}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆状态：</label>
			<div class="controls">
				<input  class="input-xxlarge" value="${hvDeliveryMst.stateNm}" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当前所在位置：</label>
			<div class="controls">
				<input class="input-xxlarge" id="position" value="" type="text" readonly/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用预警:</label>
			<div class="controls">
				<form:select path="warnYn" class="input-xxlarge" value="${hvDeliveryMst.warnYn}" disabled="true">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<c:if test="${hvDeliveryMst.warnYn}" >
		<div class="control-group">
			<label class="control-label">预警间隔时间（分钟）：</label>
			<div class="controls" >
				<form:select path="warnOptionTime" class="input-xxlarge" value="${hvDeliveryMst.warnOptionTime}" disabled="true">
					<form:options items="${fns:getDictList('warn_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警间隔距离（公里）：</label>
			<div class="controls">
				<form:select path="warnDistance" class="input-xxlarge" value="${hvDeliveryMst.warnDistance}" disabled="true">
					<form:options items="${fns:getDictList('warn_distance')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		</c:if> 
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea  path="remarks" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge"/>
			</div>
		</div>
		
		<c:if test="${hvDeliveryMst.state == 5}" >
				<div class="control-group">
					<label class="control-label">发货单：</label>
					 <img class="pimg" height="100" width="100" src="${hvDeliveryMst.imgUrl}" />
					<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
					    <div id="innerdiv" style="position:absolute;">
					        <img id="bigimg" style="border:5px solid #fff;" src="" />
					    </div>
					</div> 	
				</div>
		</c:if> 
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>产品名称</th><th>LOT</th><th>等级</th><th>数量</th><th>重量单位</th><th>重量（KG）</th><th>总重量</th></thead>
		<tbody>
			<c:forEach items="${hvDeliveryMst.hvDeliveryCarDtl}" var="hvDeliveryCarDtl">
			<tr>
				<td>${hvDeliveryCarDtl.lotNm}</td>
				<td>${hvDeliveryCarDtl.lot}</td>
				<td>${hvDeliveryCarDtl.grade}</td>
				<td>${hvDeliveryCarDtl.balCnt}</td>
				<td>${hvDeliveryCarDtl.qtyUnit}</td>
				<td>${hvDeliveryCarDtl.balWgt}</td>
				<td>${hvDeliveryCarDtl.totalQty}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>预警时间</th><th>预警地点</th><th>处理信息</th></thead>
		<tbody>
			<c:forEach items="${hvDeliveryMst.hvCarLocationDtl}" var="hvCarLocationDtl" >
			<c:if test="${hvCarLocationDtl.realWarnYn == 1}" >
				<tr>
					<td>${hvCarLocationDtl.locationTime}</td>
					<td>${hvCarLocationDtl.location}</td>
					<td>${hvCarLocationDtl.realWarnOption}</td>
				</tr>
			</c:if> 
			</c:forEach>
		</tbody>
	</table>
	
	<div id="map_canvas"></div>  
        <script>  
        var PointArr = [];
        var map = new BMap.Map('map_canvas');  
        
        $.post("${ctx}/huvis/delivery/locationList",{id:"${hvDeliveryMst.id}"},function(result){
        	for(var i=0;i<result.hvCarLocationDtl.length;i++){
        		var para = {"x":result.hvCarLocationDtl[i].x,"y":result.hvCarLocationDtl[i].y};
        		PointArr.push(para);
        		
        		if(i==result.hvCarLocationDtl.length-1){
        			showPosition(result.hvCarLocationDtl[i].x,result.hvCarLocationDtl[i].y)
        		}
        	}
        	
        	getMap();
          });
        
        function getMap(){
        	
           
            map.enableScrollWheelZoom();  
            
            
            var p = Math.ceil(PointArr.length / 2);  
            map.centerAndZoom(new BMap.Point(PointArr[p].x, PointArr[p].y), 13);  
            var driving;  
            for(var i in PointArr){  
                if(i == 0 ){  
                    addMarker(new BMap.Point(PointArr[i].x, PointArr[i].y),"终点");  
                    continue;  
                }  
                driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: false},   
                onMarkersSet:function(routes) {  
                    map.removeOverlay(routes[0].marker); //删除API自带起点  
                    map.removeOverlay(routes[1].marker); //删除API自带终点  
                }});  
                driving.search(new BMap.Point(PointArr[i-1].x, PointArr[i-1].y),   
                        new BMap.Point(PointArr[i].x, PointArr[i].y));  
                if(i == PointArr.length -1){  
                    addMarker(new BMap.Point(PointArr[i].x, PointArr[i].y),"起点");  
                }  
            }  
        }
            
            /**  
             * 标记  
             * @param {Object} point  
             */  
            function addMarker(point,name){  
                var marker = new BMap.Marker(point);  
                var label = new BMap.Label(name, {  
                    offset : new BMap.Size(20, -10)  
                });  
                marker.setLabel(label);  
                map.addOverlay(marker);  
            }  
             
           //百度地图WebAPI 坐标转地址
             function showPosition(x,y) {
                 // ak = appkey 访问次数流量有限制
                 var url = 'http://api.map.baidu.com/geocoder/v2/?ak=7b788c5ea45cc4b3ac6331a4b0643d5b&callback=?&location=' + y + ',' + x + '&output=json&pois=1';
                 $.getJSON(url, function (res) {
                     $("#position").val(res.result.addressComponent.province+res.result.addressComponent.city+res.result.addressComponent.district+res.result.addressComponent.street+res.result.addressComponent.street_number);
                 });
             }
        </script>  
        
   </form:form>
</body>
</html>