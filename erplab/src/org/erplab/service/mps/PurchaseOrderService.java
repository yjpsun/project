/**
 * 
 */
package org.erplab.service.mps;

import java.util.List;

import org.erplab.domain.Material;
import org.erplab.domain.PurchaseOrder;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface PurchaseOrderService {
	
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);

	public void removePurchaseOrder(Long id);
	
	public PaginationSupport<PurchaseOrder> findByPagination(PurchaseOrder purchaseOrder,int startIndex,
			int pageSize);
	
	public PurchaseOrder findPurchaseOrderById(Long id);
	
	public PurchaseOrder findPurchaseOrderByMaterial(Material mat);
	
	public List<PurchaseOrder> findPurchaseListByMat(Long id);
}
