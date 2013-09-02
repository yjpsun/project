<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.supplyer.input"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$("#idSupplyerForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
	});
</script>
</head>
<body>
	<div class="content" >
		<fieldset>
			<legend><s:text name="supplyer.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/supplyer!save.do" method="post" id="idSupplyerForm">
				<input type="text" name="supplyer.id" value="${supplyer.id}" style="display:none">
				<input type="text" name="supplyer.version" value="${supplyer.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="supplyer.label.materialCode"/></div>
					<div class="item_input">
						<select required="required" name="supplyer.material.id" class="select-1" id="idSupplyerMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idSupplyerMaterial").val("${supplyer.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="supplyer.label.slsr"/></div>
					<div class="item_input">
						<select name="supplyer.slsr" class="select-1" id="idMaterialMatLsr" required="required">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="LFL">LFL</option>
							<option value="POQ">POQ</option>
							<option value="FOQ">FOQ</option>
						</select>
						<script>
							$("#idMaterialMatLsr").val("${supplyer.slsr}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="supplyer.label.sls"/></div>
					<div class="item_input"><input type="text" name="supplyer.sls" value="${supplyer.sls}"></div>
					<div class="item_label"><s:text name="supplyer.label.unitP"/></div>
					<div class="item_input"><input type="text" name="supplyer.unitP" value="${supplyer.unitP}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="supplyer.label.st"/></div>
					<div class="item_input"><input type="number" required="required" type="text" name="supplyer.st" value="${supplyer.st}"></div>
					<div class="item_label"><s:text name="supplyer.label.maxS"/></div>
					<div class="item_input"><input type="number" name="supplyer.maxS" value="${supplyer.maxS}"></div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/supplyer.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>