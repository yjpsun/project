<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.forecast.setting"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		var materialCode = "${materialCode}";
		if(!materialCode){
			$("#bomOp_newEdition").remove();
		}
	});
</script>
</head>
<body>
	<div class="header">
	</div>
	<div class="content">
		<form action="${ctx}/mps/forecast!save.do" method="post" id="idForecastForm">
		<s:actionmessage/>
		<div class="item_line">
			<div class="item_label"><s:text name="bom.label.structure.id"></s:text></div>
			<div class="item_input">
				<script>
					function loadEditions(){
						var url = "${ctx}/mps/bom.do?materialCode="+$('#idBomMaterial').val();
						jump(url);
					}
				</script>
				<select onchange="loadEditions();" name="materialId" class="select-1" id="idBomMaterial">
					<option value=""><s:text name="label.select.default"/></option>
					<s:iterator value="materialList" id="material" status="stat">
						<option value="${code}">${code}</option>
					</s:iterator>
				</select>
				<script>
					$("#idBomMaterial").val("${materialCode}");
				</script>
			</div>
		</div>
		<div id="bomOp_newEdition">
			<a href="${ctx}/mps/bom!input.do?materialCode=${materialCode}"><s:text name="bom.button.newEdtion"></s:text></a>
		</div>
		<div id="editionsArea">
		<table class="displayList" id="row" style="width:50%">
				<thead>
					<tr>
						<th><s:text name="bom.table.thead.edtion"></s:text></th>
						<th><s:text name="label.operation"></s:text></th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="edtionList" id="edtion" status="stat">
					 <s:if test="#st.Even">
					 	<tr class="even">
					 </s:if>
					 <s:if test="#st.odd">
					 	<tr class="odd">
					 </s:if>
						<td>${edtion}</td>
						<td>
						    <a href="${ctx}/mps/bom!view.do?materialCode=${materialCode}&edition=${edtion}">view</a>|
							<a href="${ctx}/mps/bom!input.do?materialCode=${materialCode}&edition=${edtion}">edit</a>|
							<a href="javaScript:if(confirm('Confirm to remove this record?')) window.location='${ctx}/mps/bom!remove.do?materialCode=${materialCode}&edition=${edtion}';">remove</a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
		</table>
			
		</div>
		</form>
	</div>
</body>
</html>