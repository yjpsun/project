<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.saleApply.list"/></title>
</head>
<body>
	<div class="header">
		<div class="op">
		</div>
	</div>
	<div class="content">
		<s:actionmessage/>
		<display:table id="row"
		class="displayList" requestURI="sale.do" name="saleList" pagesize="10"
		size="resultSize" partialList="true">
		<display:column>
			<display:column titleKey="saleApply.displaytag.materialCode">
				${row.material.code}
			</display:column>
			<display:column titleKey="saleApply.displaytag.demDate">
				${row.demDateF1}
			</display:column>
			<display:column titleKey="saleApply.displaytag.purQ">
				${row.purQ}
			</display:column>
			<display:column titleKey="label.operation">
				<a href="javaScript:if(confirm('Confirm to remove this record?')) window.location='${ctx}/mps/sale!remove.do?id=${row.id}';"><s:text name="label.operation.remove"/></a>|
			</display:column>
		</display:column>
 		</display:table>
	</div>
</body>
</html>