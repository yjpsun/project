<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.inventory.input"/></title>
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
		
		$("#idInventoryForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
	});
	function loadBookOh(){
		var materialId = $("#idInventoryMaterial").val();
		$("#idInventoryForm").find(".item_line").find("input").each(function (){
			$(this).val("");
		});
		if(materialId){
			$.post("${ctx}/json/mps-ajax!loadBookValue.do",{materialId:materialId},function(data){
				var rs = data.jsonResult;
				var bookOh = rs.bookOh;
				var bookValue = rs.bookValue;
				if(!bookOh){
					bookOh = 0;
				}
				$("#id_bookOh").val(bookOh);
				if(!bookValue){
					bookValue = 0;
				}
				$("#id_bookValue").val(bookValue);
			});
		}
	}
	
	function fillInventoryOhlp(){
		var bookOh =  $("#id_bookOh").val();
		var inventoryOh = $("#id_inventoryOh").val();
		if(!bookOh){
			bookOh = 0;
		}
		if(!inventoryOh){
			inventoryOh = 0;
		}
		$("#id_ohlp").val(inventoryOh*1-bookOh*1);
	}
	function fillInventoryValueLp(){
		var bookValue =  $("#id_bookValue").val();
		var inventoryValue = $("#id_inventoryValue").val();
		if(!bookValue){
			bookValue = 0;
		}
		if(!inventoryValue){
			inventoryValue = 0;
		}
		$("#id_valueLp").val(inventoryValue*1-bookValue*1);
	}
</script>
</head>
<body>
	<div class="content" >
		<fieldset>
			<legend><s:text name="inventory.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/inventory!save.do" method="post" id="idInventoryForm">
				<input type="text" name="inventory.id" value="${inventory.id}" style="display:none">
				<input type="text" name="inventory.version" value="${inventory.version}" style="display:none">
				<input type="text" name="flag" value="${flag}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="inventory.label.materialCode"/></div>
					<div class="item_input">
						<select required="required" onChange="loadBookOh();" name="inventory.material.id" class="select-1" id="idInventoryMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idInventoryMaterial").val("${inventory.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="inventory.label.bookOh"/></div>
					<div class="item_input"><input type="number" required="required"   id="id_bookOh"  name="inventory.bookOH" value="${inventory.bookOH}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="inventory.label.bookValue"/></div>
					<div class="item_input">
						<input type="number" required="required"  id="id_bookValue" name="inventory.bookvalue" value="${inventory.bookvalue}">
					</div>
					<div class="item_label"><s:text name="inventory.label.inventoryOh"/></div>
					<div class="item_input">
						<input type="number" required="required" id="id_inventoryOh" onBlur="fillInventoryOhlp();" name="inventory.inventoryOH" value="${inventory.inventoryOH}">
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="inventory.label.inventoryValue"/></div>
					<div class="item_input">
						<input type="number" required="required"  id="id_inventoryValue" onBlur="fillInventoryValueLp();" name="inventory.inventoryvalue" value="${inventory.inventoryvalue}">
					</div>
					<div class="item_label"><s:text name="inventory.label.ohlp"/></div>
					<div class="item_input">
						<input type="number" required="required" id="id_ohlp" name="inventory.ohlp" value="${inventory.ohlp}">
					</div>
				</div>
				
				<div class="item_line">
					<div class="item_label"><s:text name="inventory.label.valueLp"/></div>
					<div class="item_input">
						<input type="number" required="required" id="id_valueLp"  name="inventory.valueLP" value="${inventory.valueLP}">
					</div>
					<div class="item_label"><s:text name="inventory.label.noftolerance"/></div>
					<div class="item_input">
						<input type="number" required="required"  name="inventory.noftolerance" value="${inventory.noftolerance}">
					</div>
				</div>
				
				<div class="item_line">
					<div class="item_label"><s:text name="inventory.label.aoftolerance"/></div>
					<div class="item_input">
						<input type="number" required="required"  name="inventory.aoftolerance" value="${inventory.aoftolerance}">
					</div>
					<div class="item_label"><s:text name="inventory.label.inventoryTime"/></div>
					<div class="item_input">
						<input type="date" required="required"  name="inventory.inventorytime" value="${inventory.inventorytimeF2}">
					</div>
				</div>
				
				<div class="item_line">
					<div class="item_label"><s:text name="inventory.label.countPeople"/></div>
					<div class="item_input">
						<input type="text" required="required"  name="inventory.countpeople" value="${inventory.countpeople}">
					</div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/inventory.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>