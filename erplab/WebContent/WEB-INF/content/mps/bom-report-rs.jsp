<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>
<body>
	<div class="reportContainer">
		<div class="dataReport">
			<table border="1"   cellpadding="0"   cellspacing="0" id="contentTable">
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
			<s:iterator value="childBomList" id="cBom" status="st">
				<tr>
					<td  width="12%">${material.code}(LT=${material.matLt})</td>
					<td width="8%">OH=</td>
					<td width="8%">${mps_oh.period_0}</td>
					<td width="8%">AL=</td>
					<td width="8%">${mps_al.period_0}</td>
					<td width="8%">SS=${material.matSs}</td>
					<td width="8%">Yield=${material.yield}</td>
					<td width="8%" class="class_lsr">LSR=${material.matLsr}</td>
					<td width="8%"></td>
					<td width="8%" class="class_ls">LS=${material.matLs}</td>
					<td width="8%" class="class_lsr_n">n=${material.purInt}</td>
				</tr>
				<tr>
					<td>In.Dmd</td>
					<td>${mps_sumption.period_0}</td>
					<td>${mps_sumption.period_1}</td>
					<td>${mps_sumption.period_2}</td>
					<td>${mps_sumption.period_3}</td>
					<td>${mps_sumption.period_4}</td>
					<td>${mps_sumption.period_5}</td>
					<td>${mps_sumption.period_6}</td>
					<td>${mps_sumption.period_7}</td>
					<td>${mps_sumption.period_8}</td>
					<td></td>
				</tr>
				<tr>
					<td>GR</td>
					<td>${mps_gr.period_0}</td>
					<td>${mps_gr.period_1}</td>
					<td>${mps_gr.period_2}</td>
					<td>${mps_gr.period_3}</td>
					<td>${mps_gr.period_4}</td>
					<td>${mps_gr.period_5}</td>
					<td>${mps_gr.period_6}</td>
					<td>${mps_gr.period_7}</td>
					<td>${mps_gr.period_8}</td>
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
				<tr class="rs_atp_${id}">
					<td>MPS</td>
					<td><div class="mat_ps" style="display:none">${matPs}</div></td>
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
				<tr class="rs_atp_${id}">
					<td>ATP</td>
					<td><div class="mat_ps" style="display:none">${matPs}</div></td>
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
				<tr class="rs_atp_${id}">
					<td>ATP Adjust</td>
					<td><div class="mat_ps" style="display:none">${matPs}</div></td>
					<td>${Mps_atp_adjust.period_0}</td>
					<td>${Mps_atp_adjust.period_1}</td>
					<td>${Mps_atp_adjust.period_2}</td>
					<td>${Mps_atp_adjust.period_3}</td>
					<td>${Mps_atp_adjust.period_4}</td>
					<td>${Mps_atp_adjust.period_5}</td>
					<td>${Mps_atp_adjust.period_6}</td>
					<td>${Mps_atp_adjust.period_7}</td>
					<td></td>
				</tr>
				<script type="text/javascript">
					var c_ps = "${matPs}";
					if("Y" != c_ps){
						$(".rs_atp_${id}").remove();
					}
				</script>
			</s:iterator>
		</table>
		</div>
		<div class="chartReport">
			
		</div>
	</div>
</body>
</html>