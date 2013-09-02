/**
 * 
 */
package org.erplab.service.mps;

import org.erplab.domain.StockCost;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface StockCostService {
	
	public StockCost saveStockCost(StockCost stockCost);

	public void removeStockCost(Long id);
	
	public PaginationSupport<StockCost> findByPagination(StockCost stockCost,int startIndex,
			int pageSize);
	
	public StockCost findStockCostById(Long id);
	
}
