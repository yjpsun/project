/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.MaterialStock;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Service
@SuppressWarnings("unchecked")
public  class MaterialStockServiceImpl extends AbstractBeanService<MaterialStock, Serializable> implements MaterialStockService {

	@Override
	public MaterialStock saveMaterialStock(MaterialStock materialStock) {
		return (MaterialStock)this.getPersistenceService().merge(materialStock);
	}

	@Override
	public void removeMaterialStock(Long id) {
		this.getPersistenceService().remove(MaterialStock.class,id);
	}

	@Override
	public PaginationSupport<MaterialStock> findByPagination(MaterialStock materialStock,
			int startIndex, int pageSize) {
		String queryHql = "select m from MaterialStock m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public MaterialStock findMaterialStockById(Long id) {
		return this.findById(MaterialStock.class, id);
	}

	@Override
	public List<MaterialStock> findAllMaterialStock() {
		return this.getPersistenceService().find("select m from MaterialStock m");
	}

	@Override
	public MaterialStock getMaterialStockByMIdAndRecDate(Long mId, Date weekStart) {
		MaterialStock materialStock = null;
		List<MaterialStock> mList = this.getPersistenceService().findByNamedOfQuery("getMSByMidAndRecDate", mId,weekStart);
		if(CollectionUtils.isNotEmpty(mList)){
			materialStock = mList.get(0);
		}
		return materialStock;
	}
}
