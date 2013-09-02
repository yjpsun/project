/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Material;
import org.erplab.domain.Sales;
import org.erplab.service.mps.report.MpsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;
import org.workin.core.persistence.support.PaginationSupport;

import com.google.common.collect.Maps;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Service
@SuppressWarnings("unchecked")
public class SaleServiceImpl extends AbstractBeanService<Sales, Serializable> implements SaleService {

	@Autowired
	private MpsReportService mpsReportService;
	
	@Override
	public Sales saveSales(Sales sales) {
		return (Sales)this.getPersistenceService().merge(sales);
	}

	@Override
	public void removeSales(Long id) {
		this.getPersistenceService().remove(Sales.class,id);
	}

	@Override
	public PaginationSupport<Sales> findByPagination(Sales sales,
			int startIndex, int pageSize) {
		String queryHql = "select m from Sales m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	
	}

	@Override
	public Sales findSalesById(Long id) {
		return this.findById(Sales.class, id);
	}

	@Override
	public Map<String, Float> getPeriodSaleSum(Material mat) {
		Map<String,Date> mps_period = mpsReportService.getMPS_Period_date(mat.getId());
		Map<String,Float> saleSum = Maps.newHashMap(); 
		for(int i=0;i<8;i++){
			Float sumValue = 0f;
			if(i<7){
				Date pDate_st = mps_period.get("period_"+i);
				Date pDate_ed = mps_period.get("period_"+(i+1));
				List<Double> sumList = this.getPersistenceService().findByNamedOfQuery("getSaleSum",mat.getId(),pDate_st,pDate_ed);
				if(CollectionUtils.isNotEmpty(sumList)){
					if(sumList.get(0) != null){
						sumValue = sumList.get(0).floatValue();
					}else{
						sumValue = 0f;
					}
				}
			}else{
				Date pDate_st = mps_period.get("period_"+i);
				List<Double> sumList = this.getPersistenceService().findByNamedOfQuery("getSaleSum2",mat.getId(),pDate_st);
				if(CollectionUtils.isNotEmpty(sumList)){
					if(sumList.get(0) != null){
						sumValue = sumList.get(0).floatValue();
					}else{
						sumValue = 0f;
					}
				}
			}
			saleSum.put("period_"+i,sumValue);
		}
		return saleSum;
	}

}