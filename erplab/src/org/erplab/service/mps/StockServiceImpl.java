/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;

import org.erplab.domain.Stock;
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
public class StockServiceImpl extends AbstractBeanService<Stock, Serializable> implements StockService {

	@Override
	public Stock saveStock(Stock stock) {
		return (Stock)this.getPersistenceService().merge(stock);
	}

	@Override
	public void removeStock(Long id) {
		this.getPersistenceService().remove(Stock.class,id);
	}

	@Override
	public PaginationSupport<Stock> findByPagination(Stock stock,
			int startIndex, int pageSize) {
		String queryHql = "select s from Stock s";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public Stock findStockById(Long id) {
		return this.findById(Stock.class, id);
	}

	@Override
	public List<Stock> findStockByMatId(Long matId) {
		String queryHql = "select s from Stock s where s.material.id ="+matId;
		return this.getPersistenceService().find(queryHql);
	}
}
