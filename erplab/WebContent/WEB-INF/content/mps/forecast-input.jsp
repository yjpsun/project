<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.forecast.list"/></title>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/forecast!input.do">Add Forecast</a>
		</div>
	</div>
	<div class="content" >
		<fieldset>
			<legend>Add Material</legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/forecast!save.do" method="post">
				<input type="text" name="forecast.id" value="${forecast.id}" style="display:none">
				<input type="text" name="forecast.version" value="${forecast.version}" style="display:none">
				<div class="item_line">
					<div class="item_label">Material Code:</div>
					<div class="item_input"><input type="text" name="forecast.code" value="${forecast.code}"></div>
				</div>
				<div class="btnArea">
					<input type="submit" value="保 存" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>