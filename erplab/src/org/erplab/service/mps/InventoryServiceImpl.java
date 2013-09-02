/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Inventory;
import org.erplab.domain.Material;
import org.erplab.domain.OutStock;
import org.erplab.domain.PurchaseOrder;
import org.erplab.domain.Stock;
import org.erplab.domain.StockQuery;
import org.erplab.utils.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;
import org.workin.core.persistence.support.PaginationSupport;

import com.google.common.collect.Maps;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Service
@SuppressWarnings("unchecked")
public class InventoryServiceImpl extends AbstractBeanService<Inventory, Serializable> implements InventoryService {

	@Autowired
	MaterialService materialService;
	@Autowired
	SupplyerService supplyerService;
	@Autowired
	OutStockService outStockService;
	@Autowired
	StockService stockService;
	@Autowired
	PurchaseOrderService purchaseOrderService;
	@Autowired
	MaterialDeliveringService materialDeliveringService;
	
	@Override
	public Inventory saveInventory(Inventory apply) {
		return (Inventory)this.getPersistenceService().merge(apply);
	}

	@Override
	public void removeInventory(Long id) {
		this.getPersistenceService().remove(Inventory.class,id);
	}

	@Override
	public PaginationSupport<Inventory> findByPagination(Inventory apply,
			int startIndex, int pageSize) {
		String queryHql = "select m from Inventory m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	
	}

	@Override
	public Inventory findInventoryById(Long id) {
		return this.findById(Inventory.class, id);
	}

	@Override
	public Map<String, Float> loadBookValue(Long matId) {
		List<Stock> stockList =stockService.findStockByMatId(matId);
		List<OutStock> outStockList = outStockService.findOutStockByMatId(matId);
		
		List<PurchaseOrder> purchaseOrderList = purchaseOrderService.findPurchaseListByMat(matId);
		Float sumStockQ = 0f;
		Float sumStockUnitP = 0f;
		Float sumPurchaseOrder = 0f;
		Float sumStockQ_inPlan = 0f;
		Float countStock = 0f;
		
		Float sumOutStockQ = 0f;
		
		if(CollectionUtils.isNotEmpty(stockList)){
			for(Stock stock:stockList){
				if(stock != null){
					countStock = countStock + 1;
					String planType = stock.getStocktype();
					if("inPlan".equals(planType)){
						Long purchaseOrderId = stock.getPurchaseOrderId();
						PurchaseOrder order = purchaseOrderService.findPurchaseOrderById(purchaseOrderId);
						sumStockUnitP = sumStockUnitP + order.getUnitP();
						sumStockQ_inPlan = stock.getRincomingQ()+ sumStockQ_inPlan;
					}
					if("outPlan".equals(planType)){
						sumStockUnitP = sumStockUnitP + stock.getUnitP();
					}
					sumStockQ = stock.getRincomingQ() + sumStockQ;
				}
			}
		}
		if(CollectionUtils.isNotEmpty(outStockList)){
			for(OutStock outStock:outStockList){
				if(outStock != null){
					sumOutStockQ = outStock.getRoutQ() + sumOutStockQ;
				}
			}
		}
		
		if(CollectionUtils.isNotEmpty(purchaseOrderList)){
			for(PurchaseOrder purchaseOrder:purchaseOrderList){
				if(purchaseOrder != null){
					sumPurchaseOrder = purchaseOrder.getPurQ() + sumPurchaseOrder;
				}
			}
		}
		
		
		Float bookOh = sumStockQ - sumOutStockQ;
		Float bookValue = 0f;
		Float unitP = 0f;
		if(countStock > 0f){
			unitP = sumStockUnitP/countStock;
			bookValue = bookOh*unitP;
		}
		Float sr = sumPurchaseOrder - sumStockQ_inPlan;
		
		Map<String,Float> rs = Maps.newHashMap();
		rs.put("bookOh", NumberTool.round1Point(bookOh));
		rs.put("bookValue", NumberTool.round1Point(bookValue));
		rs.put("bookAvgUnitP", NumberTool.round1Point(unitP));
		rs.put("sr", NumberTool.round1Point(sr));
		return rs;
	}
	
	@Override
	public StockQuery queryStock(Long matId) {
		Material mat = materialService.findMaterialById(matId);
		Map<String,Float> bookRs =this.loadBookValue(matId);
		Float oh = bookRs.get("bookOh");
		Float sr = bookRs.get("sr");
		StockQuery sq = new StockQuery();
		sq.setMaterial(mat);
		sq.setOh(oh);
		sq.setSr(sr);
		return sq;
	}
}
