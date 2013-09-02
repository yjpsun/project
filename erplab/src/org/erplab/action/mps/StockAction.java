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
import org.erplab.domain.Stock;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "stock.do", type = "redirectAction")})
public class StockAction extends GenericActionSupport<Stock>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8107638650040617847L;
	private Stock stock;
	private List<Stock> stockList;
	private List<Material> materialList;
	private List<PurchaseOrder> poList;
	private Long id;
	
	@Autowired
	private StockService stockService;
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<Stock> ps = stockService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		stockList = (List<Stock>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			stock = stockService.findStockById(id);
		}
		return INPUT;
	}

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		stockService.removeStock(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		stock = stockService.saveStock(stock);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public Stock getModel() {
		return stock;
	}
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}


	public List<Stock> getStockList() {
		return stockList;
	}


	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
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

	public List<PurchaseOrder> getPoList() {
		return poList;
	}

	public void setPoList(List<PurchaseOrder> poList) {
		this.poList = poList;
	}
}
