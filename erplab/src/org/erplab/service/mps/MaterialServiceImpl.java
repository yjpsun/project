/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Material;
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
public  class MaterialServiceImpl extends AbstractBeanService<Material, Serializable> implements MaterialService {

	@Override
	public Material saveMaterial(Material material) {
		return (Material)this.getPersistenceService().merge(material);
	}

	@Override
	public void removeMaterial(Long id) {
		this.getPersistenceService().remove(Material.class,id);
	}

	@Override
	public PaginationSupport<Material> findByPagination(Material material,
			int startIndex, int pageSize) {
		String queryHql = "select m from Material m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public Material findMaterialById(Long id) {
		return this.findById(Material.class, id);
	}

	@Override
	public List<Material> findMaterialEndByOne() {
		return this.getPersistenceService().find("select m from Material m where m.code like '%1' and m.id not in (select t.material.id from TimeGrating t)");
	}

	@Override
	public List<Material> findCompleteMaterial() {
		return this.getPersistenceService().find("select m from Material m where m.code like '%1'");
	}
	
	@Override
	public List<Material> findAllMaterial() {
		return this.getPersistenceService().find("select m from Material m");
	}

	@Override
	public Material findMaterialByCode(String materialCode) {
		Material material = null;
		List<Material> mList = this.getPersistenceService().find("select m from Material m where m.code = '" + materialCode +"'");
		if(CollectionUtils.isNotEmpty(mList)){
			material = mList.get(0);
		}
		return material;
	}
}
