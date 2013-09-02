<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.material.input"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$("#materialForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
	});
	
	function checkMaterialCode(){
		var materialCode = $("#idMaterialCode").val();
		var materialCodeOrg = $("#idMaterialCodeOrg").val();
		var materialId = $("#idMaterialId").val();
		
		if(materialCode){
			$.post("${ctx}/json/mps-ajax!checkMaterialCode.do",{materialCode:materialCode,materialCodeOrg:materialCodeOrg,materialId:materialId}, function(data) {
				var jsonResult = data.jsonResult;
				$("#idCheckMaterialCodeRs").remove();
				var hasMsg = $(".actionMessage");
				var height = "150.4px";
				if(hasMsg.length > 0){
					height = "180px";
				}
				if(jsonResult != "notExist"){
					$("body").append("<div id='idCheckMaterialCodeRs' class='error' style='visibility: visible; position: absolute; top: "+height+"; left: 403px;'><em></em><p>Material Code "+materialCode+" is already exist</p></div>");
					$("#materialSubmitBtn").attr("disabled","disabled");
					
				}else{
					$("#idCheckMaterialCodeRs").remove();
					$("#materialSubmitBtn").removeAttr("disabled");
				}
			});
		}
	}
</script>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/material!input.do"><s:text name="material.add.btn"/></a>
		</div>
	</div>
	<div class="content" >
		<fieldset>
			<legend><s:text name="material.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/material!save.do" method="post" id="materialForm">
				<input type="text" name="material.id" value="${material.id}" style="display:none" id="idMaterialId">
				<input type="text" name="material.version" value="${material.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="material.label.code"/></div>
					<div class="item_input">
						<input onblur="checkMaterialCode();" type="text" id="idMaterialCode" name="material.code" value="${material.code}" pattern="[a-zA-Z0-9]{5}">
						<input type="text" id="idMaterialCodeOrg" name="material.oldCode" value="${material.code}" style="display:none">
					</div>
					<div class="item_label"><s:text name="material.label.computation"/></div>
					<div class="item_input">
						<select name="material.computation" class="select-1" id="idMaterialComputation" required="required">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="kg">kg</option>
							<option value="g">g</option>
							<option value="L">L</option>
						</select>
						<script>
							$("#idMaterialComputation").val("${material.computation}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="material.label.ps"/></div>
					<div class="item_input">
						<select name="material.matPs" class="select-1" id="idMaterialPs">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="Y">Y</option>
							<option value="N">N</option>
						</select>
						<script>
							$("#idMaterialPs").val("${material.matPs}");
						</script>
					</div>
					<div class="item_label"><s:text name="material.label.lsr"/></div>
					<div class="item_input">
						<select name="material.matLsr" class="select-1" id="idMaterialLsr" required="required">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="LFL">LFL</option>
							<option value="POQ">POQ</option>
							<option value="FOQ">FOQ</option>
						</select>
						<script>
							$("#idMaterialLsr").val("${material.matLsr}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="material.label.ls"/></div>
					<div class="item_input"><input type="text" name="material.matLs" value="${material.matLs}"></div>
					<div class="item_label"><s:text name="material.label.structureID"/></div>
					<div class="item_input"><input type="text" name="material.structureId" value="${material.structureId}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="material.label.sourceOrigin"/></div>
					<div class="item_input">
						<select name="material.sourceOrigin" class="select-1" id="idMaterialSourceOrigin">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="purchase"><s:text name="label.select.sourceOrigin.purchase"/></option>
							<option value="product"><s:text name="label.select.sourceOrigin.product"/></option>
							<option value="outsource"><s:text name="label.select.sourceOrigin.outsource"/></option>
						</select>
						<script>
							$("#idMaterialSourceOrigin").val("${material.sourceOrigin}");
						</script>
					</div>
					<div class="item_label"><s:text name="material.label.lt"/></div>
					<div class="item_input"><input type="text" name="material.matLt" value="${material.matLt}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="material.label.virtualMat"/></div>
					<div class="item_input"><input type="text" name="material.virtualMat" value="${material.virtualMat}"></div>
					<div class="item_label"><s:text name="material.label.ss"/></div>
					<div class="item_input"><input type="text" name="material.matSs" value="${material.matSs}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="material.label.minPur"/></div>
					<div class="item_input"><input type="text" name="material.minpur" value="${material.minpur}"></div>
					<div class="item_label"><s:text name="material.label.maxPur"/></div>
					<div class="item_input"><input type="text" name="material.maxpur" value="${material.maxpur}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="material.label.purInt"/></div>
					<div class="item_input"><input type="text" name="material.purInt" value="${material.purInt}"></div>
					<div class="item_label"><s:text name="material.label.mul"/></div>
					<div class="item_input"><input type="text" name="material.mul" value="${material.mul}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="material.label.yield"/></div>
					<div class="item_input"><input type="text" name="material.yield" value="${material.yield}"></div>
					<div class="item_label"><s:text name="material.label.betwDemDate"/></div>
					<div class="item_input"><input type="text" name="material.betwDemDate" value="${material.betwDemDate}"></div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal" id="materialSubmitBtn">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/material.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>