/**
 * 
 */
package org.erplab.service.mps;

import java.util.Date;
import java.util.List;

import org.erplab.domain.MaterialDelivering;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface MaterialDeliveringService {
	
	public MaterialDelivering saveMaterialDelivering(MaterialDelivering materialDelivering);

	public void removeMaterialDelivering(Long id);
	
	public PaginationSupport<MaterialDelivering> findByPagination(MaterialDelivering materialDelivering,int startIndex,
			int pageSize);
	
	public MaterialDelivering findMaterialDeliveringById(Long id);
	
	public List<MaterialDelivering> findAllMaterialDelivering();
	
	public MaterialDelivering getMaterialDeliveringByMIdAndRecDate(Long mId, Date recDate);
}
