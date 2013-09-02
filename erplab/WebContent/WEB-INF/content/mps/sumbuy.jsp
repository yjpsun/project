<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.sumbuy.list"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		var sumAll = 0;
		$("#sumbuyTable").find("tr").each(function (){
			var tr = $(this);
			var purq = tr.find("td[class='purq']").text()*1;
			sumAll = purq + sumAll;
			tr.find("td[class='sumption']").attr("rowspan",2);
		});
		$("#sumbuyTable").find("td[class='sumption']:first").text(sumAll).css("vertical-align","middle");
	});
	
	function generateOrder(){
		var url = "${ctx}/mps/sumbuy!generateOrder.do?materialId="+$("#idSumbuyMaterial").val();
		jump(url);
	}
</script>
</head>
<body>
	<div class="header">
	</div>
	<div class="content">
		<s:actionmessage/>
		<form action="${ctx}/mps/sumbuy.do" method="post" id="idSumbuyForm">
			<div class="item_line">
				<div class="item_label"><s:text name="order.label.materialCode"/></div>
					<div class="item_input">
						<select name="materialId" class="select-1" id="idSumbuyMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idSumbuyMaterial").val("${materialId}");
						</script>
					</div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.sumbuy.btn.sum"/>" class="button-normal-01">
					<input type="button" value="<s:text name="label.sumbuy.btn.createOrder"/>" onclick="generateOrder();''" class="button-normal-01">
				</div>
		</form>
		
		<table class="displayList" id="sumbuyTable">
			<thead>
			<tr>
				<th><s:text name="label.sumbuy.purId"/></th>
				<th><s:text name="label.sumbuy.materialId"/></th>
				<th><s:text name="label.sumbuy.demDate"/></th>
				<th><s:text name="label.sumbuy.purDate"/></th>
				<th><s:text name="label.sumbuy.deliveryDate"/></th>
				<th><s:text name="label.sumbuy.sumption"/></th>
			</tr>
			</thead>
			<tbody>
			<s:iterator value="buyList" id="apply" status="st">
				<tr class="even">
					<td class="purq" style="display: none;">${purQ}</td>
					<td>${id}</td>
					<td>${material.code}</td>
					<td>${demDateF1}</td>
					<td>${purDateF1}</td>
					<td>${delDateF1}</td>
					<td class="sumption"></td>
				</tr>
			</s:iterator>	
			</tbody>
		</table>
	</div>
</body>
</html>