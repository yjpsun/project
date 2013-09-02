/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Inventory;
import org.erplab.domain.Material;
import org.erplab.service.mps.InventoryService;
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
	@Result(name = "reload", location = "inventory.do", type = "redirectAction")})
public class InventoryAction extends GenericActionSupport<Inventory>{

	private static final long serialVersionUID = 2416828758254925848L;
	
	private Inventory inventory;
	private List<Inventory> inventoryList;
	private List<Material> materialList;
	private Long id;
	private String flag;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private MaterialService materialService;

	@Override
	public String list() throws Exception {
		PaginationSupport<Inventory> ps = inventoryService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		inventoryList = (List<Inventory>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			inventory = inventoryService.findInventoryById(id);
		}
		return INPUT;
	}
	
	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		inventoryService.removeInventory(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		Float ohlp = inventory.getOhlp();
		Float bookOH = Math.abs(inventory.getBookOH());
		Float noftolerance = inventory.getNoftolerance();
		Float valueLP = Math.abs(inventory.getValueLP());
		Float  aoftolerance = inventory.getAoftolerance();
		
		if("error1".equals(flag)){
			inventory.setBookOH(inventory.getInventoryOH());
			inventory.setBookvalue(inventory.getInventoryvalue());
			inventory = inventoryService.saveInventory(inventory);
			this.addActionMessage("Save or Update successfully!");
		}else{
			if(bookOH != 0f){
				if(ohlp/bookOH<=noftolerance && valueLP<=aoftolerance){
					inventory.setBookOH(inventory.getInventoryOH());
					inventory.setBookvalue(inventory.getInventoryvalue());
					inventory = inventoryService.saveInventory(inventory);
					this.addActionMessage("Save or Update successfully!");
				}else{
					flag = "error1";
					this.addActionMessage("请重新进行盘点!");
				}
			}
		}
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findAllMaterial();
	}
	
	@Override
	public Inventory getModel() {
		return inventory;
	}
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
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

	public List<Inventory> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Inventory> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
