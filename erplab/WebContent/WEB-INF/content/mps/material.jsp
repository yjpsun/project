<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.material.list"/></title>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/material!input.do"><s:text name="material.add.btn"/></a>
		</div>
	</div>
	<div class="content">
		<s:actionmessage/>
		<display:table id="row"
		class="displayList" requestURI="${ctx}/mps/material.do" name="materialList" pagesize="10"
		size="resultSize" partialList="true">
		<display:column>
				<display:column titleKey="material.displaytag.code">
					${row.code}
				</display:column>
				<display:column titleKey="material.displaytag.computation">
					${row.computation}
				</display:column>
				<display:column titleKey="material.displaytag.ps">
					${row.matPs}
				</display:column>
				<display:column titleKey="material.displaytag.lsr">
					${row.matLsr}
				</display:column>
				<display:column titleKey="label.operation">
					<a href="javaScript:if(confirm('Confirm to remove this record?')) window.location='${ctx}/mps/material!remove.do?id=${row.id}';"><s:text name="label.operation.remove"/></a>|
					<a href="${ctx}/mps/material!input.do?id=${row.id}"><s:text name="label.operation.update"/></a>
				</display:column>
		</display:column>
 		</display:table>
	</div>
</body>
</html>