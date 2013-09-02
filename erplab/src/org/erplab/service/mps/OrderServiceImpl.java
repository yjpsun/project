/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Forecast;
import org.erplab.domain.Order;
import org.erplab.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderServiceImpl extends AbstractBeanService<Order, Serializable> implements OrderService {

	@Autowired
	private ForecastService forecastService;
	
	@Override
	public Order saveOrder(Order order) {
		return (Order)this.getPersistenceService().merge(order);
	}

	@Override
	public void removeOrder(Long id) {
		this.getPersistenceService().remove(Order.class,id);
	}

	@Override
	public PaginationSupport<Order> findByPagination(Order order,
			int startIndex, int pageSize) {
		String queryHql = "select m from Order m";
		
		if(order != null && order.getMaterial().getId() != null){
			queryHql = queryHql + " where m.material.id = "+order.getMaterial().getId();
		}
		
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public Order findOrderById(Long id) {
		return this.findById(Order.class, id);
	}

	@Override
	public Float sumOrderBy(Long materialId, int period) {
		Date periodStart = null; 
		Date periodEnd = null;
		if(period == 0){
			return 0f;
		}
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		if (CollectionUtils.isNotEmpty(forecastList)) {
			for (int i = 0; i < forecastList.size(); i++) {
				if (forecastList.get(i) != null && period-1 == i) {
					periodStart = forecastList.get(i).getWeekStart();
					if(i<7){
						periodEnd = forecastList.get(i+1).getWeekStart();
					}else{
						periodEnd = DateTool.nextNday(periodStart,7);
					}
					
					break;
				}
			}
		}
		
		List<Order> orderList = this.getPersistenceService().findByNamedOfQuery("orderSumQuery", materialId,periodStart,periodEnd);
		Float sumValue = 0f;
		if(CollectionUtils.isNotEmpty(orderList)){
			for(Order order:orderList){
				if(order != null){
					sumValue = order.getQuantity() + sumValue;
				}
			}
		}
		return sumValue;
	}
}
