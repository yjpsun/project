<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.materialDelivering.input"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$("#materialDeliveringForm").validator({ 
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
			<form action="${ctx}/mps/material-delivering!save.do" method="post" id="materialDeliveringForm">
				<input type="text" name="materialDelivering.id" value="${materialDelivering.id}" style="display:none" id="idMaterialDeliveringId">
				<input type="text" name="materialDelivering.version" value="${materialDelivering.version}" style="display:none" id="idMaterialDeliveringVersion">
				<div class="item_line">
					<div class="item_label"><s:text name="materialDelivering.label.code"/></div>
					<div class="item_input">
						<script>
							function loadDelivering(){
								var materialId = $("#idDeliveringMaterial").val();
								var recDate = $("#idDeliveringRecDate").val();
								if(materialId && recDate){
									$.post("${ctx}/json/mps-ajax!findMDByMIdAndRecDate.do",{materialId:materialId,recDate:recDate}, function(data) {
										var jsonResult = data.jsonResult;
										if(jsonResult){
											$("#idMaterialDeliveringId").val(jsonResult.id);
											$("#idMaterialDeliveringVersion").val(jsonResult.version);
											$("#idMaterialDeliveringSr").val(jsonResult.sr);
											$("#idMaterialDeliveringMatSr").val(jsonResult.matSr);
										}else{
											$("#idMaterialDeliveringId").val("");
											$("#idMaterialDeliveringVersion").val("");
											$("#idMaterialDeliveringSr").val("");
											$("#idMaterialDeliveringMatSr").val("");
										}
									});
								}
							}
							function loadPeriod(){
								var materialId = $("#idDeliveringMaterial").val();
								if(materialId){
									$.post("${ctx}/json/mps-ajax!findPeriodByMatId.do",{materialId:materialId}, function(data) {
										var jsonResult = data.jsonResult;
										$("#idDeliveringRecDate").empty();
										$("#idDeliveringRecDate").append("<option value=''><s:text name='label.select.default'/></option>");
										if(jsonResult){
											for(var i=0;i<jsonResult.length;i++){
												$("#idDeliveringRecDate").append("<option value='"+jsonResult[i].priodDateF2+"'>"+jsonResult[i].priodDateF1+"</option>");
											}
										}
									});
								}
							}
						</script>
						<select onchange="loadPeriod()" required="required" name="materialDelivering.material.id" class="select-1" id="idDeliveringMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idDeliveringMaterial").val("${materialDelivering.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="materialDlivering.label.recDate"/></div>
					<div class="item_input">
						<select onchange="loadDelivering()" required="required" name="materialDelivering.recDate" class="select-1" id="idDeliveringRecDate">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="curPeriodList" id="weekPriod" status="stat">
								<option value="${priodDateF2}">${priodDateF1}</option>
							</s:iterator>
						</select>
						<script>
							$("#idDeliveringRecDate").val("${materialDelivering.recDateF2}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="materialDlivering.label.sr"/></div>
					<div class="item_input"><input name="materialDelivering.sr" value="${materialDelivering.sr}" type="number" required="required" id="idMaterialDeliveringSr"></div>
					<div class="item_label"><s:text name="materialDlivering.label.matSr"/></div>
					<div class="item_input"><input name="materialDelivering.matSr" value="${materialDelivering.matSr}" type="number" required="required" id="idMaterialDeliveringMatSr"></div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal" id="materialDeliveringSubmitBtn">
				</div>
			</form>
	</div>
</body>
</html>