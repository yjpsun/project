<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.purchaseOrder.list"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$(":date").dateinput({
			format: 'yyyymmdd',	// the format displayed for the user
			selectors: true,             	// whether month/year dropdowns are shown
			min: -1,                    // min selectable day (100 days backwards)
			offset: [5, 10],            	// tweak the position of the calendar
			speed: 300,               	// calendar reveal speed
			firstDay: 1                  	// which day starts a week. 0 = sunday, 1 = monday etc..
		});
		
		$("#idPurchaseOrderForm").validator({ 
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
			<legend><s:text name="purchaseOrder.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/purchase-order!save.do" method="post" id="idPurchaseOrderForm">
				<input type="text" name="purchaseOrder.id" value="${purchaseOrder.id}" style="display:none">
				<input type="text" name="purchaseOrder.version" value="${purchaseOrder.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="purchaseOrder.label.materialCode"/></div>
					<div class="item_input">
						<select required="required" name="purchaseOrder.material.id" class="select-1" id="idPurchaseOrderMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idPurchaseOrderMaterial").val("${purchaseOrder.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="purchaseOrder.label.demDate"/></div>
					<div class="item_input">
						<input type="date" name="purchaseOrder.demDate" value="${purchaseOrder.demDateF2}" required="required" >
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="purchaseOrder.label.purDate"/></div>
					<div class="item_input"><input type="date" name="purchaseOrder.purDate" value="${purchaseOrder.purDateF2}" required="required"></div>
					<div class="item_label"><s:text name="purchaseOrder.label.del"/></div>
					<div class="item_input"><input type="date" name="purchaseOrder.delDate" value="${purchaseOrder.delDateF2}" required="required"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="purchaseOrder.label.sumption"/></div>
					<div class="item_input"><input type="number" required="required" type="text" name="purchaseOrder.sumption" value="${purchaseOrder.sumption}"></div>
					<div class="item_label"><s:text name="purchaseOrder.label.purQ"/></div>
					<div class="item_input"><input type="number" name="purchaseOrder.purQ" value="${purchaseOrder.purQ}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="purchaseOrder.label.unitP"/></div>
					<div class="item_input"><input type="number" required="required" type="text" name="purchaseOrder.unitP" value="${purchaseOrder.unitP}"></div>
					<div class="item_label"><s:text name="purchaseOrder.displaytag.slsr"/></div>
					<div class="item_input">
						<select name="purchaseOrder.slsr" class="select-1" id="idMaterialMatLsr" required="required">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="LFL">LFL</option>
							<option value="POQ">POQ</option>
							<option value="FOQ">FOQ</option>
						</select>
						<script>
							$("#idMaterialMatLsr").val("${purchaseOrder.slsr}");
						</script>
					</div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/purchase-order.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>