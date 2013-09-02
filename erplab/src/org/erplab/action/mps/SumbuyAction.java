/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.domain.Apply;
import org.erplab.domain.Material;
import org.erplab.service.mps.ApplyService;
import org.erplab.service.mps.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "apply.do", type = "redirectAction")})
public class SumbuyAction extends GenericActionSupport<Apply>{

	private static final long serialVersionUID = 2416828758254925848L;
	
	private Apply apply;
	private List<Apply> buyList;
	private List<Material> materialList;
	private Long id;
	private Long materialId;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		materialList = materialService.findAllMaterial();
		if(materialId != null){
			Material mat = materialService.findMaterialById(materialId);
			buyList = applyService.findApplySum(mat);
		}
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			apply = applyService.findApplyById(id);
		}
		return INPUT;
	}
	
	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		applyService.removeApply(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		apply = applyService.saveApply(apply);
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}
	
	public String generateOrder() throws Exception {
		materialList = materialService.findAllMaterial();
		if(materialId != null){
			Material mat = materialService.findMaterialById(materialId);
			buyList = applyService.findApplySum(mat);
			applyService.saveGenerateOrder(buyList, mat);
			this.addActionMessage("采购单生成成功，请到采购单管理页面查看!");
		}
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public Apply getModel() {
		return apply;
	}
	public Apply getApply() {
		return apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}


	public List<Apply> getApplyList() {
		return buyList;
	}


	public void setApplyList(List<Apply> buyList) {
		this.buyList = buyList;
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

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public List<Apply> getBuyList() {
		return buyList;
	}

	public void setBuyList(List<Apply> buyList) {
		this.buyList = buyList;
	}
}
