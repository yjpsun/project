<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.mrp.report"/></title>
<script src="${ctx}/script/amcharts/amcharts.js" type="text/javascript"></script>
<script src="${ctx}/script/amcharts/raphael.js" type="text/javascript"></script>
<script type="text/javascript">
	function loadEdition(){
		var structureId = $("#idReportMaterid").val();
		$("#idEdition").empty();
		$("#idEdition").append("<option value='''><s:text name='label.select.default'/></option>");
		if(structureId){
			$.post("${ctx}/json/mps-ajax!loadEdition.do",{materialCode:structureId},function(data){
				var editionList = data.jsonResult;
				if(editionList){
					for(var i=0;i<editionList.length;i++){
						$("#idEdition").append("<option value='"+editionList[i]+"'>"+editionList[i]+"</option>");
					}					
				}
			});
		}
	}
</script> 
</head>
<body>
	<div class="header">
	</div>
	<div class="content" >
		<div class="item_line">
			<div class="item_label"><s:text name="mpsreport.label.materialCode"/></div>
			<div class="item_input">
				<select onchange="loadEdition();" class="select-1" id="idReportMaterid">
					<option value=""><s:text name="label.select.default"/></option>
					<s:iterator value="materialList" id="material" status="stat">
						<option value="${code}">${code}</option>
					</s:iterator>
				</select>
			</div>
			<div class="item_label"><s:text name="bom.label.edition"/></div>
			<div class="item_input">
				<select class="select-1" id="idEdition" name="id">
					<option value=""><s:text name="label.select.default"/></option>
				</select>
			</div>
			<script>
				function buildReport(){
					var structureId = $("#idReportMaterid").val();
					var edition = $("#idEdition").val();
					if(structureId && edition){
						$("#reportArea").prepend("<div style='margin-bottom:20px;padding-left:50px;color:blue;'><img src='${ctx}/image/theme-1/loading.gif' style='padding-right:20px;'/>loading...</div>");
						$("#reportArea").load("${ctx}/mps/bom-report!buildReport.do",{structureId:structureId,edition:edition},function(data){
							
						});
					}
				}
				function buildGRReport(){
					var structureId = $("#idReportMaterid").val();
					var edition = $("#idEdition").val();
					if(structureId && edition){
						$("#reportArea").prepend("<div style='margin-bottom:20px;padding-left:50px;color:blue;'><img src='${ctx}/image/theme-1/loading.gif' style='padding-right:20px;'/>loading...</div>");
						$("#reportArea").load("${ctx}/mps/bom-report!buildGRReport.do",{structureId:structureId,edition:edition},function(data){
							
						});
					}
				}
			</script>
			<input type="button" value="<s:text name="mpsreport.getreport.btn"/>" class="button-normal-01" onclick="buildReport();">
			<input type="button" value="<s:text name="bom.getGRreport.btn"/>" class="button-normal-01" onclick="buildGRReport();">
		</div>
		<div id="reportArea">
		
		</div>
	</div>
</body>
</html>