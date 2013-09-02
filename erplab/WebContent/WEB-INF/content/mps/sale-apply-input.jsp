<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.saleApply.input"/></title>
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
		
		$("#apply!").validator({ 
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
			<legend><s:text name="saleApply.legend.add"/></legend>
			<s:actionmessage/>
			<div id="reportArea"></div>
			<form action="${ctx}/mps/sale-apply!save.do" method="post" id="idSaleApplyForm">
				<input type="text" name="saleApply.id" value="${saleApply.id}" style="display:none">
				<input type="text" name="saleApply.version" value="${saleApply.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="saleApply.label.materialCode"/></div>
					<div class="item_input">
						<select required="required" name="saleApply.material.id" class="select-1" id="idApplyMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}" compu="${unit}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idApplyMaterial").val("${saleApply.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="saleApply.label.purQ"/></div>
					<div class="item_input"><input type="number" required="required"  name="saleApply.purQ" value="${saleApply.purQ}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="saleApply.label.demDate"/></div>
					<div class="item_input">
						<input type="date" name="saleApply.demDate" value="${saleApply.demDateF2}" required="required" >
					</div>
				</div>
				<div class="btnArea">
					<script type="text/javascript">
						function show(){
							$("#reportArea").prepend("<div style='margin-bottom:20px;padding-left:50px;color:blue;'><img src='${ctx}/image/theme-1/loading.gif' style='padding-right:20px;'/>Working...</div>");
						}
						
					</script>
					<input type="submit" onclick="show();" value="<s:text name="label.btn.save"/>" class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/sale-apply.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>