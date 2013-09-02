/**
 * wooiioog@gmail.com
 * May 4, 20112:30:59 PM
 */
package org.erplab.service.mps.report;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Forecast;
import org.erplab.domain.Material;
import org.erplab.domain.MaterialDelivering;
import org.erplab.domain.MaterialStock;
import org.erplab.domain.TimeGrating;
import org.erplab.service.mps.ForecastService;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.OrderService;
import org.erplab.service.mps.TimeGratingService;
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
public class MpsReportServiceImpl extends AbstractBeanService implements MpsReportService {

	@Autowired
	private TimeGratingService timeGratingService;
	@Autowired
	private ForecastService forecastService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MaterialService materialService;

	@Override
	public Map<String, Float> getMPS_GR(Long materialId) {
		Map<String, Float> rs_gr = Maps.newHashMap();
		TimeGrating timeGrating = timeGratingService.findTimeGratingByMaterialId(materialId);
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);

		Long dtf = timeGrating.getDtf();
		Long ptf = timeGrating.getPtf();
		Float tempValue = 0f;
		for (int i = 1; i <= 8; i++) {
			Float sumValue = orderService.sumOrderBy(materialId, i);
			Float forecastValue = forecastList.get(i - 1).getWeekForecast();
			if (i < dtf) {
				tempValue = sumValue;
			}
			if (i >= ptf) {
				tempValue = forecastValue;
			}
			if (i >= dtf && i < ptf) {
				tempValue = Math.max(sumValue, forecastValue);
			}
			tempValue = NumberTool.round1Point(tempValue);
			rs_gr.put("period_" + (i - 1), tempValue);
		}
		return rs_gr;
	}

	@Override
	public Float getMPS_GR_byPeriod(Long materialId, int period) {
		Float gr = 0f;
		TimeGrating timeGrating = timeGratingService.findTimeGratingByMaterialId(materialId);
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);

		Long dtf = timeGrating.getDtf();
		Long ptf = timeGrating.getPtf();
		Float sumValue = orderService.sumOrderBy(materialId, period);
		Float forecastValue = forecastList.get(period - 1).getWeekForecast();
		if (period < dtf) {
			gr = sumValue;
		}
		if (period >= ptf) {
			gr = forecastValue;
		}
		if (period >= dtf && period < ptf) {
			gr = Math.max(sumValue, forecastValue);
		}
		return NumberTool.round1Point(gr);
	}

	@Override
	public Float getMPS_GR_M_N(Long materialId, int m, int n) {
		Float sumGrMN = 0f;
		n = n>8?8:n;
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
	@Override
	public Map<String, Date> getMPS_Period_date(Long materialId) {
		Map<String, Date> rs_period = Maps.newHashMap();
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		if (CollectionUtils.isNotEmpty(forecastList)) {
			for (int i = 0; i < forecastList.size(); i++) {
				if (forecastList.get(i) != null) {
					rs_period.put("period_" + i, forecastList.get(i).getWeekStart());
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
						rs_sr.put("period_" + i,NumberTool.round1Point(materialDelivering.getSr()+materialDelivering.getMatSr()));
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
					sr = mList.get(0).getSr()+mList.get(0).getMatSr();
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
							"getMSByMidAndRecDate", materialId,  DateTool.removeTimeValue(forecastList.get(i).getWeekStart()));
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
			if(lt>8){
				lt = 8l;
			}
			if(lt<0){
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
			for(int i = 9-lt.intValue();i<9;i++){
				rs_por.put("period_" + i,0f);
			}
		}
		return rs_por;
	}

	@Override
	public Map<String, Float> getMPS_MPS(Long materialId) {
		Map<String, Float> rs_mps = Maps.newHashMap();
		for (int i = 1; i <= 8; i++) {
			rs_mps.put("period_" + (i - 1), this.getMPS_MPS_byPeriod(materialId, i));
		}
		return rs_mps;
	}

	@Override
	public Float getMPS_MPS_byPeriod(Long materialId, int period) {
		Float sr = this.getMPS_SR_byPeriod(materialId, period);
		Float porc = this.getMPS_PORC_byPeriod(materialId, period);
		return NumberTool.round1Point(sr+porc);
	}

	@Override
	public Map<String, Float> getMPS_ATP(Long materialId) {
		Map<String, Float> rs_atp = Maps.newHashMap();
		for (int i = 1; i <= 8; i++) {
			rs_atp.put("period_" + (i - 1), this.getMPS_ATP_byPeriod(materialId, i));
		}
		return rs_atp;
	}

	@Override
	public Float getMPS_ATP_byPeriod(Long materialId, int period) {
		Float atp = 0f;
		Map<String,Float> mpsMap = this.getMPS_MPS(materialId);
		
		Float oh = this.getMPS_OH_byPeriod(materialId, 1);
		Float sr_0 = 0f;
		
		int n = 8;
		for(int i=period;i<9;i++){
			if(mpsMap.get("period_"+(i-1))>0){
				n = i;
				break;
			}
		}
		Float mps = this.getMPS_MPS_byPeriod(materialId, period);
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
		return NumberTool.round1Point(atp);
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
	
	private Float getMPS_Sumption_byPeriod(Long materialId,
			int period) {
		return NumberTool.round1Point(orderService.sumOrderBy(materialId, period));
	}
}
