/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Material;
import org.erplab.domain.Supplyer;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.SupplyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "supplyer.do", type = "redirectAction")})
public class SupplyerAction extends GenericActionSupport<Supplyer>{

	private static final long serialVersionUID = 1589876464204152946L;
	private Supplyer supplyer;
	private List<Supplyer> supplyerList;
	private List<Material> materialList;
	private Long id;
	
	@Autowired
	private SupplyerService supplyerService;
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<Supplyer> ps = supplyerService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		supplyerList = (List<Supplyer>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			supplyer = supplyerService.findSupplyerById(id);
		}
		return INPUT;
	}

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		supplyerService.removeSupplyer(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		supplyer = supplyerService.saveSupplyer(supplyer);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public Supplyer getModel() {
		return supplyer;
	}
	public Supplyer getSupplyer() {
		return supplyer;
	}

	public void setSupplyer(Supplyer supplyer) {
		this.supplyer = supplyer;
	}


	public List<Supplyer> getSupplyerList() {
		return supplyerList;
	}


	public void setSupplyerList(List<Supplyer> supplyerList) {
		this.supplyerList = supplyerList;
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
