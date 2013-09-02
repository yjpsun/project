/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;

import org.erplab.domain.OutStock;
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
public class OutStockServiceImpl extends AbstractBeanService<OutStock, Serializable> implements OutStockService {

	@Autowired
	SupplyerService supplyerService;
	
	@Override
	public OutStock saveOutStock(OutStock apply) {
		return (OutStock)this.getPersistenceService().merge(apply);
	}

	@Override
	public void removeOutStock(Long id) {
		this.getPersistenceService().remove(OutStock.class,id);
	}

	@Override
	public PaginationSupport<OutStock> findByPagination(OutStock apply,
			int startIndex, int pageSize) {
		String queryHql = "select m from OutStock m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	
	}

	@Override
	public OutStock findOutStockById(Long id) {
		return this.findById(OutStock.class, id);
	}

	@Override
	public List<OutStock> findOutStockByMatId(Long matId) {
		String queryHql = "select s from OutStock s where s.material.id ="+matId;
		return this.getPersistenceService().find(queryHql);
	}
}
