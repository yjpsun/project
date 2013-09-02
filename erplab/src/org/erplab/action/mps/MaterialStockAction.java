/**
 * 
 */
package org.erplab.action.mps;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.domain.Material;
import org.erplab.domain.MaterialStock;
import org.erplab.domain.WeekPriod;
import org.erplab.service.mps.MaterialStockService;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "material-stock.do", type = "redirectAction"),
	@Result(name = "reInput", location = "/WEB-INF/content/mps/material-stock.jsp")})
public class MaterialStockAction extends GenericActionSupport<MaterialStock>{

	private static final long serialVersionUID = -4987870986035260874L;
	private MaterialStock materialStock;
	private List<MaterialStock> materialStockList;
	private List<Material> materialList;
	private List<WeekPriod> curPeriodList;
	private Long materialId;
	private Date recDate;

	@Autowired
	private MaterialStockService materialStockService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private CommonService commonService;
	
	private Long id;

	@Override
	public String list() throws Exception {
		materialList = materialService.findAllMaterial();
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			materialStock = materialStockService.findMaterialStockById(id);
		}
		return INPUT;
	}
	

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		materialStockService.removeMaterialStock(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		materialStock = materialStockService.saveMaterialStock(materialStock);
		materialList = materialService.findAllMaterial();
		curPeriodList = commonService.getPeriodsByMatId(materialStock.getMaterial().getId());
		this.addActionMessage("Save or Update successfully!");
		return "reInput";
	}

	@Override
	protected void prepareModel() throws Exception {
	}
	
	@Override
	public MaterialStock getModel() {
		return materialStock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MaterialStock getMaterialStock() {
		return materialStock;
	}

	public void setMaterialStock(MaterialStock materialStock) {
		this.materialStock = materialStock;
	}

	public List<MaterialStock> getMaterialStockList() {
		return materialStockList;
	}

	public void setMaterialStockList(
			List<MaterialStock> materialStockList) {
		this.materialStockList = materialStockList;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	public List<WeekPriod> getCurPeriodList() {
		return curPeriodList;
	}

	public void setCurPeriodList(List<WeekPriod> curPeriodList) {
		this.curPeriodList = curPeriodList;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
}
