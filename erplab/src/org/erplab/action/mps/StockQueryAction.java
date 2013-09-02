/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.domain.Material;
import org.erplab.domain.StockQuery;
import org.erplab.service.mps.InventoryService;
import org.erplab.service.mps.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "stock.do", type = "redirectAction")})
public class StockQueryAction extends GenericActionSupport<StockQuery>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8107638650040617847L;
	private StockQuery stockQuery;
	private List<Material> materialList;
	private Long id;
	private Float matSr = 0f;
	
	@Autowired
	private MaterialService materialService;
	@Autowired
	private InventoryService inventoryService;

	@Override
	public String list() throws Exception {
		materialList = materialService.findAllMaterial();
		if(id != null){
			stockQuery = inventoryService.queryStock(id);
			if(stockQuery != null){
				stockQuery.setMatSr(matSr);
				stockQuery.setSr(stockQuery.getSr() + matSr);
			}
		}
		return SUCCESS;
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

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String remove() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public StockQuery getStockQuery() {
		return stockQuery;
	}

	public void setStockQuery(StockQuery stockQuery) {
		this.stockQuery = stockQuery;
	}

	@Override
	public StockQuery getModel() {
		// TODO Auto-generated method stub
		return stockQuery;
	}

	public Float getMatSr() {
		return matSr;
	}

	public void setMatSr(Float matSr) {
		this.matSr = matSr;
	}
}
