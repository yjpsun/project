<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.order.list"/></title>
</head>
<body>
	<div class="header">
	</div>
	<div class="content" >
		<fieldset>
			<legend><s:text name="ordersum.legend.add"/></legend>
			<s:actionmessage/>
			<input type="text" name="order.id" value="${order.id}" style="display:none">
			<input type="text" name="order.version" value="${order.version}" style="display:none">
			<div class="item_line">
				<div class="item_label"><s:text name="ordersum.label.materialCode"/></div>
				<div class="item_input">
					<script>
						function sumOrder(){
							var materialId = $("#idOrderSumMaterial").val();
							var period = $("#idOrderPeriod").val();
							if(materialId && materialId != '-1'){
								$.post("${ctx}/json/mps-ajax.do",{materialId:materialId,period:period},function(data){
									var sumValue = data.jsonResult;
									$("#idOrderSumValue").val(sumValue);
								});
							}else{
								$("#idOrderSumValue").val(0);
							}
						}
					</script>
					<select class="select-1" onchange="sumOrder()" id="idOrderSumMaterial">
						<option value=""><s:text name="label.select.default"/></option>
						<s:iterator value="materialList" id="material" status="stat">
							<option value="${id}">${code}</option>
						</s:iterator>
					</select>
				</div>
			</div>
			<div class="item_line">
				<div class="item_label"><s:text name="ordersum.label.period"/></div>
				<div class="item_input">
					<select class="select-1" id="idOrderPeriod" onchange="sumOrder()" >
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
					</select>
				</div>
			</div>
			<div class="item_line">
				<div class="item_label"><s:text name="ordersum.label.sumption"/></div>
				<div class="item_input"><input type="text" id="idOrderSumValue"></div>
			</div>
		</fieldset>
	</div>
</body>
</html>