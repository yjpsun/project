<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>
<body>
	<div class="reportContainer">
		<script type="text/javascript">
		$(document).ready(function (){
			var lsr = $("#id_lsr").text();
			if(lsr == "LSR=LFL"){
				$("#id_ls").text("");
				$("#id_lsr_n").text("");
			}
			var ps = "${material.matPs}";
			if("Y" != ps){
				$("#rs_atp").remove();
				$("#rs_mps").remove();
				$("#rs_atp_adjust").remove();
			}
		});
		</script>
		<div class="dataReport">
			<table border="1"   cellpadding="0"   cellspacing="0">
			<tr>
				<td  width="12%">LT=${material.matLt }</td>
				<td width="8%">OH=</td>
				<td width="8%">${oh}</td>
				<td width="8%">AL=</td>
				<td width="8%">${al}</td>
				<td width="8%">SS=${material.matSs}</td>
				<td width="8%">Yield=${material.yield}</td>
				<td width="8%">LSR=${material.matLsr}</td>
				<td width="8%"></td>
				<td width="8%" id="id_ls">LS=${material.matLs}</td>
				<td width="8%" id="id_lsr_n">n=${material.purInt}</td>
			</tr>
			<tr>
				<td>Periods</td>
				<td>Due</td>
				<td>${mps_period.period_0}</td>
				<td>${mps_period.period_1}</td>
				<td>${mps_period.period_2}</td>
				<td>${mps_period.period_3}</td>
				<td>${mps_period.period_4}</td>
				<td>${mps_period.period_5}</td>
				<td>${mps_period.period_6}</td>
				<td>${mps_period.period_7}</td>
				<td></td>
			</tr>
			<tr>
				<td>GR</td>
				<td>0.0</td>
				<td>${mps_gr.period_0}</td>
				<td>${mps_gr.period_1}</td>
				<td>${mps_gr.period_2}</td>
				<td>${mps_gr.period_3}</td>
				<td>${mps_gr.period_4}</td>
				<td>${mps_gr.period_5}</td>
				<td>${mps_gr.period_6}</td>
				<td>${mps_gr.period_7}</td>
				<td></td>
			</tr>
			<tr>
				<td>SR</td>
				<td>0.0</td>
				<td>${mps_sr.period_0}</td>
				<td>${mps_sr.period_1}</td>
				<td>${mps_sr.period_2}</td>
				<td>${mps_sr.period_3}</td>
				<td>${mps_sr.period_4}</td>
				<td>${mps_sr.period_5}</td>
				<td>${mps_sr.period_6}</td>
				<td>${mps_sr.period_7}</td>
				<td></td>
			</tr>
			<tr>
				<td>POH</td>
				<td>0.0</td>
				<td>${mps_poh.period_0}</td>
				<td>${mps_poh.period_1}</td>
				<td>${mps_poh.period_2}</td>
				<td>${mps_poh.period_3}</td>
				<td>${mps_poh.period_4}</td>
				<td>${mps_poh.period_5}</td>
				<td>${mps_poh.period_6}</td>
				<td>${mps_poh.period_7}</td>
				<td></td>
			</tr>
			<tr>
				<td>PAB</td>
				<td>0.0</td>
				<td>${mps_pab.period_0}</td>
				<td>${mps_pab.period_1}</td>
				<td>${mps_pab.period_2}</td>
				<td>${mps_pab.period_3}</td>
				<td>${mps_pab.period_4}</td>
				<td>${mps_pab.period_5}</td>
				<td>${mps_pab.period_6}</td>
				<td>${mps_pab.period_7}</td>
				<td></td>
			</tr>
			<tr>
				<td>NR</td>
				<td>0.0</td>
				<td>${mps_nr.period_0}</td>
				<td>${mps_nr.period_1}</td>
				<td>${mps_nr.period_2}</td>
				<td>${mps_nr.period_3}</td>
				<td>${mps_nr.period_4}</td>
				<td>${mps_nr.period_5}</td>
				<td>${mps_nr.period_6}</td>
				<td>${mps_nr.period_7}</td>
				<td></td>
			</tr>
			<tr>
				<td>PORC</td>
				<td>0.0</td>
				<td>${mps_porc.period_0}</td>
				<td>${mps_porc.period_1}</td>
				<td>${mps_porc.period_2}</td>
				<td>${mps_porc.period_3}</td>
				<td>${mps_porc.period_4}</td>
				<td>${mps_porc.period_5}</td>
				<td>${mps_porc.period_6}</td>
				<td>${mps_porc.period_7}</td>
				<td></td>
			</tr>
			<tr>
				<td>POR</td>
				<td>${mps_por.period_0}</td>
				<td>${mps_por.period_1}</td>
				<td>${mps_por.period_2}</td>
				<td>${mps_por.period_3}</td>
				<td>${mps_por.period_4}</td>
				<td>${mps_por.period_5}</td>
				<td>${mps_por.period_6}</td>
				<td>${mps_por.period_7}</td>
				<td>${mps_por.period_8}</td>
				<td></td>
			</tr>
			<tr id="rs_mps">
				<td>MPS</td>
				<td></td>
				<td>${mps_mps.period_0}</td>
				<td>${mps_mps.period_1}</td>
				<td>${mps_mps.period_2}</td>
				<td>${mps_mps.period_3}</td>
				<td>${mps_mps.period_4}</td>
				<td>${mps_mps.period_5}</td>
				<td>${mps_mps.period_6}</td>
				<td>${mps_mps.period_7}</td>
				<td></td>
			</tr>
			<tr id="rs_atp">
				<td>ATP</td>
				<td></td>
				<td>${mps_atp.period_0}</td>
				<td>${mps_atp.period_1}</td>
				<td>${mps_atp.period_2}</td>
				<td>${mps_atp.period_3}</td>
				<td>${mps_atp.period_4}</td>
				<td>${mps_atp.period_5}</td>
				<td>${mps_atp.period_6}</td>
				<td>${mps_atp.period_7}</td>
				<td></td>
			</tr>
			<tr id="rs_atp_adjust">
					<td>ATP Adjust</td>
					<td></td>
					<td>${mps_atp_adjust.period_0}</td>
					<td>${mps_atp_adjust.period_1}</td>
					<td>${mps_atp_adjust.period_2}</td>
					<td>${mps_atp_adjust.period_3}</td>
					<td>${mps_atp_adjust.period_4}</td>
					<td>${mps_atp_adjust.period_5}</td>
					<td>${mps_atp_adjust.period_6}</td>
					<td>${mps_atp_adjust.period_7}</td>
					<td></td>
			</tr>
		</table>
		</div>
		<div class="chartReport">
			 <script type="text/javascript">
			    var chart;
				var chartData = [{country:"Period_0", visits:'${mps_gr.period_0}', color:"#FF0F00"},
						{country:"Period_1",visits:'${mps_gr.period_1}',color:"#FF6600"},
						{country:"Period_2",visits:'${mps_gr.period_2}',color:"#FF9E01"},
						{country:"Period_3",visits:'${mps_gr.period_3}',color:"#FCD202"},
						{country:"Period_4",visits:'${mps_gr.period_4}',color:"#F8FF01"},
						{country:"Period_5",visits:'${mps_gr.period_5}',color:"#B0DE09"},
						{country:"Period_6",visits:'${mps_gr.period_6}',color:"#04D215"},
						{country:"Period_7",visits:'${mps_gr.period_7}',color:"#0D8ECF"}];
				
				var chart = new AmCharts.AmSerialChart();
				chart.dataProvider = chartData;
				chart.categoryField = "country";
				chart.marginTop = 25;
				chart.marginBottom = 80;
				chart.marginLeft = 55;
				chart.marginRight = 25;
				chart.startDuration = 1;

				var graph = new AmCharts.AmGraph();
				graph.valueField = "visits";
				graph.colorField = "color";
				graph.balloonText="[[category]]: [[value]]";
				graph.type = "column";
				graph.lineAlpha = 0;
				graph.fillAlphas = 0.8;
				chart.addGraph(graph);

				var catAxis = chart.categoryAxis;
				catAxis.gridPosition = "start";
				catAxis.autoGridCount = true;

				chart.write("chartdiv");
	    	</script>
			<div id="chartdiv" style="width:600px; height:400px; background-color:#FFFFFF"></div>
		</div>
	</div>
</body>
</html>