/**
 * 
 */
package org.erplab.service.mps;

import java.util.Map;

import org.erplab.domain.Material;
import org.erplab.domain.Sales;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface SaleService {
	
	public Sales saveSales(Sales sales);

	public void removeSales(Long id);
	
	public PaginationSupport<Sales> findByPagination(Sales sales,int startIndex,
			int pageSize);
	
	public Sales findSalesById(Long id);
	
	public Map<String,Float> getPeriodSaleSum(Material mat);
}
