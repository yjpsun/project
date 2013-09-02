/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.domain.Bom;
import org.erplab.domain.Material;
import org.erplab.service.mps.BomService;
import org.erplab.service.mps.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "bom.do", type = "redirectAction")})
public class BomAction extends GenericActionSupport<Material>{

	private static final long serialVersionUID = -4987870986035260874L;
	private Material material;
	private List<Material> materialList;
	private List<String> edtionList;

	@Autowired
	private MaterialService materialService;
	@Autowired
	private BomService bomService;
	
	private Long materialId;
	private String edition;
	private List<Bom> bomList;
	private String materialCode;
	private String removedBomIds;

	@Override
	public String list() throws Exception {
		materialList = materialService.findCompleteMaterial();
		if(StringUtils.isEmpty(materialCode)){
			Object obj = this.getSession().getAttribute("s_materialCode");
			if(obj != null){
				materialCode = String.valueOf(obj);
				this.getSession().removeAttribute("s_materialCode");
			}
		}
		edtionList = bomService.findBomEdtionsByStructureId(materialCode);
		return SUCCESS;
	}

	public String input() throws Exception {
		materialList = materialService.findAllMaterial();
		bomList = bomService.findBomStrutByMidAndEdtion(materialCode, edition);
		if(StringUtils.isEmpty(edition)){
			edition = bomService.findNextEdtionNum(materialCode);
		}
		return INPUT;
	}
	
	public String view() throws Exception {
		materialList = materialService.findAllMaterial();
		bomList = bomService.findBomStrutByMidAndEdtion(materialCode, edition);
		return VIEW;
	}
	

	@Override
	public String remove() throws Exception {
		bomService.removeBom(materialCode,edition);
		this.getSession().setAttribute("s_materialCode", materialCode);
		this.addActionMessage("Remove successfully!");
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		materialList = materialService.findAllMaterial();
		bomList = bomService.saveBomList(bomList,removedBomIds);
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

	public List<String> getEdtionList() {
		return edtionList;
	}

	public void setEdtionList(List<String> edtionList) {
		this.edtionList = edtionList;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public List<Bom> getBomList() {
		return bomList;
	}

	public void setBomList(List<Bom> bomList) {
		this.bomList = bomList;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getRemovedBomIds() {
		return removedBomIds;
	}

	public void setRemovedBomIds(String removedBomIds) {
		this.removedBomIds = removedBomIds;
	}
}
