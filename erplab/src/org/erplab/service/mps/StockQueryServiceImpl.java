/**
 * 
 */
package org.erplab.service.mps;

import java.util.Map;

import org.erplab.domain.Material;
import org.erplab.domain.StockQuery;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lawrence.wang
 *
 * 2011-5-24
 */
public class StockQueryServiceImpl implements StockQueryService{
	@Autowired
	InventoryService inventoryService;
	@Autowired
	MaterialService materialService;

	@Override
	public StockQuery queryStock(Long matId) {
		Material mat = materialService.findMaterialById(matId);
		Map<String,Float> bookRs =inventoryService.loadBookValue(matId);
		Float oh = bookRs.get("bookOh");
		Float sr = bookRs.get("sr");
		StockQuery sq = new StockQuery();
		sq.setMaterial(mat);
		sq.setOh(oh);
		sq.setSr(sr);
		return sq;
	}
}
