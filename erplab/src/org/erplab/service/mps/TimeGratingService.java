/**
 * 
 */
package org.erplab.service.mps;

import org.erplab.domain.TimeGrating;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface TimeGratingService {
	
	public TimeGrating saveTimeGrating(TimeGrating timeGrating);

	public void removeTimeGrating(Long id);
	
	public PaginationSupport<TimeGrating> findByPagination(TimeGrating timeGrating,int startIndex,
			int pageSize);
	
	public TimeGrating findTimeGratingById(Long id);
	
	public TimeGrating findTimeGratingByMaterialId(Long materialId);
	
}
