/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Apply;
import org.erplab.domain.Material;
import org.erplab.domain.PurchaseOrder;
import org.erplab.domain.Supplyer;
import org.erplab.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;
import org.workin.core.persistence.support.PaginationSupport;

import com.google.common.collect.Lists;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Service
@SuppressWarnings("unchecked")
public class ApplyServiceImpl extends AbstractBeanService<Apply, Serializable> implements ApplyService {

	@Autowired
	SupplyerService supplyerService;
	
	@Override
	public Apply saveApply(Apply apply) {
		return (Apply)this.getPersistenceService().merge(apply);
	}

	@Override
	public void removeApply(Long id) {
		this.getPersistenceService().remove(Apply.class,id);
	}

	@Override
	public PaginationSupport<Apply> findByPagination(Apply apply,
			int startIndex, int pageSize) {
		String queryHql = "select m from Apply m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	
	}

	@Override
	public Apply findApplyById(Long id) {
		return this.findById(Apply.class, id);
	}

	@Override
	public List<Apply> findApplySum(Material mat) {
		Long lt = mat.getMatLt() == null?0l:mat.getMatLt();
		Long matId = mat.getId();
		List<Date> minDateList = this.getPersistenceService().findByNamedOfQuery("getMinDate",matId);
		Date minDate = null;
		if(CollectionUtils.isNotEmpty(minDateList)){
			minDate = minDateList.get(0);
		}
		Date endDate = DateTool.nextNday(minDate, lt.intValue());
		List<Apply> applyList = this.getPersistenceService().findByNamedOfQuery("getSumApplyList",matId,minDate,endDate);
		if(CollectionUtils.isNotEmpty(applyList)){
			for(Apply app:applyList){
				Supplyer supplyer = supplyerService.findBestSupplyerByMaterial(mat);
				Float st = 0f;
				if(supplyer != null){
					st = supplyer.getSt() == null?0f:supplyer.getSt();
				}
				Date demDate = app.getDemDate();
				Date purDate = DateTool.nextNday(demDate, -lt.intValue()-st.intValue());
				Date delDate = DateTool.nextNday(demDate, -st.intValue());
				app.setPurDate(purDate);
				app.setDelDate(delDate);
			}
		}
		return applyList;
	}

	@Override
	public List<PurchaseOrder> saveGenerateOrder(List<Apply> applyList,Material mat) {
		List<PurchaseOrder> poList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(applyList)){
			Supplyer supplyer = supplyerService.findBestSupplyerByMaterial(mat);
			Apply appOne = null;
			Float sumption = 0f;
			for(Apply app:applyList){
				if(app != null){
					if(appOne == null){
						appOne = app;
					}else{
						if(appOne.getDemDate().after(app.getDemDate())){
							appOne = app;
						}
					}
					sumption = sumption + app.getPurQ();
				}
			}
			PurchaseOrder po = new PurchaseOrder();
			if(appOne != null){
				po.setDelDate(appOne.getDelDate());
				po.setDemDate(appOne.getDemDate());
				po.setPurDate(appOne.getPurDate());
				po.setPurQ(appOne.getPurQ());
			}
			if(supplyer != null){
				po.setMaterial(supplyer.getMaterial());
				po.setSls(supplyer.getSls());
				po.setSt(supplyer.getSt());
				po.setUnitP(supplyer.getUnitP());
				po.setSupplyerId(supplyer.getId());
				po.setSlsr(supplyer.getSlsr());
			}
			po.setSumption(sumption);
			po = (PurchaseOrder)this.getPersistenceService().merge(po);
			poList.add(po);
		}
		return poList;
	}
}
