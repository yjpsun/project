/**
 * 
 */
package org.erplab.service.mps;

import java.util.Date;
import java.util.List;

import org.erplab.domain.MaterialStock;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface MaterialStockService {
	
	public MaterialStock saveMaterialStock(MaterialStock materialStock);

	public void removeMaterialStock(Long id);
	
	public PaginationSupport<MaterialStock> findByPagination(MaterialStock materialStock,int startIndex,
			int pageSize);
	
	public MaterialStock findMaterialStockById(Long id);
	
	public List<MaterialStock> findAllMaterialStock();
	
	public MaterialStock getMaterialStockByMIdAndRecDate(Long mId, Date weekStart);
}
