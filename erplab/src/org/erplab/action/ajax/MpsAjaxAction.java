/**
 * 
 */
package org.erplab.action.ajax;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.erplab.domain.Material;
import org.erplab.domain.MaterialDelivering;
import org.erplab.domain.MaterialStock;
import org.erplab.domain.PurchaseOrder;
import org.erplab.domain.WeekPriod;
import org.erplab.service.mps.BomService;
import org.erplab.service.mps.InventoryService;
import org.erplab.service.mps.MaterialDeliveringService;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.MaterialStockService;
import org.erplab.service.mps.OrderService;
import org.erplab.service.mps.PurchaseOrderService;
import org.erplab.service.mps.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.web.struts2.GenericAjaxActionSupport;

/**
 * @author Administrator
 * 
 */
@SuppressWarnings("rawtypes")
public class MpsAjaxAction extends GenericAjaxActionSupport {

	private static final long serialVersionUID = -3052289044950285792L;
	private Long materialId;
	private Integer period;
	private String materialCode;
	private String materialCodeOrg;
	
	private Date recDate;
	private MaterialDelivering materialDelivering;
	
	private MaterialStock materialStock;
	private Date weekStart;
	
	private List<WeekPriod> periodList;
	private List<String> edtionList;
	private List<PurchaseOrder> poList;
	private Map<String,Float> bookMap;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private MaterialDeliveringService materialDeliveringService;
	@Autowired
	private MaterialStockService materialStockService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private BomService bomService;
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private InventoryService inventoryService;
		
	
	public Object buildJsonResult() {
		return orderService.sumOrderBy(materialId, period);
	}
	
	@SuppressWarnings("unchecked")
	public String checkMaterialCode(){
		String result = "";
		Material material = null;
		if(materialId == null || !materialCode.equals(materialCodeOrg)){
			material = materialService.findMaterialByCode(materialCode);
		}
		if(material == null){
			result = "notExist";
		}
		this.setJsonResult(result);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String findMDByMIdAndRecDate(){
		materialDelivering = materialDeliveringService.getMaterialDeliveringByMIdAndRecDate(materialId, recDate);
		this.setJsonResult(materialDelivering);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String findMSByMIdAndRecDate(){
		materialStock = materialStockService.getMaterialStockByMIdAndRecDate(materialId, weekStart);
		this.setJsonResult(materialStock);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String findPeriodByMatId(){
		periodList = commonService.getPeriodsByMatId(materialId);
		this.setJsonResult(periodList);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String findPurchaseOrder(){
		poList = purchaseOrderService.findPurchaseListByMat(materialId);
		this.setJsonResult(poList);
		return SUCCESS;
	}
	
	
	@SuppressWarnings("unchecked")
	public String loadEdition(){
		edtionList = bomService.findBomEdtionsByStructureId(materialCode);
		this.setJsonResult(edtionList);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String loadBookValue(){
		bookMap = inventoryService.loadBookValue(materialId);
		this.setJsonResult(bookMap);
		return SUCCESS;
	}
	
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialCodeOrg() {
		return materialCodeOrg;
	}

	public void setMaterialCodeOrg(String materialCodeOrg) {
		this.materialCodeOrg = materialCodeOrg;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public MaterialDelivering getMaterialDelivering() {
		return materialDelivering;
	}

	public void setMaterialDelivering(MaterialDelivering materialDelivering) {
		this.materialDelivering = materialDelivering;
	}

	public MaterialStock getMaterialStock() {
		return materialStock;
	}

	public void setMaterialStock(MaterialStock materialStock) {
		this.materialStock = materialStock;
	}

	public Date getWeekStart() {
		return weekStart;
	}

	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}

	public List<WeekPriod> getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List<WeekPriod> periodList) {
		this.periodList = periodList;
	}

	public List<String> getEdtionList() {
		return edtionList;
	}

	public void setEdtionList(List<String> edtionList) {
		this.edtionList = edtionList;
	}

	public List<PurchaseOrder> getPoList() {
		return poList;
	}

	public void setPoList(List<PurchaseOrder> poList) {
		this.poList = poList;
	}

	public Map<String, Float> getBookMap() {
		return bookMap;
	}

	public void setBookMap(Map<String, Float> bookMap) {
		this.bookMap = bookMap;
	}
}
