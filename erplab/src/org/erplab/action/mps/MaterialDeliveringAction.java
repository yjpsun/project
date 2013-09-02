/**
 * 
 */
package org.erplab.action.mps;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.domain.Material;
import org.erplab.domain.MaterialDelivering;
import org.erplab.domain.WeekPriod;
import org.erplab.service.mps.MaterialDeliveringService;
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
	@Result(name = "reload", location = "material-delivering.do", type = "redirectAction"),
	@Result(name = "reInput", location = "/WEB-INF/content/mps/material-delivering.jsp")})
public class MaterialDeliveringAction extends GenericActionSupport<MaterialDelivering>{

	private static final long serialVersionUID = -4987870986035260874L;
	private MaterialDelivering materialDelivering;
	private List<MaterialDelivering> materialDeliveringList;
	private List<Material> materialList;
	private List<WeekPriod> curPeriodList;
	private Long materialId;
	private Date recDate;

	@Autowired
	private MaterialDeliveringService materialDeliveringService;
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
			materialDelivering = materialDeliveringService.findMaterialDeliveringById(id);
		}
		return INPUT;
	}
	

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		materialDeliveringService.removeMaterialDelivering(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		materialDelivering = materialDeliveringService.saveMaterialDelivering(materialDelivering);
		materialList = materialService.findAllMaterial();
		curPeriodList = commonService.getPeriodsByMatId(materialDelivering.getMaterial().getId());
		this.addActionMessage("Save or Update successfully!");
		return "reInput";
	}

	@Override
	protected void prepareModel() throws Exception {
	}
	
	@Override
	public MaterialDelivering getModel() {
		return materialDelivering;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MaterialDelivering getMaterialDelivering() {
		return materialDelivering;
	}

	public void setMaterialDelivering(MaterialDelivering materialDelivering) {
		this.materialDelivering = materialDelivering;
	}

	public List<MaterialDelivering> getMaterialDeliveringList() {
		return materialDeliveringList;
	}

	public void setMaterialDeliveringList(
			List<MaterialDelivering> materialDeliveringList) {
		this.materialDeliveringList = materialDeliveringList;
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
