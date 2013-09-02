<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.outStock.list"/></title>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/out-stock!input.do"><s:text name="outstock.add.btn"/></a>
		</div>
	</div>
	<div class="content">
		<s:actionmessage/>
		<display:table id="row"
		class="displayList" requestURI="out-stock.do" name="outStockList" pagesize="10"
		size="resultSize" partialList="true">
		<display:column>
			<display:column titleKey="outstock.displaytag.type">
				${row.outStockType}
			</display:column>
			<display:column titleKey="outstock.displaytag.materialCode">
				${row.material.code}
			</display:column>
			<display:column titleKey="outstock.displaytag.saleId">
				${row.salesId}
			</display:column>
			<display:column titleKey="outstock.displaytag.poutq">
				${row.poutQ}
			</display:column>
			<display:column titleKey="outstock.displaytag.routq">
				${row.routQ}
			</display:column>
			<display:column titleKey="outstock.displaytag.outDate">
				${row.outDateF1}
			</display:column>
			<display:column titleKey="label.operation">
				<a href="javaScript:if(confirm('Confirm to remove this record?')) window.location='${ctx}/mps/out-stock!remove.do?id=${row.id}';"><s:text name="label.operation.remove"/></a>|
				<a href="${ctx}/mps/out-stock!input.do?id=${row.id}"><s:text name="label.operation.update"/></a>
			</display:column>
		</display:column>
 		</display:table>
	</div>
</body>
</html>