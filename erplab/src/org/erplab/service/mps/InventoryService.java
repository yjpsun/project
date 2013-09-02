/**
 * 
 */
package org.erplab.service.mps;

import java.util.Map;

import org.erplab.domain.Inventory;
import org.erplab.domain.StockQuery;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface InventoryService {
	
	public Inventory saveInventory(Inventory inventory);

	public void removeInventory(Long id);
	
	public PaginationSupport<Inventory> findByPagination(Inventory inventory,int startIndex,
			int pageSize);
	
	public Inventory findInventoryById(Long id);
	
	public Map<String,Float> loadBookValue(Long matId);
	
	public StockQuery queryStock(Long matId);
}
