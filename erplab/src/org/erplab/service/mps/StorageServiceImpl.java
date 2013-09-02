/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Storage;
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
public class StorageServiceImpl extends AbstractBeanService<Storage, Serializable> implements StorageService {

	@Override
	public Storage saveStorage(Storage storage) {
		return (Storage)this.getPersistenceService().merge(storage);
	}

	@Override
	public void removeStorage(Long id) {
		this.getPersistenceService().remove(Storage.class,id);
	}

	@Override
	public PaginationSupport<Storage> findByPagination(Storage storage,
			int startIndex, int pageSize) {
		String queryHql = "select t from Storage t";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public Storage findStorageById(Long id) {
		return this.findById(Storage.class, id);
	}

	@Override
	public Storage findStorageByMaterialId(Long materialId) {
		List<Storage> tgList = this.getPersistenceService().find("select t from Storage t where t.material.id = "+materialId);
		if(CollectionUtils.isNotEmpty(tgList)){
			return tgList.get(0);
		}
		return null;
	}

}
