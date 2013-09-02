<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="title.index"/>-<s:text name="title.stock.list"/></title>
<script type="text/javascript">
	$(document).ready(function (){
		$("#idstockForm").validator({ 
			position: 'top left', 
			offset: [-2, 30],
			message: '<div><em/></div>' // em element is the arrow
		});
		$("#hiddenPo").css("display","none");
		$(":date").dateinput({
			format: 'yyyymmdd',	// the format displayed for the user
			selectors: true,             	// whether month/year dropdowns are shown
			min: -1,                    // min selectable day (100 days backwards)
			offset: [5, 10],            	// tweak the position of the calendar
			speed: 300,               	// calendar reveal speed
			firstDay: 1                  	// which day starts a week. 0 = sunday, 1 = monday etc..
		});
		
		var type = $("#idStockType").val();
		var poValue = "${stock.purchaseOrderId}";
		var materialId = $("#idstockMaterial").val();
		if("inPlan"	== type && materialId){
			$("#hiddenPo").css("display","");
			$("#poTable").find("tr").remove();
			$.post("${ctx}/json/mps-ajax!findPurchaseOrder.do",{materialId:materialId},function(data){
				var poList = data.jsonResult;
				if(poList){
					for(var i=0;i<poList.length;i++){
						if(poValue == poList[i].id){
							$("#poTable").append("<tr><td>需求单号:"+poList[i].id+"</td><td>需求日期："+poList[i].demDateF1+"</td><td>采购单价："+poList[i].unitP+"</td><td>合计数量："
									+poList[i].sumption+"</td><td><input type='radio' name='stock.purchaseOrderId' checked='checked' value="+poList[i].id+"></td></tr>");
						}else{
							$("#poTable").append("<tr><td>需求单号:"+poList[i].id+"</td><td>需求日期："+poList[i].demDateF1+"</td><td>采购单价："+poList[i].unitP+"</td><td>合计数量："
									+poList[i].sumption+"</td><td><input type='radio' name='stock.purchaseOrderId' value="+poList[i].id+"></td></tr>");								
						}
					}					
				}
			});
		}
	});
	
	function changeType(){
		var type = $("#idStockType").val();
		var materialId = $("#idstockMaterial").val();
		if("inPlan"	== type){
			$("#id_plan_incoming_q").removeAttr("readonly");
			$("#line_unit_p").css("display","none");
		}
		
		if("inPlan"	== type && materialId){
			$("#hiddenPo").css("display","");
			
			$("#poTable").find("tr").remove();
			$.post("${ctx}/json/mps-ajax!findPurchaseOrder.do",{materialId:materialId},function(data){
				var poList = data.jsonResult;
				if(poList){
					for(var i=0;i<poList.length;i++){
						$("#poTable").append("<tr><td>需求单号:"+poList[i].id+"</td><td>需求日期："+poList[i].demDateF1+"</td><td>采购单价："+poList[i].unitP+"</td><td>合计数量："
								+poList[i].sumption+"</td><td><input type='radio' name='stock.purchaseOrderId' value="+poList[i].id+"></td></tr>");
					}					
				}
			});
		}
		if("outPlan" == type || !type){
			$("#hiddenPo").css("display","none");
			$("#line_unit_p").css("display","");
			$("#id_plan_incoming_q").val("");
			$("#id_plan_incoming_q").attr("readOnly","readOnly");
		}
	}
</script>
</head>
<body>
	<div class="content" >
		<fieldset>
			<legend><s:text name="stock.legend.add"/></legend>
			<s:actionmessage/>
			<form action="${ctx}/mps/stock!save.do" method="post" id="idstockForm">
				<input type="text" name="stock.id" value="${stock.id}" style="display:none">
				<input type="text" name="stock.version" value="${stock.version}" style="display:none">
				<div class="item_line">
					<div class="item_label"><s:text name="stock.label.materialCode"/></div>
					<div class="item_input">
						<select required="required" onchange="changeType();" name="stock.material.id" class="select-1" id="idstockMaterial">
							<option value=""><s:text name="label.select.default"/></option>
							<s:iterator value="materialList" id="material" status="stat">
								<option value="${id}" compu="${unit}">${code}</option>
							</s:iterator>
						</select>
						<script>
							$("#idstockMaterial").val("${stock.material.id}");
						</script>
					</div>
					<div class="item_label"><s:text name="stock.label.stocktype"/></div>
					<div class="item_input">
						<select name="stock.stocktype" onchange="changeType();" class="select-1" id="idStockType" required="required">
							<option value=""><s:text name="label.select.default"/></option>
							<option value="outPlan">计划外</option>
							<option value="inPlan">计划内</option>
						</select>
						<script>
							$("#idStockType").val("${stock.stocktype}");
						</script>
					</div>
				</div>
				<div class="cl"></div>
				<div id="hiddenPo">
					<div class="item_label">采购单号</div>
					<div id="hiddenPoSelect">
						<table id="poTable">
						</table>
					</div>
				</div>
				<div class="cl"></div>
				<div class="item_line">
					<div class="item_label"><s:text name="stock.label.rIncomingQ"/></div>
					<div class="item_input"><input type="number" name="stock.rincomingQ" value="${stock.rincomingQ}"></div>
					<div class="item_label"><s:text name="stock.label.pIncomingQ"/></div>
					<div class="item_input"><input id="id_plan_incoming_q" type="number" name="stock.pincomingQ" value="${stock.pincomingQ}"></div>
				</div>
				<div class="item_line">
					<div class="item_label"><s:text name="stock.label.incomingdate"/></div>
					<div class="item_input"><input type="date" name="stock.incomingdate" value="${stock.incomingdateF2}" required="required" ></div>
				</div>
				
				<div class="item_line" id="line_unit_p">
					<div class="item_label"><s:text name="stock.label.unitP"/></div>
					<div class="item_input"><input id="id_unit_p" type="number" name="stock.unitP" value="${stock.unitP}"></div>
				</div>
				
					
				<div class="btnArea">
					<input type="submit" value="<s:text name="label.btn.save"/>"  class="button-normal">
					<input type="button" value="<s:text name="label.btn.back"/>" onclick="jump('${ctx}/mps/stock.do')" class="button-normal">
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>