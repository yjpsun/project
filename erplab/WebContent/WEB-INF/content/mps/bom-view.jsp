<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.forecast.setting"/></title>
</head>
<body>
	<div class="header">
	</div>
	<div class="content">
		<form action="${ctx}/mps/bom!save.do" method="post" id="idBomForm">
		<s:actionmessage/>
		<div class="item_line">
			<div class="item_label"><s:text name="bom.label.structure.id"></s:text></div>
			<div class="item_input">
				<input type="text" id="idBomMaterialId" name="materialCode" value="${materialCode}" readonly="readonly"/>
			</div>
			<div class="item_label"><s:text name="bom.label.edition"></s:text></div>
			<div class="item_input">
				<input type="text" id="idBomMaterialId" name="edition" value="${edition}" readonly="readonly"/>
				<input type="text" id="idBomIds" name="removedBomIds" style="display:none"/>
			</div>
		</div>
		<div id="editionArea">
		<table class="displayList" id="bowTable" style="width:50%">
				<thead>
					<tr>
					    <th><s:text name="bom.table.thead.serialNum"></s:text></th>
					    <th><s:text name="bom.table.thead.structureId"></s:text></th>
					    <th><s:text name="bom.table.thead.childId"></s:text></th>
					    <th><s:text name="bom.table.thead.qp"></s:text></th>
						<th><s:text name="bom.table.thead.ot"></s:text></th>
						<th><s:text name="bom.table.thead.scraprate"></s:text></th>
					</tr>
				</thead>
				<tbody>
					
				<s:iterator value="bomList" id="bom" status="stat">
					<tr class="even" id="bomTr_${stat.index}">
					    <td class="serialNum">${stat.count}</td>
						<td>
							<input type="text" name="bomList[${stat.index}].father_material_id" class="input-1" value="${father_material_id}" readonly="readonly">
						</td>
						<td>
							<input type="text" name="bomList[${stat.index}].child_material_id" class="input-1" value="${child_material_id}" readonly="readonly">
						</td>
						<td><input type="text" name="bomList[${stat.index}].qp" class="input-1" value="${qp}" readonly="readonly"></td>
						<td><input type="text" name="bomList[${stat.index}].ot" class="input-1" value="${ot}" readonly="readonly"></td>
						<td><input type="text" name="bomList[${stat.index}].scraprate" class="input-1" value="${scraprate}" readonly="readonly"></td>
					</tr>
				</s:iterator>
				</tbody>
		</table>
		</div>
		<div class="btnArea">
			<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/bom.do?materialCode=${materialCode}')" class="button-normal">
		</div>
		</form>
	</div>
</body>
</html>