<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.materialStock.input"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$("#materialStockForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
	});
</script>
</head>
<body>
	<div class="header">
	</div>
	<div class="content" >
			<s:actionmessage/>
			<form action="${ctx}/mps/material-stock!save.do" method="post" id="materialStockForm">
				<input type="text" name="materialStock.id" value="${materialStock.id}" style="display:none" id="idMaterialStockId">
				<input type="text" name="materialStock.version" value="${materialStock.version}" style="display:none" id="idMaterialStockVersion">
				<div class="item_line">
					<div class="item_label"><s:text name="materialStock.label.code"/></div>
					<div class="item_input">
						<script>
							function loadStock(){
								var materialId = $("#idStockMaterial").val();
								var weekStart = $("#idStockWeekStart").val();
								if(materialId && weekStart){
									$.post("${ctx}/json/mps-ajax!findMSByMIdAndRecDate.do",{materialId:materialId,weekStart:weekStart}, function(data) {
										var jsonResult = data.jsonResult;
										if(jsonResult){
											$("#idMaterialStockId").val(jsonResult.id);
											$("#idMaterialStockVersion").val(jsonResult.version);
											$("#idMaterialStockOh").val(jsonResult.oh);
											$("#idMaterialStockAl").val(jsonResult.al);
										}else{
											$("#idMaterialStockId").val("");
											$("#idMaterialStockVersion").val("");
											$("#idMaterialStockOh").val("");
											$("#idMaterialStockAl").val("");
										}
									});
								}
							}
							
							function loadPeriod(){
								var materialId = $("#idStockMaterial").val();
								if(materialId){
									$.post("${ctx}/json/mps-ajax!findPeriodByMatId.do",{materialId:materialId}, function(data) {
										var jsonResult = data.jsonResult;
										$("#idStockWeekStart").empty();
										$("#idStockWeekStart").append("<option value=''><s:text name='label.select.default'/></option>");
										if(jsonResult){
											for(var i=0;i<jsonResult.length;i++){
												$("#idStockWeekStart").append("<option value='"+jsonResult[i].priodDateF2+"'>"+jsonResult[i].priodDateF1+"</option>");
											}
										}
									});
								}
							}
						</script>
						<select onchange="loadPeriod()" required="required" name="materialStock.material.id" class="select-1" id="idStockMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idStockMaterial").val("${materialStock.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="materialStock.label.weekStart"/></div>
					<div class="item_input">
						<select onchange="loadStock()" required="required" name="materialStock.weekStart" class="select-1" id="idStockWeekStart">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="curPeriodList" id="weekPriod" status="stat">
								<option value="${priodDateF2}">${priodDateF1}</option>
							</s:iterator>
						</select>
						<script>
							$("#idStockWeekStart").val("${materialStock.weekStartF2}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="materialStock.label.oh"/></div>
					<div class="item_input"><input name="materialStock.oh" value="${materialStock.oh}" type="number" required="required" id="idMaterialStockOh"></div>
					<div class="item_label"><s:text name="materialStock.label.al"/></div>
					<div class="item_input"><input name="materialStock.al" value="${materialStock.al}" type="number" required="required" id="idMaterialStockAl"></div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal" id="materialStockSubmitBtn">
				</div>
			</form>
	</div>
</body>
</html>