<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.purchaseOrder.list"/></title>
</head>
<body>
	<div class="header">
	</div>
	<div class="content">
		<s:actionmessage/>
		<display:table id="row"
		class="displayList" requestURI="purchase-order.do" name="purchaseOrderList" pagesize="10"
		size="resultSize" partialList="true">
		<display:column>
			<display:column titleKey="purchaseOrder.displaytag.id">
				${row.id}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.materialCode">
				${row.material.code}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.demDate">
				${row.demDateF1}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.purDate">
				${row.purDateF1}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.del">
				${row.delDateF1}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.sumption">
				${row.sumption}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.slsr">
				${row.material.matLsr}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.purQ">
				${row.purQ}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.unitP">
				${row.unitP}
			</display:column>
			<display:column titleKey="purchaseOrder.displaytag.supplyId">
				${row.supplyerId}
			</display:column>
			<display:column titleKey="label.operation">
				<a href="javaScript:if(confirm('Confirm to remove this record?')) window.location='${ctx}/mps/purchase-order!remove.do?id=${row.id}';"><s:text name="label.operation.remove"/></a>|
				<a href="${ctx}/mps/purchase-order!input.do?id=${row.id}"><s:text name="label.operation.update"/></a>
			</display:column>
		</display:column>
 		</display:table>
	</div>
</body>
</html>