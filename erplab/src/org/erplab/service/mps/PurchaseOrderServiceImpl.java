/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Material;
import org.erplab.domain.PurchaseOrder;
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
public  class PurchaseOrderServiceImpl extends AbstractBeanService<PurchaseOrder, Serializable> implements PurchaseOrderService {

	@Override
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
		return (PurchaseOrder)this.getPersistenceService().merge(purchaseOrder);
	}

	@Override
	public void removePurchaseOrder(Long id) {
		this.getPersistenceService().remove(PurchaseOrder.class,id);
	}

	@Override
	public PaginationSupport<PurchaseOrder> findByPagination(PurchaseOrder purchaseOrder,
			int startIndex, int pageSize) {
		String queryHql = "select m from PurchaseOrder m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);
	}

	@Override
	public PurchaseOrder findPurchaseOrderById(Long id) {
		return this.findById(PurchaseOrder.class, id);
	}

	@Override
	public PurchaseOrder findPurchaseOrderByMaterial(Material mat) {
		if(mat == null) return null;
		String queryHql = "select m from PurchaseOrder m where m.material.code='"+mat.getCode()+"'";
		List<PurchaseOrder> supList = this.getPersistenceService().find(queryHql);
		if(CollectionUtils.isNotEmpty(supList)){
			return supList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<PurchaseOrder> findPurchaseListByMat(Long id) {
		String queryHql = "select m from PurchaseOrder m where m.material.id="+id;
		List<PurchaseOrder> supList = this.getPersistenceService().find(queryHql);
		return supList;
	}
}
