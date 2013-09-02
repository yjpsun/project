/**
 * wooiioog@gmail.com
 * May 4, 20112:30:59 PM
 */
package org.erplab.service.mps.report;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Bom;
import org.erplab.domain.Forecast;
import org.erplab.domain.Material;
import org.erplab.domain.MaterialDelivering;
import org.erplab.domain.MaterialStock;
import org.erplab.service.mps.ForecastService;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.OrderService;
import org.erplab.utils.DateTool;
import org.erplab.utils.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;

import com.google.common.collect.Maps;

/**
 * @author wanggang
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class BomReportServiceImpl extends AbstractBeanService implements BomReportService {

	@Autowired
	private ForecastService forecastService;
	@Autowired
	private MaterialService materialService;

	@Autowired
	private MpsReportService mpsReportService;
	
	@Autowired
	private OrderService orderService;
	
	
	
	@Override
	public Map<String, Float> getMPS_GR(Long materialId) {
		return c_rs_gr;
	}

	@Override
	public Float getMPS_GR_byPeriod(Long materialId, int period) {
		return c_rs_gr.get("period_" + period);
	}

	@Override
	public Float getMPS_GR_M_N(Long materialId, int m, int n) {
		Float sumGrMN = 0f;
		n = n > 8 ? 8 : n;
		for (int i = m + 1; i < n + 1; i++) {
			sumGrMN = sumGrMN + this.getMPS_GR_byPeriod(materialId, i);
		}
		return NumberTool.round1Point(sumGrMN);
	}

	@Override
	public Map<String, Object> getMPS_Period(Long materialId) {
		Map<String, Object> rs_period = Maps.newHashMap();
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		if (CollectionUtils.isNotEmpty(forecastList)) {
			for (int i = 0; i < forecastList.size(); i++) {
				if (forecastList.get(i) != null) {
					rs_period.put("period_" + i, forecastList.get(i).getWeekStartF1());
				}
			}
		}
		return rs_period;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Float> getMPS_SR(Long materialId) {

		Map<String, Float> rs_sr = Maps.newHashMap();
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		if (CollectionUtils.isNotEmpty(forecastList)) {
			for (int i = 0; i < forecastList.size(); i++) {
				if (forecastList.get(i) != null) {
					List<MaterialDelivering> mList = this.getPersistenceService().findByNamedOfQuery(
							"getMDByMidAndRecDate", materialId, DateTool.removeTimeValue(forecastList.get(i).getWeekStart()));
					if (CollectionUtils.isNotEmpty(mList)) {
						MaterialDelivering materialDelivering = mList.get(0);
						rs_sr.put("period_" + i, NumberTool.round1Point(materialDelivering.getSr()));
					} else {
						rs_sr.put("period_" + i, 0f);
					}
				}
			}
		}
		return rs_sr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Float getMPS_SR_byPeriod(Long materialId, int period) {
		Float sr = 0f;
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		if (CollectionUtils.isNotEmpty(forecastList)) {
			if (forecastList.get(period - 1) != null) {
				List<MaterialDelivering> mList = this.getPersistenceService().findByNamedOfQuery(
						"getMDByMidAndRecDate", materialId, DateTool.removeTimeValue(forecastList.get(period - 1).getWeekStart()));
				if (CollectionUtils.isNotEmpty(mList)) {
					sr = mList.get(0).getSr();
				}
			}
		}
		sr = (sr == null ? 0f : sr);
		return NumberTool.round1Point(sr);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Float> getMPS_OH(Long materialId) {
		Map<String, Float> rs_oh = Maps.newHashMap();
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		if (CollectionUtils.isNotEmpty(forecastList)) {
			for (int i = 0; i < forecastList.size(); i++) {
				if (forecastList.get(i) != null) {
					List<MaterialStock> mList = this.getPersistenceService().findByNamedOfQuery(
							"getMSByMidAndRecDate", materialId, DateTool.removeTimeValue(forecastList.get(i).getWeekStart()));
					if (CollectionUtils.isNotEmpty(mList)) {
						MaterialStock materialStock = mList.get(0);
						rs_oh.put("period_" + i, NumberTool.round1Point(materialStock.getOh()));
					} else {
						rs_oh.put("period_" + i, 0f);
					}
				}
			}
		}
		return rs_oh;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Float getMPS_OH_byPeriod(Long materialId, int period) {
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		Float oh = 0f;
		if (CollectionUtils.isNotEmpty(forecastList)) {
			if (forecastList.get(period - 1) != null) {
				List<MaterialStock> mList = this.getPersistenceService().findByNamedOfQuery(
						"getMSByMidAndRecDate", materialId, DateTool.removeTimeValue(forecastList.get(period - 1).getWeekStart()));
				if (CollectionUtils.isNotEmpty(mList)) {
					oh = mList.get(0).getOh();
				}
			}
		}
		return NumberTool.round1Point(oh);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Float> getMPS_AL(Long materialId) {
		Map<String, Float> rs_al = Maps.newHashMap();
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		if (CollectionUtils.isNotEmpty(forecastList)) {
			for (int i = 0; i < forecastList.size(); i++) {
				if (forecastList.get(i) != null) {
					List<MaterialStock> mList = this.getPersistenceService().findByNamedOfQuery(
							"getMSByMidAndRecDate", materialId, DateTool.removeTimeValue(forecastList.get(i).getWeekStart()));
					if (CollectionUtils.isNotEmpty(mList)) {
						MaterialStock materialStock = mList.get(0);
						rs_al.put("period_" + i, NumberTool.round1Point(materialStock.getAl()));
					} else {
						rs_al.put("period_" + i, 0f);
					}
				}
			}
		}
		return rs_al;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Float getMPS_AL_byPeriod(Long materialId, int period) {
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		Float al = 0f;
		if (CollectionUtils.isNotEmpty(forecastList)) {
			if (forecastList.get(period - 1) != null) {
				List<MaterialStock> mList = this.getPersistenceService().findByNamedOfQuery(
						"getMSByMidAndRecDate", materialId, DateTool.removeTimeValue(forecastList.get(period - 1).getWeekStart()));
				if (CollectionUtils.isNotEmpty(mList)) {
					al = mList.get(0).getAl();
				}
			}
		}
		return NumberTool.round1Point(al);
	}

	@Override
	public Map<String, Float> getMPS_POH(Long materialId) {
		Map<String, Float> rs_poh = Maps.newHashMap();
		for (int i = 1; i <= 8; i++) {
			rs_poh.put("period_" + (i - 1), this.getMPS_POH_byPeriod(materialId, i));
		}
		return rs_poh;
	}

	@Override
	public Float getMPS_POH_byPeriod(Long materialId, int period) {
		Float poh = 0f;
		if (period == 1) {
			Float oh_1 = this.getMPS_OH_byPeriod(materialId, 1);
			Float sr_0 = 0f;
			Float sr_1 = this.getMPS_SR_byPeriod(materialId, 1);

			Float al_1 = this.getMPS_AL_byPeriod(materialId, 1);
			Float gr_1 = this.getMPS_GR_byPeriod(materialId, 1);

			poh = oh_1 + sr_0 + sr_1 - al_1 - gr_1;
		} else {
			Float pab_pre = this.getMPS_PAB_byPeriod(materialId, period - 1);
			Float sr = this.getMPS_SR_byPeriod(materialId, period);
			Float gr = this.getMPS_GR_byPeriod(materialId, period);
			poh = pab_pre + sr - gr;
		}
		return NumberTool.round1Point(poh);
	}

	@Override
	public Map<String, Float> getMPS_NR(Long materialId) {
		Map<String, Float> rs_nr = Maps.newHashMap();
		for (int i = 1; i <= 8; i++) {
			rs_nr.put("period_" + (i - 1), this.getMPS_NR_byPeriod(materialId, i));
		}
		return rs_nr;
	}

	@Override
	public Float getMPS_NR_byPeriod(Long materialId, int period) {
		Material material = materialService.findMaterialById(materialId);
		Float ss = material.getMatSs() == null ? 0f : material.getMatSs();
		Float yield = material.getYield() == null ? 0f : material.getYield();

		Float nr = 0f;
		Float poh = this.getMPS_POH_byPeriod(materialId, period);
		if (poh < ss) {
			if (yield != 0) {
				nr = (ss - poh) / yield;
			}
		} else {
			nr = 0f;
		}
		return NumberTool.round1Point(nr);
	}

	@Override
	public Map<String, Float> getMPS_PORC(Long materialId) {
		Map<String, Float> rs_porc = Maps.newHashMap();
		for (int i = 1; i <= 8; i++) {
			rs_porc.put("period_" + (i - 1), this.getMPS_PORC_byPeriod(materialId, i));
		}
		return rs_porc;
	}

	@Override
	public Float getMPS_PORC_byPeriod(Long materialId, int period) {
		Material material = materialService.findMaterialById(materialId);
		String lsr = material.getMatLsr();
		Float purInt = material.getPurInt();
		Float minPur = material.getMinpur();
		Float ls = material.getMatLs() == null ? 0f:material.getMatLs();
		//Long mul = material.getMul();

		Float proc = 0f;
		Float nr = this.getMPS_NR_byPeriod(materialId, period);

		if (nr == 0f) {
			proc = 0f;
		} else {
			if ("LFL".equals(lsr)) {
				if (nr <= minPur) {
					proc = minPur;
				}
				else {
					//proc = ls * mul;
					proc = nr;
				}

			}
			if ("POQ".equals(lsr)) {
				if (nr > 0) {
					Float tempPorc = nr
							+ this.getMPS_GR_M_N(materialId, period, purInt.intValue() + period - 1);
					if (tempPorc <= minPur) {
						proc = minPur;
					} else {
						proc = tempPorc;
					}
				} else {
					proc = 0f;
				}
			}
			if ("FOQ".equals(lsr)) {
				if (nr <= ls) {
					proc = ls;
				} else {
					if(ls != 0f){
						proc = (float)Math.ceil(nr/ls)*ls;
					}
				}
			}
		}
		return NumberTool.round1Point(proc);
	}

	@Override
	public Map<String, Float> getMPS_PAB(Long materialId) {
		Map<String, Float> rs_pab = Maps.newHashMap();
		for (int i = 1; i <= 8; i++) {
			rs_pab.put("period_" + (i - 1), this.getMPS_PAB_byPeriod(materialId, i));
		}
		return rs_pab;
	}

	@Override
	public Float getMPS_PAB_byPeriod(Long materialId, int period) {
		Float poh = this.getMPS_POH_byPeriod(materialId, period);
		Float porc = this.getMPS_PORC_byPeriod(materialId, period);
		return NumberTool.round1Point(poh + porc);
	}

	@Override
	public Map<String, Float> getMPS_POR(Map<String, Float> porcMap, Long lt) {
		Map<String, Float> rs_por = Maps.newHashMap();
		Float por_1 = 0f;
		if (lt != null) {
			if (lt > 8) {
				lt = 8l;
			}
			if (lt < 0) {
				lt = 0l;
			}
			for (int i = 0; i < lt; i++) {
				por_1 = porcMap.get("period_" + i) + por_1;
			}
			rs_por.put("period_0", NumberTool.round1Point(por_1));
			for (int i = lt.intValue(); i < 8; i++) {
				rs_por.put("period_" + (i - lt + 1),NumberTool.round1Point(
						porcMap.get("period_" + i) != null ? porcMap.get("period_" + i) : 0f));
			}
			for (int i = 9 - lt.intValue(); i < 9; i++) {
				rs_por.put("period_" + i, 0f);
			}
		}
		return rs_por;
	}

	@Override
	public Map<String, Map<String, Float>> getMainAllItem(Material mat) {
		Map<String, Map<String, Float>> rs_all = Maps.newHashMap();
		Long materialId = mat.getId();
		Map<String, Float> gr = mpsReportService.getMPS_GR(materialId);
		Map<String, Float> gr_new = Maps.newHashMap();
		gr_new.put("period_"+0, 0f);
		for(int i=0;i<8;i++){
			gr_new.put("period_"+(i+1), gr.get("period_"+i));
		}
		
		rs_all.put("rs_gr", gr_new);
		
		
		rs_all.put("rs_sr", mpsReportService.getMPS_SR(materialId));
		rs_all.put("rs_poh", mpsReportService.getMPS_POH(materialId));
		rs_all.put("rs_pab", mpsReportService.getMPS_PAB(materialId));
		rs_all.put("rs_nr", mpsReportService.getMPS_NR(materialId));
		Map<String, Float> rs_porc = mpsReportService.getMPS_PORC(materialId);
		rs_all.put("rs_porc", rs_porc);
		rs_all.put("rs_por", mpsReportService.getMPS_POR(rs_porc, mat.getMatLt()));
		rs_all.put("rs_sumption", this.getMPS_Sumption(materialId));
		rs_all.put("rs_mps", mpsReportService.getMPS_MPS(materialId));
		rs_all.put("rs_atp", mpsReportService.getMPS_ATP(materialId));
		
		rs_all.put("rs_oh", mpsReportService.getMPS_OH(materialId));
		rs_all.put("rs_al", mpsReportService.getMPS_AL(materialId));
		
		return rs_all;
	}

	Map<String, Float> c_rs_gr = Maps.newHashMap();

	@Override
	public Map<String, Map<String, Float>> getChildAllItem(Map<String, Map<String, Float>> curParentRs,
			Bom bom, Material mat,Map<String, Float> rs_gr,String flag) {

		Map<String, Map<String, Float>> rs_c_all = Maps.newHashMap();

		Map<String, Float> p_rs_por = curParentRs.get("rs_por");
		Long materialId = mat.getId();

		if(rs_gr == null){
			Float temp_c_gr = 0f;
			Float sumption = 0f;
			Float qp = bom.getQp();
			Integer ot = bom.getOt();
			Float scraptate = bom.getScraprate() == null ? 0f : bom.getScraprate();
			Float temp_p_por_ot = 0f;

			Float c_gr_0 = 0f;
			Float sumOtPor = 0f;
			for (int i = 0; i < 9; i++) {
				if(!"report-gr".equals(flag)){
					sumption = getMPS_Sumption_byPeriod(materialId,i);
				}
				
				if(i+ot>8){
					temp_p_por_ot = 0f;
				}else{
					temp_p_por_ot = p_rs_por.get("period_" + (i + ot));
				}
				if (scraptate == 1f) {
					temp_c_gr = 0f;
				} else {
					if (i == 0) {
						for(int j=0;j<=ot;j++){
							if(p_rs_por.get("period_" + j) != null){
								sumOtPor = p_rs_por.get("period_" + j) + sumOtPor;
							}
						}
						temp_c_gr = sumOtPor * qp / (1f - scraptate) + sumption;
						c_gr_0 = temp_c_gr;
					} else if (i == 1) {
						temp_c_gr = temp_p_por_ot * qp / (1f - scraptate) + sumption + c_gr_0;
					} else {
						temp_c_gr = temp_p_por_ot * qp / (1f - scraptate) + sumption;
					}
				}
				c_rs_gr.put("period_" + i, NumberTool.round1Point(temp_c_gr));
			}

			Map<String, Float> c_rs_gr_clone = Maps.newHashMap();
			for(String gr_key:c_rs_gr.keySet()){
				c_rs_gr_clone.put(gr_key, c_rs_gr.get(gr_key));
			}
			rs_c_all.put("rs_gr", c_rs_gr_clone);
		}else{
			Map<String, Float> c_rs_gr_clone = Maps.newHashMap();
			for(String gr_key:rs_gr.keySet()){
				c_rs_gr_clone.put(gr_key, rs_gr.get(gr_key));
			}
			rs_c_all.put("rs_gr", c_rs_gr_clone);
		}
		
		rs_c_all.put("rs_sr", this.getMPS_SR(materialId));
		rs_c_all.put("rs_poh", this.getMPS_POH(materialId));
		rs_c_all.put("rs_pab", this.getMPS_PAB(materialId));
		rs_c_all.put("rs_nr", this.getMPS_NR(materialId));
		Map<String, Float> rs_porc = this.getMPS_PORC(materialId);
		rs_c_all.put("rs_porc", rs_porc);
		rs_c_all.put("rs_por", this.getMPS_POR(rs_porc, mat.getMatLt()));
		rs_c_all.put("rs_sumption", this.getMPS_Sumption(materialId));
		rs_c_all.put("rs_al", this.getMPS_AL(materialId));
		rs_c_all.put("rs_oh", this.getMPS_OH(materialId));
		rs_c_all.put("rs_mps", this.getMPS_MPS(materialId,rs_c_all));
		
		rs_c_all.put("rs_atp", this.getMPS_ATP(materialId,rs_c_all));
		
		return rs_c_all;
	}

	@Override
	public Map<String, Map<String, Float>> tempGetChildAllItem(Map<String, Float> p_rs_por, Bom bom,
			Material mat) {

		Map<String, Map<String, Float>> rs_c_all = Maps.newHashMap();

		Long materialId = mat.getId();

		Float temp_c_gr = 0f;
		Float sumption = 0f;
		Float qp = bom.getQp() == null ? 0f : bom.getQp();
		Integer ot = bom.getOt();
		Float scraptate = bom.getScraprate() == null ? 0f : bom.getScraprate();
		Float temp_p_por_ot = 0f;

		Float c_gr_0 = 0f;
		Float sumOtPor = 0f;
		for (int i = 0; i < 9; i++) {
			sumption = getMPS_Sumption_byPeriod(materialId,i);
			if(i+ot>8){
				temp_p_por_ot = 0f;
			}else{
				temp_p_por_ot = p_rs_por.get("period_" + (i + ot));
			}
			

			if (scraptate == 1f) {
				temp_c_gr = 0f;
			} else {
				if (i == 0) {
					for(int j=0;j<=ot;j++){
						if(p_rs_por.get("period_" + j) != null){
							sumOtPor = p_rs_por.get("period_" + j) + sumOtPor;
						}
					}
					temp_c_gr = sumOtPor * qp / (1f - scraptate) + sumption;
					c_gr_0 = temp_c_gr;
				} else if (i == 1) {
					temp_c_gr = temp_p_por_ot * qp / (1f - scraptate) + sumption + c_gr_0;
				} else {
					temp_c_gr = temp_p_por_ot * qp / (1f - scraptate) + sumption;
				}
			}
			c_rs_gr.put("period_" + i, temp_c_gr);
		}
		
		Map<String, Float> c_rs_gr_clone = Maps.newHashMap();
		for(String gr_key:c_rs_gr.keySet()){
			c_rs_gr_clone.put(gr_key, c_rs_gr.get(gr_key));
		}
		rs_c_all.put("rs_gr", c_rs_gr_clone);
		rs_c_all.put("rs_sr", this.getMPS_SR(materialId));
		rs_c_all.put("rs_poh", this.getMPS_POH(materialId));
		rs_c_all.put("rs_pab", this.getMPS_PAB(materialId));
		rs_c_all.put("rs_nr", this.getMPS_NR(materialId));
		Map<String, Float> rs_porc = this.getMPS_PORC(materialId);
		rs_c_all.put("rs_porc", rs_porc);
		rs_c_all.put("rs_por", this.getMPS_POR(rs_porc, mat.getMatLt()));
		rs_c_all.put("rs_sumption", this.getMPS_Sumption(materialId));
		return rs_c_all;
	}

	@Override
	public Map<String, Float> getMPS_Sumption(Long materialId) {
		Map<String,Float> rs_sum = Maps.newHashMap();
		rs_sum.put("period_0", 0f);
		for(int i=1;i<9;i++){
			rs_sum.put("period_"+i, NumberTool.round1Point(orderService.sumOrderBy(materialId, i)));
		}
		return rs_sum;
	}

	@Override
	public Float getMPS_Sumption_byPeriod(Long materialId,
			int period) {
		return NumberTool.round1Point(orderService.sumOrderBy(materialId, period));
	}

	@Override
	public Map<String, Float> getMPS_MPS(Long materialId,Map<String, Map<String, Float>> rs_c_all) {
		Map<String, Float> rs_mps = Maps.newHashMap();
		for (int i = 1; i <= 8; i++) {
			rs_mps.put("period_" + (i - 1), this.getMPS_MPS_byPeriod(materialId, i,rs_c_all));
		}
		return rs_mps;
	}

	@Override
	public Float getMPS_MPS_byPeriod(Long materialId, int period,Map<String, Map<String, Float>> rs_c_all) {
		Map<String, Float> rs_sr = rs_c_all.get("rs_sr");
		Float sr = rs_sr.get("period_"+(period-1));
		Map<String, Float> rs_porc = rs_c_all.get("rs_porc");
		Float porc = rs_porc.get("period_"+(period-1));
		return NumberTool.round1Point(sr+porc);
	}

	@Override
	public Map<String, Float> getMPS_ATP(Long materialId,Map<String, Map<String, Float>> rs_c_all) {
		Map<String, Float> rs_atp = Maps.newHashMap();
		for (int i = 1; i <= 8; i++) {
			rs_atp.put("period_" + (i - 1), this.getMPS_ATP_byPeriod(materialId, i,rs_c_all));
		}
		return rs_atp;
	}

	@Override
	public Float getMPS_ATP_byPeriod(Long materialId, int period,Map<String, Map<String, Float>> rs_c_all) {
		Float atp = 0f;
		Map<String,Float> mpsMap = this.getMPS_MPS(materialId,rs_c_all);
		
		Float oh = this.getMPS_OH_byPeriod(materialId, 1);
		Float sr_0 = 0f;
		
		int n = 8;
		for(int i=period;i<9;i++){
			if(mpsMap.get("period_"+(i-1))>0){
				n = i;
				break;
			}
		}
		Float mps = this.getMPS_MPS_byPeriod(materialId, period,rs_c_all);
		if(period == 1){
			Float sum_s = this.getSumEnd(materialId, n);
			atp = oh + sr_0 + mps - sum_s;
		}else{
			if(mps == 0f){
				atp = 0f;
			}else if(mps > 0f){
				Float sum_s = this.getSumStart(materialId, n);
				atp = mps - sum_s;
			}
		}
		return atp;
	}
	
	private Float getSumEnd(Long materialId,int n){
		Float rs = 0f;
		for(int i = 1;i<=n;i++){
			rs = this.getMPS_Sumption_byPeriod(materialId, i)+rs;
		}
		return rs;	
	}
	private Float getSumStart(Long materialId,int m){
		Float rs = 0f;
		for(int i = m;i < 9;i++){
			rs = this.getMPS_Sumption_byPeriod(materialId, i)+rs;
		}
		return rs;	
	}
}
