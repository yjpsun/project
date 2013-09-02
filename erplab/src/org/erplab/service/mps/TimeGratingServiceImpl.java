/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.TimeGrating;
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
public class TimeGratingServiceImpl extends AbstractBeanService<TimeGrating, Serializable> implements TimeGratingService {

	@Override
	public TimeGrating saveTimeGrating(TimeGrating timeGrating) {
		return (TimeGrating)this.getPersistenceService().merge(timeGrating);
	}

	@Override
	public void removeTimeGrating(Long id) {
		this.getPersistenceService().remove(TimeGrating.class,id);
	}

	@Override
	public PaginationSupport<TimeGrating> findByPagination(TimeGrating timeGrating,
			int startIndex, int pageSize) {
		String queryHql = "select t from TimeGrating t";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public TimeGrating findTimeGratingById(Long id) {
		return this.findById(TimeGrating.class, id);
	}

	@Override
	public TimeGrating findTimeGratingByMaterialId(Long materialId) {
		List<TimeGrating> tgList = this.getPersistenceService().find("select t from TimeGrating t where t.material.id = "+materialId);
		if(CollectionUtils.isNotEmpty(tgList)){
			return tgList.get(0);
		}
		return null;
	}

}
