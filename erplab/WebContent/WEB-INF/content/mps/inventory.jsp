<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.inventory.list"/></title>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/inventory!input.do"><s:text name="inventory.add.btn"/></a>
		</div>
	</div>
	<div class="content">
		<s:actionmessage/>
		<display:table id="row"
		class="displayList" requestURI="inventory.do" name="inventoryList" pagesize="10"
		size="resultSize" partialList="true">
		<display:column>
			<display:column titleKey="inventory.displaytag.materialCode">
				${row.material.code}
			</display:column>
			<display:column titleKey="inventory.displaytag.bookOh">
				${row.bookOH}
			</display:column>
			<display:column titleKey="inventory.displaytag.bookValue">
				${row.bookvalue}
			</display:column>
			<display:column titleKey="inventory.displaytag.inventoryOh">
				${row.inventoryOH}
			</display:column>
			<display:column titleKey="inventory.displaytag.inventoryValue">
				${row.inventoryvalue}
			</display:column>
			<display:column titleKey="inventory.displaytag.ohlp">
				${row.ohlp}
			</display:column>
			<display:column titleKey="inventory.displaytag.valueLp">
				${row.valueLP}
			</display:column>
			<display:column titleKey="inventory.displaytag.noftolerance">
				${row.noftolerance}
			</display:column>
			<display:column titleKey="inventory.displaytag.aoftolerance">
				${row.aoftolerance}
			</display:column>
			<display:column titleKey="inventory.displaytag.inventoryTime">
				${row.inventorytimeF1}
			</display:column>
			<display:column titleKey="inventory.displaytag.countPeople">
				${row.countpeople}
			</display:column>
			<display:column titleKey="label.operation">
				<a href="javaScript:if(confirm('Confirm to remove this record?')) window.location='${ctx}/mps/inventory!remove.do?id=${row.id}';"><s:text name="label.operation.remove"/></a>|
				<a href="${ctx}/mps/inventory!input.do?id=${row.id}"><s:text name="label.operation.update"/></a>
			</display:column>
		</display:column>
 		</display:table>
	</div>
</body>
</html>