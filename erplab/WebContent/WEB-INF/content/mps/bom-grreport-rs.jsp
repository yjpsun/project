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
				
				<s:iterator value="grList" id="mps_gr" status="st">
					<tr>
						<td>
							<s:if test="#st.index==0">
								GR							
							</s:if>
						</td>
						<td>${period_0}</td>
						<td>${period_1}</td>
						<td>${period_2}</td>
						<td>${period_3}</td>
						<td>${period_4}</td>
						<td>${period_5}</td>
						<td>${period_6}</td>
						<td>${period_7}</td>
						<td>${period_8}</td>
						<td></td>
					</tr>	
				</s:iterator>
			</s:iterator>
		</table>
		</div>
		<div class="chartReport">
		</div>
	</div>
</body>
</html>