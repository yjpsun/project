/**
 * 
 */
package org.erplab.service.mps;

import java.util.List;

import org.erplab.domain.Stock;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface StockService {
	
	public Stock saveStock(Stock stock);

	public void removeStock(Long id);
	
	public PaginationSupport<Stock> findByPagination(Stock stock,int startIndex,
			int pageSize);
	
	public Stock findStockById(Long id);
	
	public List<Stock> findStockByMatId(Long matId);
}
