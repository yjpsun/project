/**
 * wooiioog@gmail.com
 * May 4, 20112:30:42 PM
 */
package org.erplab.service.mps.report;

import java.util.Date;
import java.util.Map;

/**
 * @author wanggang
 * 
 */
public interface MpsReportService {

	public Map<String, Float> getMPS_GR(Long materialId);

	public Float getMPS_GR_byPeriod(Long materialId, int period);

	public Float getMPS_GR_M_N(Long materialId, int m, int n);

	public Map<String, Float> getMPS_SR(Long materialId);

	public Float getMPS_SR_byPeriod(Long materialId, int period);

	public Map<String, Float> getMPS_OH(Long materialId);

	public Float getMPS_OH_byPeriod(Long materialId, int period);

	public Map<String, Float> getMPS_AL(Long materialId);

	public Float getMPS_AL_byPeriod(Long materialId, int period);

	public Map<String, Float> getMPS_NR(Long materialId);

	public Float getMPS_NR_byPeriod(Long materialId, int period);

	public Map<String, Float> getMPS_PORC(Long materialId);

	public Float getMPS_PORC_byPeriod(Long materialId, int period);
	
	public Map<String, Float> getMPS_POR(Map<String, Float> porcMap,Long lt);

	public Map<String, Float> getMPS_POH(Long materialId);

	public Float getMPS_POH_byPeriod(Long materialId, int period);

	public Map<String, Float> getMPS_PAB(Long materialId);

	public Float getMPS_PAB_byPeriod(Long materialId, int period);

	public Map<String, Object> getMPS_Period(Long materialId);
	
	public Map<String, Date> getMPS_Period_date(Long materialId);
	
	public Map<String, Float> getMPS_MPS(Long materialId);

	public Float getMPS_MPS_byPeriod(Long materialId, int period);
	
	public Map<String, Float> getMPS_ATP(Long materialId);

	public Float getMPS_ATP_byPeriod(Long materialId, int period);
}
