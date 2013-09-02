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
		$("#idBomForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
	});
	function addBomRow(){
		var curIndex = $("#orgBomLength").val()*1;
		var newTr = $("#hiddenTableForBom").find("tbody").html();
		newTr = newTr.replace(/y000001/g,curIndex).replace(/y000002/g,curIndex+1).replace(/none/,"");
		$("#bowTable").append(newTr);
		$("#orgBomLength").val(curIndex+1);
		resetSerialNum();
	};
	
	var removeBomIds = "";
	function removeBomRow(trNum){
		var curBomId = $("#bomId_"+trNum).val();
		if(curBomId){
			removeBomIds = curBomId + "," + removeBomIds;
		};
		$("#bomTr_"+trNum).remove();
		$("#idBomIds").val(removeBomIds);
		resetSerialNum();
	};
	
	function resetSerialNum(){
		$("#bowTable .serialNum").each(function (index){
			$(this).text(index+1);
		});
	}
</script>
</head>
<body>
	<div class="header">
	</div>
	<div class="content">
		<table id="hiddenTableForBom">
			 <tr class="even"  style="display:none;" id="bomTr_y000001">
			    <td class="serialNum">y000002</td>
				<td>
					<select class="select-1" name="bomList[y000001].father_material_id" id="idFatherM_${stat.index}" required="required">
						<option value=""><s:text name="label.select.default"/></option>
						<s:iterator value="materialList" id="material" status="stat">
							<option value="${code}">${code}</option>
						</s:iterator>
					</select>
				</td>
				<td>
					<select class="select-1" name="bomList[y000001].child_material_id" id="idChildM_${stat.index}" required="required">
						<option value=""><s:text name="label.select.default"/></option>
						<s:iterator value="materialList" id="material" status="stat">
							<option value="${code}">${code}</option>
						</s:iterator>
					</select>
				</td>
				<td><input type="text" name="bomList[y000001].qp" class="input-1" type="number" required="required" ></td>
				<td><input type="text" name="bomList[y000001].ot" class="input-1" type="number" required="required" ></td>
				<td><input type="text" name="bomList[y000001].scraprate" class="input-1" type="number" required="required" ></td>
				<td>
					<a href="javaScript:removeBomRow('y000001');">remove</a>
					<input type="text" name="bomList[y000001].structure_id" class="input-1" value="${materialCode}" style="display:none;">
					<input type="text" name="bomList[y000001].edition" class="input-1" value="${edition}" style="display:none;">
				</td>
			</tr>
		</table>
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
		<div> <a href="javascript:addBomRow();">Add Row</a></div>
		<table class="displayList" id="bowTable" style="width:50%">
				<thead>
					<tr>
					    <th><s:text name="bom.table.thead.serialNum"></s:text></th>
					    <th><s:text name="bom.table.thead.structureId"></s:text></th>
					    <th><s:text name="bom.table.thead.childId"></s:text></th>
					    <th><s:text name="bom.table.thead.qp"></s:text></th>
						<th><s:text name="bom.table.thead.ot"></s:text></th>
						<th><s:text name="bom.table.thead.scraprate"></s:text></th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					
				<s:iterator value="bomList" id="bom" status="stat">
					<tr class="even" id="bomTr_${stat.index}">
					    <td class="serialNum">${stat.count}</td>
						<td>
							<select name="bomList[${stat.index}].father_material_id"  class="select-1" id="idFatherM_${stat.index}" required="required">
								<option value=""><s:text name="label.select.default"/></option>
								<s:iterator value="materialList" id="material" status="stat">
									<option value="${code}">${code}</option>
								</s:iterator>
							</select>
							<script>
								$("#idFatherM_${stat.index}").val("${father_material_id}");
							</script>
						</td>
						<td>
							<select class="select-1" name="bomList[${stat.index}].child_material_id" id="idChildM_${stat.index}" required="required">
								<option value=""><s:text name="label.select.default"/></option>
								<s:iterator value="materialList" id="material" status="stat">
									<option value="${code}">${code}</option>
								</s:iterator>
							</select>
							<script>
								$("#idChildM_${stat.index}").val("${child_material_id}");
							</script>
						</td>
						<td><input type="text" name="bomList[${stat.index}].qp" class="input-1" value="${qp}" type="number" required="required" ></td>
						<td><input type="text" name="bomList[${stat.index}].ot" class="input-1" value="${ot}" type="number" required="required" ></td>
						<td><input type="text" name="bomList[${stat.index}].scraprate" class="input-1" value="${scraprate}" type="number" required="required" ></td>
						<td>
							<a href="javaScript:removeBomRow('${stat.index}');">remove</a>
							<input type="text" name="bomList[${stat.index}].structure_id" class="input-1" value="${materialCode}" style="display:none;">
							<input type="text" name="bomList[${stat.index}].edition" class="input-1" value="${edition}" style="display:none;">
							<input type="text" name="bomList[${stat.index}].id"  id="bomId_${stat.index}" class="input-1" value="${id}" style="display:none;">
							<input type="text" name="bomList[${stat.index}].version"  class="input-1" value="${version}" style="display:none;">
						</td>
					</tr>
				</s:iterator>
				</tbody>
		</table>
		<input type="text" value="<s:property value='bomList.size'/>" id="orgBomLength" style="display:none;">
		</div>
		<div class="btnArea">
			<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal" id="materialSubmitBtn">
			<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/bom.do?materialCode=${materialCode}')" class="button-normal">
		</div>
		</form>
	</div>
</body>
</html>