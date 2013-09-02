/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Material;
import org.erplab.domain.Supplyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;
import org.workin.core.persistence.support.PaginationSupport;

import com.google.common.collect.Lists;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Service
@SuppressWarnings("unchecked")
public  class SupplyerServiceImpl extends AbstractBeanService<Supplyer, Serializable> implements SupplyerService {
	@Autowired
	MaterialService materialService;

	@Override
	public Supplyer saveSupplyer(Supplyer supplyer) {
		if(supplyer.getId() == null){
			Long matId = supplyer.getMaterial().getId();
			Material mat = materialService.findMaterialById(matId);
			String query = "select m from Supplyer m where m.material.code='"+mat.getCode()+"'";
			int count = this.getPersistenceService().countByQueryString(query);
			supplyer.setCode(mat.getCode()+"-"+(count+1));
		}
		return (Supplyer)this.getPersistenceService().merge(supplyer);
	}

	@Override
	public void removeSupplyer(Long id) {
		this.getPersistenceService().remove(Supplyer.class,id);
	}

	@Override
	public PaginationSupport<Supplyer> findByPagination(Supplyer supplyer,
			int startIndex, int pageSize) {
		String queryHql = "select m from Supplyer m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public Supplyer findSupplyerById(Long id) {
		return this.findById(Supplyer.class, id);
	}

	@Override
	public Supplyer findSupplyerByMaterial(Material mat) {
		if(mat == null) return null;
		String queryHql = "select m from Supplyer m where m.material.code='"+mat.getCode()+"'";
		List<Supplyer> supList = this.getPersistenceService().find(queryHql);
		if(CollectionUtils.isNotEmpty(supList)){
			return supList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Supplyer findBestSupplyerByMaterial(Material mat) {
		if(mat == null) return null;
		String queryHql = "select m from Supplyer m where m.material.code='"+mat.getCode()+"'";
		List<Supplyer> supList = this.getPersistenceService().find(queryHql);
		List<Supplyer> supList_0 = Lists.newArrayList();
		List<Supplyer> supList_1 = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(supList)){
			for(Supplyer sup:supList){
				if(sup != null && sup.getSt() == 0f){
					supList_0.add(sup);
				}else if(sup != null && sup.getSt() != 0f){
					supList_1.add(sup);
				}
			}
		}
		Supplyer rs = null;
		for(Supplyer sup:supList_0){
			if(sup != null){
				if(rs == null){
					rs =sup;
				}else{
					if(rs.getUnitP() > sup.getUnitP()){
						rs = sup;
					}
				}
			}
		}
		if(rs == null){
			for(Supplyer sup:supList_1){
				if(sup != null){
					if(rs == null){
						rs =sup;
					}else{
						if(rs.getUnitP() > sup.getUnitP()){
							rs = sup;
						}
					}
				}
			}
		}
		return rs;
	}
}
