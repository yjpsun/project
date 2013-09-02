/**
 * 
 */
package org.erplab.service.mps;

import java.util.List;

import org.erplab.domain.Material;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface MaterialService {
	
	public Material saveMaterial(Material material);

	public void removeMaterial(Long id);
	
	public PaginationSupport<Material> findByPagination(Material material,int startIndex,
			int pageSize);
	
	public Material findMaterialById(Long id);
	
	public List<Material> findMaterialEndByOne();
	
	public List<Material>  findCompleteMaterial();
	
	public List<Material> findAllMaterial();
	
	public Material findMaterialByCode(String materialCode);
}
