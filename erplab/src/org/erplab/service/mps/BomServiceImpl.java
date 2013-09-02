/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.erplab.domain.Bom;
import org.erplab.domain.Material;
import org.erplab.service.mps.report.BomReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;
import org.workin.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Service
public  class BomServiceImpl extends AbstractBeanService<Bom, Serializable> implements BomService {
	@Autowired
	private MaterialService materialService;
	@Autowired
	private BomReportService bomReportService;
	
	@Override
	public Bom saveBom(Bom bom) {
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bom> saveBomList(List<Bom> bomList,String removedBomIds) {
		List<Bom> newList = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(bomList)){
			bomList = CollectionUtils.removeNullObject(bomList);
			for(Bom bom:bomList){
				Bom newBom = (Bom)this.getPersistenceService().merge(bom);
				newList.add(newBom);
			}
		}
		if(StringUtils.isNotBlank(removedBomIds)){
			String removedBomIdArray[] = removedBomIds.split(",");
			for(String removedBomId:removedBomIdArray){
				if(StringUtils.isNotBlank(removedBomId)){
					this.getPersistenceService().remove(Bom.class, Long.valueOf(removedBomId));
				}
			}
		}
		return newList;
	}

	@Override
	public void removeBom(String structureId,String edition){
		this.getPersistenceService().execute("delete from Bom b where b.structure_id ='"+structureId+ "' and b.edition='"+edition+"'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findBomEdtionsByStructureId(String structureId) {
		return this.getPersistenceService().find("select distinct b.edition from Bom b where b.structure_id = '"+structureId+"'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bom> findBomStrutByMidAndEdtion(String materialCode, String edition) {
		return this.getPersistenceService().find("select b from Bom b where b.structure_id = '"+materialCode +"' and b.edition='"+edition+"'");
	}

	@Override
	public String findNextEdtionNum(String structureId) {
		String nextEdition = "";
		@SuppressWarnings("unchecked")
		List<String> editionList = this.getPersistenceService().find("select max(b.edition) from Bom b where b.structure_id = '"+structureId+"'");
		if(!CollectionUtils.isEmpty(editionList)){
			if(editionList.get(0) != null){
				nextEdition = Integer.valueOf(editionList.get(0))+1+"";
			}else{
				nextEdition = "1";
			}
		}else{
			nextEdition = "1";
		}
		return nextEdition;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bom> findChildBom(String structureId,String edition) {
		Map<String,Map<String,Map<String, Float>>> rs_all = Maps.newHashMap();
		
		Material mainMat = materialService.findMaterialByCode(structureId);
		
		Map<String,Map<String, Float>> mainAll = bomReportService.getMainAllItem(mainMat);
		rs_all.put(structureId, mainAll);
		rs_all = loopChild(rs_all,mainMat,edition);
		//Map<String, Float> main_rs_porc = mpsReportService.getMPS_PORC(mainMat.getId());
		//Map<String, Float> main_rs_por = mpsReportService.getMPS_POR(main_rs_porc,mainMat.getMatLt());
		
		List<Bom> childBomList =  this.getPersistenceService().find("select b from Bom b where b.structure_id = '"+structureId +"' and b.edition='"+edition+"'");
		List<Bom> childBomList_new = Lists.newArrayList();
		Bom mainBom = new Bom();
		mainBom.setMaterial(mainMat);
		mainBom.setMps_gr(mainAll.get("rs_gr"));
		mainBom.setMps_sr(mainAll.get("rs_sr"));
		mainBom.setMps_poh(mainAll.get("rs_poh"));
		mainBom.setMps_pab(mainAll.get("rs_pab"));
		mainBom.setMps_nr(mainAll.get("rs_nr"));
		mainBom.setMps_porc(mainAll.get("rs_porc"));
		mainBom.setMps_por(mainAll.get("rs_por"));
		mainBom.setMps_al(mainAll.get("rs_al"));
		mainBom.setMps_oh(mainAll.get("rs_oh"));
		mainBom.setMps_sumption(mainAll.get("rs_sumption"));
		String _matps = mainMat.getMatPs();
		mainBom.setMatPs(_matps);
		if(_matps.equals("Y")){
			mainBom.setMps_mps(mainAll.get("rs_mps"));
			Map<String,Float> rs_atp_main = mainAll.get("rs_atp");
			mainBom.setMps_atp(rs_atp_main);
			mainBom.setMps_atp_adjust(adjustATP(rs_atp_main));
		}
		
		
		childBomList_new.add(mainBom);
		
		String child_material_code = "";
		for(int i=0;i<childBomList.size();i++){
			Bom bom = childBomList.get(i);
			if(bom != null){
				child_material_code = bom.getChild_material_id();
				
				String isExist = "";
				for(int j = 0;j<childBomList_new.size();j++){
					if(childBomList_new.get(j).getMaterial().getCode().equals(child_material_code)){
						isExist = "Y";
						break;
					};
				}
				
				Material mat = materialService.findMaterialByCode(child_material_code);
				bom.setMaterial(mat);
				Map<String, Map<String, Float>> childAllItem = rs_all.get(child_material_code);
				bom.setMps_gr(childAllItem.get("rs_gr"));
				bom.setMps_sr(childAllItem.get("rs_sr"));
				bom.setMps_poh(childAllItem.get("rs_poh"));
				bom.setMps_pab(childAllItem.get("rs_pab"));
				bom.setMps_nr(childAllItem.get("rs_nr"));
				bom.setMps_porc(childAllItem.get("rs_porc"));
				bom.setMps_por(childAllItem.get("rs_por"));
				bom.setMps_al(childAllItem.get("rs_al"));
				bom.setMps_oh(childAllItem.get("rs_oh"));
				bom.setMps_sumption(childAllItem.get("rs_sumption"));
				String _childmatps = bom.getMaterial().getMatPs();
				bom.setMatPs(_childmatps);
				if(_childmatps.equals("Y")){
					bom.setMps_mps(childAllItem.get("rs_mps"));
					Map<String,Float> rs_atp = childAllItem.get("rs_atp");
					bom.setMps_atp(rs_atp);
					bom.setMps_atp_adjust(adjustATP(rs_atp));
				}
				if(StringUtils.isEmpty(isExist)){
					childBomList_new.add(bom);
				}
			}
		}
		return childBomList_new;
	}
	
	private Map<String,Float> adjustATP(Map<String,Float> rs_atp){
		Map<String, Float> rs_atp_clone = Maps.newHashMap();
		if(rs_atp != null){
			for(String gr_key:rs_atp.keySet()){
				rs_atp_clone.put(gr_key, rs_atp.get(gr_key));
			}
			
			for (int i = 7; i > 0; i--) {
				Float atp = rs_atp_clone.get("period_" + i);
				if(atp < 0){
					rs_atp_clone.put("period_" + i,0f);
					for(int j=i-1;j>=0;j--){
						Float atpIn = rs_atp_clone.get("period_" + j);
						if(atpIn != 0f){
							rs_atp_clone.put("period_" + j,atpIn+atp);
							break;
						}
					}
				}
			}
		}
		return rs_atp_clone;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Map<String,Map<String, Float>>> loopChild(Map<String,Map<String,Map<String, Float>>> rs_all,Material parentMat,String edition){
		List<Bom> childList = this.getPersistenceService().find("select b from Bom b where b.father_material_id = '"+parentMat.getCode() +"' and b.edition='"+edition+"'");
		if(CollectionUtils.isEmpty(childList) || childList.get(0) == null){
			return rs_all;
		}else{
			for(Bom bom:childList){
				if(bom != null){
					Map<String,Map<String, Float>> parent_rs_all = rs_all.get(parentMat.getCode());
					Material curMat = materialService.findMaterialByCode(bom.getChild_material_id());
					if(parent_rs_all != null){
						String child_material_id = bom.getChild_material_id();
						Map<String,Map<String, Float>> cur_rs_all = bomReportService.getChildAllItem(parent_rs_all, bom, curMat,null,null);
						
						Map<String,Map<String, Float>> rs_gr_old = rs_all.get(child_material_id);
						if(rs_gr_old != null){
							Map<String, Float> rs_gr_combine = combineSame(rs_gr_old,cur_rs_all);
							cur_rs_all = bomReportService.getChildAllItem(parent_rs_all, bom, curMat,rs_gr_combine,null);
						}
						rs_all.put(child_material_id, cur_rs_all);
						loopChild(rs_all,curMat,edition);
					}
				}
			}
		}
		return rs_all;
	}
	
	public Map<String, Float> combineSame(Map<String,Map<String, Float>> oldRs,Map<String,Map<String, Float>> newRs){
		Map<String, Float> rs_gr_old = oldRs.get("rs_gr");
		Map<String, Float> rs_gr_new = newRs.get("rs_gr");
		Map<String, Float> rs_sumption_new = newRs.get("rs_sumption");
		
		Map<String, Float> rs_gr_combine = Maps.newHashMap();
		for (int i = 0; i < 9; i++) {
			Float gr_old = rs_gr_old.get("period_" + i);
			Float gr_new = rs_gr_new.get("period_" + i);
			Float sumption_new = rs_sumption_new.get("period_" + i);
			Float gr_combine = gr_old + gr_new-sumption_new;
			rs_gr_combine.put("period_" + i, gr_combine);
		}
		return rs_gr_combine;
	}

	@Override
	public List<Bom> findChildGR(String structureId, String edition) {
		Map<String,Map<String,Map<String, Float>>> rs_all = Maps.newHashMap();
		
		Material mainMat = materialService.findMaterialByCode(structureId);
		Map<String,Map<String, Float>> mainAll = bomReportService.getMainAllItem(mainMat);
		rs_all.put(structureId, mainAll);
		rs_all = loopChildWithOutCombine(rs_all,mainMat,edition,null);
		
		@SuppressWarnings("unchecked")
		List<Bom> childBomList =  this.getPersistenceService().find("select b from Bom b where b.structure_id = '"+structureId +"' and b.edition='"+edition+"'");
		List<Bom> childBomList_new = Lists.newArrayList();
		Bom mainBom = new Bom();
		mainBom.setMaterial(mainMat);
		mainBom.setMps_al(mainAll.get("rs_al"));
		mainBom.setMps_oh(mainAll.get("rs_oh"));
		mainBom.setMps_sumption(mainAll.get("rs_sumption"));
		
		List<Map<String,Float>> lmt = Lists.newArrayList();
		lmt.add(mainAll.get("rs_gr"));
		mainBom.setGrList(lmt);
		childBomList_new.add(mainBom);
		
		
		String child_material_code = "";
		for(int i=0;i<childBomList.size();i++){
			Bom bom = childBomList.get(i);
			if(bom != null){
				child_material_code = bom.getChild_material_id();
				List<Map<String,Float>> lm = Lists.newArrayList();
				Material mat = materialService.findMaterialByCode(child_material_code);
				bom.setMaterial(mat);
				
				Map<String, Map<String, Float>> childAllItem = rs_all.get("bomId_"+bom.getId());
				
				String isExist = "";
				for(int j = 0;j<childBomList_new.size();j++){
					if(childBomList_new.get(j).getMaterial().getCode().equals(child_material_code)){
						childBomList_new.get(j).getGrList().add(childAllItem.get("rs_gr"));
						isExist = "Y";
						break;
					};
				}
				
				
				if(StringUtils.isEmpty(isExist)){
					lm.add(childAllItem.get("rs_gr"));
					bom.setGrList(lm);
					childBomList_new.add(bom);
				}
				
				bom.setMps_al(childAllItem.get("rs_al"));
				bom.setMps_oh(childAllItem.get("rs_oh"));
				bom.setMps_sumption(childAllItem.get("rs_sumption"));
			}
		}
		return childBomList_new;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Map<String,Map<String, Float>>> loopChildWithOutCombine(Map<String,Map<String,Map<String, Float>>> rs_all,Material parentMat,String edition,Bom p_bom){
		List<Bom> childList = this.getPersistenceService().find("select b from Bom b where b.father_material_id = '"+parentMat.getCode() +"' and b.edition='"+edition+"'");
		if(CollectionUtils.isEmpty(childList) || childList.get(0) == null){
			return rs_all;
		}else{
			for(Bom bom:childList){
				if(bom != null){
					String parentKey = "";
					if(p_bom != null){
						parentKey = "bomId_"+p_bom.getId();
					}else{
						parentKey = parentMat.getCode();
					}
					Map<String,Map<String, Float>> parent_rs_all = rs_all.get(parentKey);
					Material curMat = materialService.findMaterialByCode(bom.getChild_material_id());
					if(parent_rs_all != null){
						Map<String,Map<String, Float>> cur_rs_all = bomReportService.getChildAllItem(parent_rs_all, bom, curMat,null,"report-gr");
						rs_all.put("bomId_"+bom.getId(), cur_rs_all);
						loopChildWithOutCombine(rs_all,curMat,edition,bom);
					}
				}
			}
		}
		return rs_all;
	}
}
