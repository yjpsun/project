/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;

import org.erplab.domain.StockCost;
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
public class StockCostServiceImpl extends AbstractBeanService<StockCost, Serializable> implements StockCostService {

	@Override
	public StockCost saveStockCost(StockCost stockCost) {
		return (StockCost)this.getPersistenceService().merge(stockCost);
	}

	@Override
	public void removeStockCost(Long id) {
		this.getPersistenceService().remove(StockCost.class,id);
	}

	@Override
	public PaginationSupport<StockCost> findByPagination(StockCost stockCost,
			int startIndex, int pageSize) {
		String queryHql = "select s from StockCost s";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public StockCost findStockCostById(Long id) {
		return this.findById(StockCost.class, id);
	}

}
