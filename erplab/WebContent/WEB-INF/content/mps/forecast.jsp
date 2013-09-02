<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.forecast.setting"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$("#idForecastForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
	});
</script>
</head>
<body>
	<div class="header">
		<div class="op">
			<a class="button-red" href="${ctx}/mps/forecast!input.do"><s:text name="forecast.add.btn"/></a>
		</div>
	</div>
	<div class="content">
		<form action="${ctx}/mps/forecast!save.do" method="post" id="idForecastForm">
		<s:actionmessage/>
		<div class="item_line">
			<div class="item_label"><s:text name="forecast.label.materialCode"/></div>
			<div class="item_input">
				<script>
					function reloadForecast(){
						var url = "${ctx}/mps/forecast.do?materialId="+$('#idForecastMaterial').val();
						jump(url);
					}
				</script>
				<select onchange="reloadForecast()" required="required" name="materialId" class="select-1" id="idForecastMaterial">
					<option value=""><s:text name="label.select.default"/></option>
					<s:iterator value="materialList" id="material" status="stat">
						<option value="${id}">${code}</option>
					</s:iterator>
				</select>
				<script>
					$("#idForecastMaterial").val("${materialId}");
				</script>
			</div>
		</div>
		<div class="item_line">
			<div class="item_label"><s:text name="forecast.label.weekStart"/></div>
			<div class="item_label"><s:text name="forecast.label.weekForecast"/></div>
		</div>
		<s:iterator value="forecastList" id="forecast" status="stat">
			<div class="item_line">
				<div class="item_label">
					${stat.count}. ${weekStartF1}:
					<input type="text" name="forecastList[${stat.index}].id" value="${id}" style="display:none">
					<input type="text" name="forecastList[${stat.index}].version" value="${version}" style="display:none">
					<input type="text" name="forecastList[${stat.index}].weekStart" value="${weekStartF2}" style="display:none">
				</div>
				<div class="item_input">
					<input type="number" required="required" name="forecastList[${stat.index}].weekForecast" value="${weekForecast}">
				</div>
			</div>
		</s:iterator>
		<div class="btnArea">
			<input type="submit" value="<s:text name="label.btn.save"/>" class="button-normal">
		</div>
		</form>
	</div>
</body>
</html>