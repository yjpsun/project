/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.domain.Bom;
import org.erplab.domain.Material;
import org.erplab.service.mps.BomService;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.report.BomReportService;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-7
 */
@Results({
	@Result(name = "bomReportRs", location = "/WEB-INF/content/mps/bom-report-rs.jsp"),
	@Result(name = "bomGRReportRs", location = "/WEB-INF/content/mps/bom-grreport-rs.jsp")
})
public class BomReportAction extends ActionSupport{

	private static final long serialVersionUID = 3361676425681949368L;
	private List<Material> materialList;
	private Long materialId;
	private String structureId;
	private String edition;
	
	private Map<String,Object> mps_period = Maps.newHashMap();
	private Map<String,Float> mps_gr = Maps.newHashMap();
	private Map<String,Float> mps_sr = Maps.newHashMap();
	private Map<String,Float> mps_poh = Maps.newHashMap();
	private Map<String,Float> mps_pab = Maps.newHashMap();
	private Map<String,Float> mps_nr = Maps.newHashMap();
	private Map<String,Float> mps_porc = Maps.newHashMap();
	private Map<String,Float> mps_por = Maps.newHashMap();
	
	private Material material;
	
	private List<Bom> childBomList;
	
	@Autowired
	private MaterialService materialService;
	@Autowired
	private BomService bomService;
	@Autowired
	private BomReportService bomReportService;
	
	public String execute() throws Exception{
		materialList  = materialService.findCompleteMaterial();
		return SUCCESS;
	}

	public String buildReport() throws Exception{
		material = materialService.findMaterialByCode(structureId);
		materialId = material.getId();
		childBomList = bomService.findChildBom(structureId,edition);
		mps_period = bomReportService.getMPS_Period(materialId);
		return "bomReportRs";
	}
	
	public String buildGRReport() throws Exception{
		material = materialService.findMaterialByCode(structureId);
		materialId = material.getId();
		childBomList = bomService.findChildGR(structureId,edition);
		mps_period = bomReportService.getMPS_Period(materialId);
		return "bomGRReportRs";
	}
	
	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public Map<String, Float> getMps_gr() {
		return mps_gr;
	}

	public void setMps_gr(Map<String, Float> mps_gr) {
		this.mps_gr = mps_gr;
	}

	public Map<String, Object> getMps_period() {
		return mps_period;
	}

	public void setMps_period(Map<String, Object> mps_period) {
		this.mps_period = mps_period;
	}

	public Map<String, Float> getMps_sr() {
		return mps_sr;
	}

	public void setMps_sr(Map<String, Float> mps_sr) {
		this.mps_sr = mps_sr;
	}

	public Map<String, Float> getMps_poh() {
		return mps_poh;
	}

	public void setMps_poh(Map<String, Float> mps_poh) {
		this.mps_poh = mps_poh;
	}

	public Map<String, Float> getMps_pab() {
		return mps_pab;
	}

	public void setMps_pab(Map<String, Float> mps_pab) {
		this.mps_pab = mps_pab;
	}

	public Map<String, Float> getMps_nr() {
		return mps_nr;
	}

	public void setMps_nr(Map<String, Float> mps_nr) {
		this.mps_nr = mps_nr;
	}

	public Map<String, Float> getMps_porc() {
		return mps_porc;
	}

	public void setMps_porc(Map<String, Float> mps_porc) {
		this.mps_porc = mps_porc;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Map<String, Float> getMps_por() {
		return mps_por;
	}

	public void setMps_por(Map<String, Float> mps_por) {
		this.mps_por = mps_por;
	}

	public List<Bom> getChildBomList() {
		return childBomList;
	}

	public void setChildBomList(List<Bom> childBomList) {
		this.childBomList = childBomList;
	}

	public String getStructureId() {
		return structureId;
	}

	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}
}
