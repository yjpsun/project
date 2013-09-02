/**
 * 
 */
package org.erplab.service.mps;

import java.util.List;

import org.erplab.domain.Bom;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface BomService {
	
	public Bom saveBom(Bom bom);
	
	public List<Bom> saveBomList(List<Bom> bomList,String removedBomIds);
	
	public List<Bom> findChildBom(String structureId,String edition);
	
	public List<Bom> findChildGR(String structureId,String edition);

	public void removeBom(String structureId,String edition);
	
	public List<String> findBomEdtionsByStructureId(String structureId);
	
	public String findNextEdtionNum(String structureId);
	
	public List<Bom> findBomStrutByMidAndEdtion(String materialCode,String edition);
}
