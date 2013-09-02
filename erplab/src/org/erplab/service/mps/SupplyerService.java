/**
 * 
 */
package org.erplab.service.mps;

import org.erplab.domain.Material;
import org.erplab.domain.Supplyer;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface SupplyerService {
	
	public Supplyer saveSupplyer(Supplyer supplyer);

	public void removeSupplyer(Long id);
	
	public PaginationSupport<Supplyer> findByPagination(Supplyer supplyer,int startIndex,
			int pageSize);
	
	public Supplyer findSupplyerById(Long id);
	
	public Supplyer findSupplyerByMaterial(Material mat);
	
	public Supplyer findBestSupplyerByMaterial(Material mat);
}
