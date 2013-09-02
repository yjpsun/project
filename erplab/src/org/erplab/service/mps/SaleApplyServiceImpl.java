/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.erplab.domain.Material;
import org.erplab.domain.SaleApply;
import org.erplab.domain.Sales;
import org.erplab.service.mps.report.BomReportService;
import org.erplab.service.mps.report.MpsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Service
@SuppressWarnings("unchecked")
public class SaleApplyServiceImpl extends AbstractBeanService<SaleApply, Serializable> implements SaleApplyService {
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private BomReportService bomReportService;
	
	@Autowired
	private MpsReportService mpsReportService;
	
	@Override
	public SaleApply saveSaleApply(SaleApply saleApply) {
		Sales sales = new Sales();
		sales.setDemDate(saleApply.getDemDate());
		sales.setMaterial(saleApply.getMaterial());
		sales.setPurQ(saleApply.getPurQ());
		this.getPersistenceService().merge(sales);
		return (SaleApply)this.getPersistenceService().merge(saleApply);
	}
	
	@Override
	public boolean checkSaleApply(SaleApply saleApply) {
		Material mat = saleApply.getMaterial();
		Map<String,Float> sumOrder = saleService.getPeriodSaleSum(mat);
		Map<String,Map<String,Float>> mps_all = bomReportService.getMainAllItem(mat);
		Map<String,Float> atp_all = mps_all.get("rs_atp");
		int t = getT(mat.getId(),saleApply.getDemDate());
		Float atp = atp_all.get("period_"+t);
		Float sum = sumOrder.get("period_"+t);
		Float sum_1 = sumOrder.get("period_"+(t-1));
		atp = atp == null ? 0f:atp;
		sum = sum == null ? 0f:sum;
		sum_1 = sum_1 == null ? 0f:sum_1;
		
		if(atp-sum_1 >= sum){
			return true;
		}else{
			return false;
		}
	}
	

	private int getT(Long mId, Date curDate){
		Map<String,Date> mps_period = mpsReportService.getMPS_Period_date(mId);
		if(curDate.getTime() < mps_period.get("period_0").getTime()){
			return 0;
		}
		if(curDate.getTime() >= mps_period.get("period_0").getTime() && curDate.getTime() < mps_period.get("period_1").getTime()){
			return 0;
		}
		if(curDate.getTime() >= mps_period.get("period_1").getTime() && curDate.getTime() < mps_period.get("period_2").getTime()){
			return 1;
		}
		if(curDate.getTime() >= mps_period.get("period_2").getTime() && curDate.getTime() < mps_period.get("period_3").getTime()){
			return 2;
		}
		if(curDate.getTime() >= mps_period.get("period_3").getTime() && curDate.getTime() < mps_period.get("period_4").getTime()){
			return 3;
		}
		if(curDate.getTime() >= mps_period.get("period_4").getTime() && curDate.getTime() < mps_period.get("period_5").getTime()){
			return 4;
		}
		if(curDate.getTime() >= mps_period.get("period_5").getTime() && curDate.getTime() < mps_period.get("period_6").getTime()){
			return 51;
		}
		if(curDate.getTime() >= mps_period.get("period_6").getTime() && curDate.getTime() < mps_period.get("period_7").getTime()){
			return 6;
		}
		if(curDate.getTime() >= mps_period.get("period_7").getTime()){
			return 7;
		}
		return 0;
	}
	@Override
	public void removeSaleApply(Long id) {
		this.getPersistenceService().remove(SaleApply.class,id);
	}

	@Override
	public PaginationSupport<SaleApply> findByPagination(SaleApply saleApply,
			int startIndex, int pageSize) {
		String queryHql = "select m from SaleApply m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	
	}

	@Override
	public SaleApply findSaleApplyById(Long id) {
		return this.findById(SaleApply.class, id);
	}

}