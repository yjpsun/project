<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.timegrating.input"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$("#timeGratingForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
		addCompareLessValidtor();
	});
</script>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/timegrating!input.do"><s:text name="timegrating.add.btn"/></a>
		</div>
	</div>
	<div class="content" >
		<fieldset>
			<legend><s:text name="timegrating.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/timegrating!save.do" method="post" id="timeGratingForm">
				<input type="text" name="timeGrating.id" value="${timeGrating.id}" style="display:none">
				<input type="text" name="timeGrating.version" value="${timeGrating.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="timegrating.label.materialCode"/></div>
					<div class="item_input">
						<select required="required" name="timeGrating.material.id" class="select-1" id="idTimeGratingMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idTimeGratingMaterial").val("${timeGrating.material.id}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="timegrating.label.DTF"/></div>
					<div class="item_input"><input pattern="[1-7]{1}" validatorName="DTF" type="text" id="idTimeGratingDtf" name="timeGrating.dtf" value="${timeGrating.dtf}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="timegrating.label.PTF"/></div>
					<div class="item_input"><input pattern="[1-7]{1}" validatorName="RTF" data-compare-less="idTimeGratingDtf" type="text" name="timeGrating.ptf" value="${timeGrating.ptf}"></div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/timegrating.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>