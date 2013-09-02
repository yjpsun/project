<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.order.list"/></title>
</head>
<body>
	<div class="header">
	</div>
	<div class="content">
		<s:actionmessage/>
		<form action="${ctx}/mps/stock-query.do" method="post" id="idSearchForm">
				<div class="item_line">
					<div class="item_label"><s:text name="order.label.materialCode"/></div>
					<div class="item_input">
						<select name="id" class="select-1" id="idOrderMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idOrderMaterial").val("${id}");
						</script>
					</div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="stock.label.matSr"/></div>
					<div class="item_input">
						<input name="matSr" value="${matSr}" type="number">
					</div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.search"/>" class="button-normal">
				</div>
		</form>
		<div class="dataReport">
			<table border="1"   cellpadding="0"   cellspacing="0">
			<tr>
				<td width="200">MaterialID</td>
				<td width="200">OH（在库量）</td>
				<td width="200">SR（在途量）</td>
				<td width="200">MatSR（在途调整量）</td>
			</tr>
			<tr>
				<td>${stockQuery.material.code}</td>
				<td>${stockQuery.oh}</td>
				<td>${stockQuery.sr}</td>
				<td>${stockQuery.matSr}</td>
			</tr>
			</table>
	</div>
	</div>
</body>
</html>