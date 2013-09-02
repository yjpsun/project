<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.order.input"/></title>
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
		
		$("#idOrderForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
	});
</script>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/order!input.do"><s:text name="order.add.btn"/></a>
		</div>
	</div>
	<div class="content" >
		<fieldset>
			<legend><s:text name="timegrating.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/order!save.do" method="post" id="idOrderForm">
				<input type="text" name="order.id" value="${order.id}" style="display:none">
				<input type="text" name="order.version" value="${order.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="order.label.materialCode"/></div>
					<div class="item_input">
						<script>
							function changeComputation(){
								var computation = $("#idOrderMaterial option:selected").attr("compu");
								$("#idOrderComputation").val(computation);
							}
						</script>
						<select required="required" name="order.material.id" class="select-1" onchange="changeComputation()" id="idOrderMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}" compu="${computation}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idOrderMaterial").val("${order.material.id}");
						</script>
						
					</div>
					<div class="item_label"><s:text name="order.label.computation"/></div>
					<div class="item_input">
						<select name="order.computation" required="required" class="select-1" id="idOrderComputation">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="kg">kg</option>
							<option value="g">g</option>
							<option value="L">L</option>
						</select>
						<script>
							$("#idOrderComputation").val("${order.computation}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="order.label.orderQ"/></div>
					<div class="item_input"><input type="number" required="required" type="text" name="order.quantity" value="${order.quantity}"></div>
					<div class="item_label"><s:text name="order.label.demDate"/></div>
					<div class="item_input">
						<input type="date" name="order.demDate" value="${order.demDateF2}" required="required" >
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="order.label.recRej"/></div>
					<div class="item_input"><input type="text" name="order.recRej" value="${order.recRej}"></div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/order.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>