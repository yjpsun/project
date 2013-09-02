<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.stockCost.list"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$("#idstockcostForm").validator({ 
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
			<legend><s:text name="stockcost.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/stock-cost!save.do" method="post" id="idstockcostForm">
				<input type="text" name="stockCost.id" value="${stockCost.id}" style="display:none">
				<input type="text" name="stockCost.version" value="${stockCost.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="stockcost.label.materialCode"/></div>
					<div class="item_input">
						<select required="required" name="stockCost.material.id" class="select-1" id="idstockcostMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}" compu="${unit}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idstockcostMaterial").val("${stockCost.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="stockcost.label.storeLimit"/></div>
					<div class="item_input"><input type="text" name="stockCost.storeLimit" value="${stockCost.storeLimit}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="stockcost.label.ustorecost"/></div>
					<div class="item_input"><input type="number" name="stockCost.ustorecost" value="${stockCost.ustorecost}"></div>
					<div class="item_label"><s:text name="stockcost.label.beyondUSC"/></div>
					<div class="item_input"><input type="number" name="stockCost.beyondUSC" value="${stockCost.beyondUSC}"></div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/stock-cost.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>