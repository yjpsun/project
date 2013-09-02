/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Material;
import org.erplab.domain.StockCost;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.StockCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "stock-cost.do", type = "redirectAction")})
public class StockCostAction extends GenericActionSupport<StockCost>{
	
	private static final long serialVersionUID = 1589876464204152946L;
	private StockCost stockCost;
	private List<StockCost> stockCostList;
	private List<Material> materialList;
	private Long id;
	
	@Autowired
	private StockCostService stockCostService;
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<StockCost> ps = stockCostService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		stockCostList = (List<StockCost>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			stockCost = stockCostService.findStockCostById(id);
		}
		return INPUT;
	}

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		stockCostService.removeStockCost(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		stockCost = stockCostService.saveStockCost(stockCost);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public StockCost getModel() {
		return stockCost;
	}
	public StockCost getStockCost() {
		return stockCost;
	}

	public void setStockCost(StockCost stockCost) {
		this.stockCost = stockCost;
	}


	public List<StockCost> getStockCostList() {
		return stockCostList;
	}


	public void setStockCostList(List<StockCost> stockCostList) {
		this.stockCostList = stockCostList;
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
