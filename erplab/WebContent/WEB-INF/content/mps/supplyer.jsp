<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.supplyer.list"/></title>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/supplyer!input.do"><s:text name="supplyer.add.btn"/></a>
		</div>
	</div>
	<div class="content">
		<s:actionmessage/>
		<display:table id="row"
		class="displayList" requestURI="supplyer.do" name="supplyerList" pagesize="10"
		size="resultSize" partialList="true">
		<display:column>
			<display:column titleKey="supplyer.displaytag.id">
				${row.code}
			</display:column>
			<display:column titleKey="supplyer.displaytag.materialCode">
				${row.material.code}
			</display:column>
			<display:column titleKey="supplyer.displaytag.slsr">
				${row.slsr}
			</display:column>
			<display:column titleKey="supplyer.displaytag.sls">
				${row.sls}
			</display:column>
			<display:column titleKey="supplyer.displaytag.unitP">
				${row.unitP}
			</display:column>
			<display:column titleKey="supplyer.displaytag.st">
				${row.st}
			</display:column>
			<display:column titleKey="supplyer.displaytag.maxS">
				${row.maxS}
			</display:column>
			<display:column titleKey="label.operation">
				<a href="javaScript:if(confirm('Confirm to remove this record?')) window.location='${ctx}/mps/supplyer!remove.do?id=${row.id}';"><s:text name="label.operation.remove"/></a>|
				<a href="${ctx}/mps/supplyer!input.do?id=${row.id}"><s:text name="label.operation.update"/></a>
			</display:column>
		</display:column>
 		</display:table>
	</div>
</body>
</html>