<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.mps.report"/></title>
<script src="${ctx}/script/amcharts/amcharts.js" type="text/javascript"></script>
<script src="${ctx}/script/amcharts/raphael.js" type="text/javascript"></script>
</head>
<body>
	<div class="header">
	</div>
	<div class="content" >
		<div class="item_line">
			<div class="item_label"><s:text name="mpsreport.label.materialCode"/></div>
			<div class="item_input">
				<select class="select-1" id="idReportMaterid" name="id">
					<option value=""><s:text name="label.select.default"/></option>
					<s:iterator value="materialList" id="material" status="stat">
						<option value="${id}">${code}</option>
					</s:iterator>
				</select>
			</div>
			<script>
				function buildReport(){
					var materialId = $("#idReportMaterid").val();
					if(materialId && materialId != '-1'){
						$("#reportArea").prepend("<div style='margin-bottom:20px;padding-left:50px;color:blue;'><img src='${ctx}/image/theme-1/loading.gif' style='padding-right:20px;'/>loading...</div>");
						$("#reportArea").load("${ctx}/mps/report!buildReport.do",{materialId:materialId},function(data){
							
						});
					}
				}
			</script>
			<input type="button" value="<s:text name="mpsreport.getreport.btn"/>" class="button-normal-01" onclick="buildReport();">
		</div>
		<div id="reportArea">
		
		</div>
	</div>
</body>
</html>