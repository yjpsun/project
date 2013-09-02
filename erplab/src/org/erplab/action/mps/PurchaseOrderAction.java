/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Material;
import org.erplab.domain.PurchaseOrder;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "purchase-order.do", type = "redirectAction")})
public class PurchaseOrderAction extends GenericActionSupport<PurchaseOrder>{

	private static final long serialVersionUID = 1589876464204152946L;
	private PurchaseOrder purchaseOrder;
	private List<PurchaseOrder> purchaseOrderList;
	private List<Material> materialList;
	private Long id;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<PurchaseOrder> ps = purchaseOrderService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		purchaseOrderList = (List<PurchaseOrder>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			purchaseOrder = purchaseOrderService.findPurchaseOrderById(id);
		}
		return INPUT;
	}

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		purchaseOrderService.removePurchaseOrder(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		purchaseOrder = purchaseOrderService.savePurchaseOrder(purchaseOrder);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public PurchaseOrder getModel() {
		return purchaseOrder;
	}
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}


	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}


	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
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
