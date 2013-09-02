/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Apply;
import org.erplab.domain.Material;
import org.erplab.service.mps.ApplyService;
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
	@Result(name = "reload", location = "apply.do", type = "redirectAction")})
public class ApplyAction extends GenericActionSupport<Apply>{

	private static final long serialVersionUID = 2416828758254925848L;
	
	private Apply apply;
	private List<Apply> buyList;
	private List<Material> materialList;
	private Long id;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<Apply> ps = applyService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		buyList = (List<Apply>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
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
}
