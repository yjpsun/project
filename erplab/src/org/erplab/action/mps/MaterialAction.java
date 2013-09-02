/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Material;
import org.erplab.service.mps.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "material.do", type = "redirectAction")})
public class MaterialAction extends GenericActionSupport<Material>{

	private static final long serialVersionUID = -4987870986035260874L;
	private Material material;
	private List<Material> materialList;

	@Autowired
	private MaterialService materialService;
	private Long id;
	private String isCodeExist = "";
	private String code;

	@Override
	public String list() throws Exception {
		PaginationSupport<Material> ps = materialService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		materialList = (List<Material>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			material = materialService.findMaterialById(id);
		}
		return INPUT;
	}
	

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		materialService.removeMaterial(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		material = materialService.saveMaterial(material);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}
	
	@Override
	public Material getModel() {
		return material;
	}
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}


	public List<Material> getMaterialList() {
		return materialList;
	}


	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsCodeExist() {
		return isCodeExist;
	}

	public void setIsCodeExist(String isCodeExist) {
		this.isCodeExist = isCodeExist;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
