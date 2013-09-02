/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.MaterialDelivering;
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
public  class MaterialDeliveringServiceImpl extends AbstractBeanService<MaterialDelivering, Serializable> implements MaterialDeliveringService {

	@Override
	public MaterialDelivering saveMaterialDelivering(MaterialDelivering materialDelivering) {
		return (MaterialDelivering)this.getPersistenceService().merge(materialDelivering);
	}

	@Override
	public void removeMaterialDelivering(Long id) {
		this.getPersistenceService().remove(MaterialDelivering.class,id);
	}

	@Override
	public PaginationSupport<MaterialDelivering> findByPagination(MaterialDelivering materialDelivering,
			int startIndex, int pageSize) {
		String queryHql = "select m from MaterialDelivering m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public MaterialDelivering findMaterialDeliveringById(Long id) {
		return this.findById(MaterialDelivering.class, id);
	}

	@Override
	public List<MaterialDelivering> findAllMaterialDelivering() {
		return this.getPersistenceService().find("select m from MaterialDelivering m");
	}

	@Override
	public MaterialDelivering getMaterialDeliveringByMIdAndRecDate(Long mId,
			Date recDate) {
		MaterialDelivering materialDelivering = null;
		List<MaterialDelivering> mList = this.getPersistenceService().findByNamedOfQuery("getMDByMidAndRecDate", mId,recDate);
		if(CollectionUtils.isNotEmpty(mList)){
			materialDelivering = mList.get(0);
		}
		return materialDelivering;
	}
}
