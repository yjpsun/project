/**
 * 
 */
package org.erplab.service.mps;

import java.util.List;

import org.erplab.domain.OutStock;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface OutStockService {
	
	public OutStock saveOutStock(OutStock outStock);

	public void removeOutStock(Long id);
	
	public PaginationSupport<OutStock> findByPagination(OutStock outStock,int startIndex,
			int pageSize);
	
	public OutStock findOutStockById(Long id);
	
	public List<OutStock> findOutStockByMatId(Long matId);
}
