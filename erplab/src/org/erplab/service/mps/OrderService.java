/**
 * 
 */
package org.erplab.service.mps;

import org.erplab.domain.Order;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface OrderService {
	
	public Order saveOrder(Order order);

	public void removeOrder(Long id);
	
	public PaginationSupport<Order> findByPagination(Order order,int startIndex,
			int pageSize);
	
	public Order findOrderById(Long id);
	
	public Float sumOrderBy(Long materialId,int period);
}
