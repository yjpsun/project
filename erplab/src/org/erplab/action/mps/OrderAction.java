/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Forecast;
import org.erplab.domain.Material;
import org.erplab.domain.Order;
import org.erplab.service.mps.ForecastService;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.util.CollectionUtils;
import org.workin.web.struts2.GenericActionSupport;

import com.google.common.collect.Sets;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "orderSum", location = "/WEB-INF/content/mps/order-sum.jsp"),
	@Result(name = "reload", location = "order.do", type = "redirectAction")})
public class OrderAction extends GenericActionSupport<Order>{

	private static final long serialVersionUID = -3915469472117145994L;
	private Order order;
	private List<Order> orderList;
	private List<Material> materialList;
	private Long id;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private ForecastService forecastService;
	

	@Override
	public String list() throws Exception {
		materialList = materialService.findAllMaterial();
		PaginationSupport<Order> ps = orderService.findByPagination(order,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		orderList = (List<Order>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			order = orderService.findOrderById(id);
		}
		return INPUT;
	}
	
	@SuppressWarnings("unchecked")
	public String orderSum() throws Exception{
		List<Forecast> forecastList = forecastService.findAllForecast();
		Set<Material> temMatSet = Sets.newHashSet();
		if(!CollectionUtils.isEmpty(forecastList)){
			
			for(Forecast forecast:forecastList){
				if(forecast != null){
					temMatSet.add(forecast.getMaterial());
				}
			}
		}
		List<Material> temMatList = CollectionUtils.arrayToList(temMatSet.toArray());
		materialList = CollectionUtils.removeNullObject(temMatList);
		return "orderSum";
	}

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		orderService.removeOrder(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		order = orderService.saveOrder(order);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public Order getModel() {
		return order;
	}
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	public List<Order> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}
}
