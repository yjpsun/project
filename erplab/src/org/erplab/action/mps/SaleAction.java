/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Material;
import org.erplab.domain.Sales;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "sale.do", type = "redirectAction")})
public class SaleAction extends GenericActionSupport<Sales>{

	private static final long serialVersionUID = 2416828758254925848L;
	
	private Sales sale;
	private List<Sales> saleList;
	private List<Material> materialList;
	private Long id;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<Sales> ps = saleService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		saleList = (List<Sales>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			sale = saleService.findSalesById(id);
		}
		return INPUT;
	}
	
	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		saleService.removeSales(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		sale = saleService.saveSales(sale);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public Sales getModel() {
		return sale;
	}
	public Sales getSales() {
		return sale;
	}

	public void setSales(Sales sale) {
		this.sale = sale;
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

	public List<Sales> getSalesList() {
		return saleList;
	}

	public void setSalesList(List<Sales> saleList) {
		this.saleList = saleList;
	}

	public List<Sales> getSaleList() {
		return saleList;
	}

	public void setSaleList(List<Sales> saleList) {
		this.saleList = saleList;
	}
}
