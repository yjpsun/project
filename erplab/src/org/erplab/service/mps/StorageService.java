/**
 * 
 */
package org.erplab.service.mps;

import org.erplab.domain.Storage;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface StorageService {
	
	public Storage saveStorage(Storage storage);

	public void removeStorage(Long id);
	
	public PaginationSupport<Storage> findByPagination(Storage storage,int startIndex,
			int pageSize);
	
	public Storage findStorageById(Long id);
	
	public Storage findStorageByMaterialId(Long materialId);
	
}
