/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Material;
import org.erplab.domain.SaleApply;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.SaleApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "sale-apply.do", type = "redirectAction")})
public class SaleApplyAction extends GenericActionSupport<SaleApply>{

	private static final long serialVersionUID = 2416828758254925848L;
	
	private SaleApply saleApply;
	private List<SaleApply> saleApplyList;
	private List<Material> materialList;
	private Long id;
	
	@Autowired
	private SaleApplyService saleApplyService;
	
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<SaleApply> ps = saleApplyService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		saleApplyList = (List<SaleApply>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			saleApply = saleApplyService.findSaleApplyById(id);
		}
		return INPUT;
	}
	
	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		saleApplyService.removeSaleApply(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		if(saleApplyService.checkSaleApply(saleApply)){
			saleApply = saleApplyService.saveSaleApply(saleApply);
			this.addActionMessage("接单成功!请到销售管理页面查看!");
		}else{
			this.addActionMessage("拒绝接单");
		}
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public SaleApply getModel() {
		return saleApply;
	}
	public SaleApply getSaleApply() {
		return saleApply;
	}

	public void setSaleApply(SaleApply saleApply) {
		this.saleApply = saleApply;
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

	public List<SaleApply> getSaleApplyList() {
		return saleApplyList;
	}

	public void setSaleApplyList(List<SaleApply> saleApplyList) {
		this.saleApplyList = saleApplyList;
	}
}
