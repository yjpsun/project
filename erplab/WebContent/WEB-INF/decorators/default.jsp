<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><decorator:title default="<s:text name='title.index'/>" /></title>
<link rel="stylesheet" title="style-normal" href="${ctx}/style/style-normal.css" type="text/css" media="all" />
<link rel="alternate stylesheet" title="style-green" href="${ctx}/style/style-green.css" type="text/css" media="all" />

<script type="text/javascript" src="${ctx}/script/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.tools.min.js"></script>
<script type="text/javascript" src="${ctx}/script/utils.js"></script>
<script type="text/javascript">
	$(document).ready(function (){
		$('a.styleswitch').click(function(){
			switchStylestyle(this.getAttribute("rel"));
			return false;
		});
		var c = readCookie('style');
		if (c) switchStylestyle(c);
		
		
		var curUrl = location.href;
		var curMenuId = "";
		if(curUrl.indexOf("material") >= 0){
			curMenuId = "mId_material";
		};
		if(curUrl.indexOf("timegrating") >= 0){
			curMenuId = "mId_timegrating";
		};
		if(curUrl.indexOf("forecast") >= 0){
			curMenuId = "mId_forecast";
		};
		if(curUrl.indexOf("order.do") >= 0 || curUrl.indexOf("order!input.do")>= 0 || curUrl.indexOf("order!save.do")>= 0){
			curMenuId = "mId_order";
		};
		if(curUrl.indexOf("order!orderSum.do") >= 0){
			curMenuId = "mId_orderSum";
		};
		
		if(curUrl.indexOf("report.do") >= 0){
			curMenuId = "mId_mpsReport";
		};
		
		if(curUrl.indexOf("material-delivering") >= 0){
			curMenuId = "mId_mpsDelivering";
		};
		
		if(curUrl.indexOf("material-stock") >= 0){
			curMenuId = "mId_mpsStock";
		};
		
		if(curUrl.indexOf("bom") >= 0){
			curMenuId = "mId_bomConfig";
		};
		
		if(curUrl.indexOf("bom-report") >= 0){
			curMenuId = "mId_bomReport";
		};
		
		if(curUrl.indexOf("apply") >= 0){
			curMenuId = "mId_apply";
		};
		if(curUrl.indexOf("supplyer") >= 0){
			curMenuId = "mId_supplyer";
		};
		if(curUrl.indexOf("sumbuy") >= 0){
			curMenuId = "mId_sumbuy";
		};
		if(curUrl.indexOf("purchase-order") >= 0){
			curMenuId = "mId_purchaseOrder";
		};
		if(curUrl.indexOf("stock-cost.do") >= 0 || curUrl.indexOf("stock-cost!input.do") >= 0){
			curMenuId = "mId_stockCost";
		};
		if(curUrl.indexOf("stock.do") >= 0 || curUrl.indexOf("stock!input.do") >= 0){
			curMenuId = "mId_stock";
		};
		if(curUrl.indexOf("inventory") >= 0){
			curMenuId = "mId_inventory";
		};
		
		if(curUrl.indexOf("out-stock") >= 0){
			curMenuId = "mId_outStock";
		};
		
		if(curUrl.indexOf("sale-apply.do") >= 0 || curUrl.indexOf("sale-apply!input.do") >= 0){
			curMenuId = "mId_saleApply";
		};
		if(curUrl.indexOf("sale.do") >= 0){
			curMenuId = "mId_sale";
		};
		if(curUrl.indexOf("stock-query") >= 0){
			curMenuId = "mId_stockQuery";
		};
		changeMenu(curMenuId);
	});
	
	function changeMenu(curMenuId){
		$("li.subMenu").find("li").each(function (){
			var curId = $(this).attr("id");
			if(curId == curMenuId){
				$(this).attr("class","selected");
			}else{
				$(this).removeAttr("selected");
			}
		});  	
	}
	
	function languageChange(obj){
		var url = '${ctx}/index.do?request_locale='+$(obj).val();
		jump(url);
	}
</script>



<decorator:head/>
</head>
<body>
	<div id="page">
		<div id="header">
			<div id="logo">
				<div class="text">
					<h1><s:text name="label.logo.title"/></h1>
				</div>
			</div>
			<div id="functionBar">
				<div class="langShift">
					<s:text name="label.language.change"/>
					<select class="select-1" id="idLanguageChange" onchange="languageChange(this)">
						<option value="zh_CN">中文</option>
						<option value="en_US">English</option>
					</select>
					<script type="text/javascript">
						$("#idLanguageChange").val('${WW_TRANS_I18N_LOCALE}');
					</script>
				</div>
				<div class="themeShift">
					 <ul id="styleSwitch" style="float:left">
					 	<li><s:text name="label.style.change"/></li>
			            <li class="styleGrid">
			              <a href="" rel="style-normal" class="styleswitch normal"></a>
			              <a href="" rel="style-green" class="styleswitch green"></a>
			            </li>
		            </ul>
				</div>
			</div>
		</div>
		<div id="central">
			<div id="sidebar">
				<div id="loginInfo">
				
				</div>
				<div id="menu">
					<ul>
						<li class="mainMenu"><div class="menu_exp"><s:text name="label.menu1.main"/></div></li>
						<li class="subMenu">
							<ul>
								<li id="mId_material"><a href="${ctx}/mps/material.do"><s:text name="label.menu1.sub1"/></a></li>
								
								<li id="mId_order"><a href="${ctx}/mps/order.do"><s:text name="label.menu1.sub4"/></a></li>
								<li id="mId_orderSum"><a href="${ctx}/mps/order!orderSum.do"><s:text name="label.menu1.sub5"/></a></li>
								<li id="mId_mpsDelivering"><a href="${ctx}/mps/material-delivering.do"><s:text name="label.menu1.sub7"/></a></li>
								<li id="mId_mpsStock"><a href="${ctx}/mps/material-stock.do"><s:text name="label.menu1.sub8"/></a></li>
								<li id="mId_timegrating"><a href="${ctx}/mps/timegrating.do"><s:text name="label.menu1.sub2"/></a></li>
								<li id="mId_forecast"><a href="${ctx}/mps/forecast.do"><s:text name="label.menu1.sub3"/></a></li>
								<li id="mId_mpsReport"><a href="${ctx}/mps/report.do"><s:text name="label.menu1.sub6"/></a></li>
								<li id="mId_bomConfig"><a href="${ctx}/mps/bom.do"><s:text name="label.menu1.sub10"/></a></li>
								<li id="mId_bomReport"><a href="${ctx}/mps/bom-report.do"><s:text name="label.menu1.sub9"/></a></li>
							</ul>
						</li>
					</ul>
					<ul>
						<li class="mainMenu"><div class="menu_col"><s:text name="label.menu2.main"/></div></li>
						<li class="subMenu">
							<ul>
								<li id="mId_apply"><a href="${ctx}/mps/apply.do"><s:text name="label.menu2.sub1"/></a></li>
								<li id="mId_supplyer"><a href="${ctx}/mps/supplyer.do"><s:text name="label.menu2.sub2"/></a></li>
								<li id="mId_sumbuy"><a href="${ctx}/mps/sumbuy.do"><s:text name="label.menu2.sub3"/></a></li>
								<li id="mId_purchaseOrder"><a href="${ctx}/mps/purchase-order.do"><s:text name="label.menu2.sub4"/></a></li>
								<li id="mId_stockCost"><a href="${ctx}/mps/stock-cost.do"><s:text name="label.menu2.sub5"/></a></li>
								<li id="mId_stock"><a href="${ctx}/mps/stock.do"><s:text name="label.menu2.sub6"/></a></li>
								<li id="mId_inventory"><a href="${ctx}/mps/inventory.do"><s:text name="label.menu2.sub7"/></a></li>
								<li id="mId_outStock"><a href="${ctx}/mps/out-stock.do"><s:text name="label.menu2.sub8"/></a></li>
								<li id="mId_saleApply"><a href="${ctx}/mps/sale-apply.do"><s:text name="label.menu2.sub9"/></a></li>
								<li id="mId_sale"><a href="${ctx}/mps/sale.do"><s:text name="label.menu2.sub10"/></a></li>
								<li id="mId_stockQuery"><a href="${ctx}/mps/stock-query.do"><s:text name="label.menu2.sub11"/></a></li>
							</ul>
						</li>
					</ul>
					<ul>
						<li class="mainMenu"><div class="menu_col"><s:text name="label.menu3.main"/></div></li>
					</ul>
				</div>
			</div>
			<div id="content">
				<decorator:body></decorator:body>
			</div>
		</div>
		<div id="footer">
			POWERED BY DAWN STUDIO.
		</div>
	</div>
</body>
</html>
