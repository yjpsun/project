/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Material;
import org.erplab.domain.OutStock;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.OutStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "out-stock.do", type = "redirectAction")})
public class OutStockAction extends GenericActionSupport<OutStock>{

	private static final long serialVersionUID = 2416828758254925848L;
	
	private OutStock outStock;
	private List<OutStock> outStockList;
	private List<Material> materialList;
	private Long id;
	
	@Autowired
	private OutStockService outStockService;
	
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<OutStock> ps = outStockService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		outStockList = (List<OutStock>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			outStock = outStockService.findOutStockById(id);
		}
		return INPUT;
	}
	
	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		outStockService.removeOutStock(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		outStock = outStockService.saveOutStock(outStock);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public OutStock getModel() {
		return outStock;
	}
	public OutStock getOutStock() {
		return outStock;
	}

	public void setOutStock(OutStock outStock) {
		this.outStock = outStock;
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

	public List<OutStock> getOutStockList() {
		return outStockList;
	}

	public void setOutStockList(List<OutStock> outStockList) {
		this.outStockList = outStockList;
	}
}
