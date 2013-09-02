/**
 * 
 */
package org.erplab.service.mps;

import org.erplab.domain.SaleApply;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface SaleApplyService {
	
	public SaleApply saveSaleApply(SaleApply saleApply);

	public void removeSaleApply(Long id);
	
	public PaginationSupport<SaleApply> findByPagination(SaleApply saleApply,int startIndex,
			int pageSize);
	
	public SaleApply findSaleApplyById(Long id);
	
	public boolean checkSaleApply(SaleApply saleApply);
}
