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
		<div class="op">
			<a class="button-red" href="${ctx}/mps/order!input.do"><s:text name="order.add.btn"/></a>
		</div>
	</div>
	<div class="content">
		<s:actionmessage/>
		<form action="${ctx}/mps/order.do" method="post" id="idSearchForm">
			<div class="item_line">
				<div class="item_label"><s:text name="order.label.materialCode"/></div>
					<div class="item_input">
						<select name="order.material.id" class="select-1" id="idOrderMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idOrderMaterial").val("${order.material.id}");
						</script>
					</div>
				</div>
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.search"/>" class="button-normal">
				</div>
		</form>
		<display:table id="row"
		class="displayList" requestURI="order.do" name="orderList" pagesize="10"
		size="resultSize" partialList="true">
		<display:column>
			<display:column titleKey="order.displaytag.materialCode">
				${row.material.code}
			</display:column>
			<display:column titleKey="order.displaytag.computation">
				${row.computation}
			</display:column>
			<display:column titleKey="order.displaytag.orderQ">
				${row.quantity}
			</display:column>
			<display:column titleKey="order.displaytag.demDate">
				${row.demDateF1}
			</display:column>
			<display:column titleKey="label.operation">
				<a href="javaScript:if(confirm('Confirm to remove this record?')) window.location='${ctx}/mps/order!remove.do?id=${row.id}';"><s:text name="label.operation.remove"/></a>|
				<a href="${ctx}/mps/order!input.do?id=${row.id}"><s:text name="label.operation.update"/></a>
			</display:column>
		</display:column>
 		</display:table>
	</div>
</body>
</html>