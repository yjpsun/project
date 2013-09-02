<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.outStock.list"/></title>
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
		
		$("#idOutStockForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
		
		var type = $("#idOutStockType").val();
		if("outPlan" == type){
			$("#poutQID").css("display","none");
		}
		if("inPlan" == type){
			$("#poutQID").css("display","");
		}
	});
	
	function changeType(){
		var type = $("#idOutStockType").val();
		if("outPlan" == type){
			$("#poutQID").css("display","none");
		}
		if("inPlan" == type){
			$("#poutQID").css("display","");
		}
	}
</script>
</head>
<body>
	<div class="content" >
		<fieldset>
			<legend><s:text name="outstock.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/out-stock!save.do" method="post" id="idOutStockForm">
				<input type="text" name="outStock.id" value="${outStock.id}" style="display:none">
				<input type="text" name="outStock.version" value="${outStock.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="outstock.label.materialCode"/></div>
					<div class="item_input">
						<select required="required" name="outStock.material.id" class="select-1" id="idOutStockMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idOutStockMaterial").val("${outStock.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="outstock.label.type"/></div>
					<div class="item_input">
						<select name="outStock.outStockType" onchange="changeType();" class="select-1" id="idOutStockType" required="required">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="outPlan">计划外</option>
							<option value="inPlan">计划内</option>
						</select>
						<script>
							$("#idOutStockType").val("${outStock.outStockType}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="outstock.label.outDate"/></div>
					<div class="item_input">
						<input type="date" name="outStock.outDate" value="${outStock.outDateF2}" required="required" >
					</div>
					<div class="item_label"><s:text name="outstock.label.routq"/></div>
					<div class="item_input">
						<input type="number" name="outStock.routQ" value="${outStock.routQ}" required="required" >
					</div>
				</div>
				<div class="item_line" id="poutQID">
					<div class="item_label"><s:text name="outstock.label.poutq"/></div>
					<div class="item_input">
						<input type="number" name="outStock.poutQ" value="${outStock.poutQ}">
					</div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/out-stock.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>