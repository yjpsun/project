/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.domain.Material;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.report.MpsReportService;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-7
 */
@Results({
	@Result(name = "reportRs", location = "/WEB-INF/content/mps/report-rs.jsp"),
	@Result(name = "bomReport", location = "/WEB-INF/content/mps/bom-report.jsp"),
	@Result(name = "bomReportRs", location = "/WEB-INF/content/mps/bom-report-rs.jsp")
})
public class ReportAction extends ActionSupport{

	private static final long serialVersionUID = 3361676425681949368L;
	private List<Material> materialList;
	private Long materialId;
	
	private Map<String,Object> mps_period = Maps.newHashMap();
	private Map<String,Float> mps_gr = Maps.newHashMap();
	private Map<String,Float> mps_sr = Maps.newHashMap();
	private Map<String,Float> mps_poh = Maps.newHashMap();
	private Map<String,Float> mps_pab = Maps.newHashMap();
	private Map<String,Float> mps_nr = Maps.newHashMap();
	private Map<String,Float> mps_porc = Maps.newHashMap();
	private Map<String,Float> mps_por = Maps.newHashMap();
	private Map<String,Float> mps_mps = Maps.newHashMap();
	private Map<String,Float> mps_atp = Maps.newHashMap();
	private Map<String,Float> mps_atp_adjust = Maps.newHashMap();
	
	private Material material;
	private Float oh;
	private Float al;
	
	
	@Autowired
	private MaterialService materialService;
	@Autowired
	private MpsReportService mpsReportService;
	
	public String execute() throws Exception{
		materialList  = materialService.findCompleteMaterial();
		return SUCCESS;
	}
	
	public String bomReport() throws Exception{
		materialList  = materialService.findCompleteMaterial();
		return "bomReport";
	}

	public String buildReport() throws Exception{
		try {
			material = materialService.findMaterialById(materialId);
			oh = mpsReportService.getMPS_OH_byPeriod(materialId, 1);
			al = mpsReportService.getMPS_AL_byPeriod(materialId, 1);
			
			mps_period = mpsReportService.getMPS_Period(materialId);
			mps_gr = mpsReportService.getMPS_GR(materialId);
			mps_sr = mpsReportService.getMPS_SR(materialId);
			mps_poh = mpsReportService.getMPS_POH(materialId);
			mps_pab = mpsReportService.getMPS_PAB(materialId);
			mps_nr = mpsReportService.getMPS_NR(materialId);
			mps_porc = mpsReportService.getMPS_PORC(materialId);
			mps_por = mpsReportService.getMPS_POR(mps_porc,material.getMatLt());
			mps_mps = mpsReportService.getMPS_MPS(materialId);
			
			if("Y".equals(material.getMatPs())){
				mps_atp = mpsReportService.getMPS_ATP(materialId);
				mps_atp_adjust = adjustATP(mps_atp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "reportRs";
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

	public Float getOh() {
		return oh;
	}

	public void setOh(Float oh) {
		this.oh = oh;
	}

	public Float getAl() {
		return al;
	}

	public void setAl(Float al) {
		this.al = al;
	}

	public Map<String, Float> getMps_por() {
		return mps_por;
	}

	public void setMps_por(Map<String, Float> mps_por) {
		this.mps_por = mps_por;
	}

	public Map<String, Float> getMps_mps() {
		return mps_mps;
	}

	public void setMps_mps(Map<String, Float> mps_mps) {
		this.mps_mps = mps_mps;
	}

	public Map<String, Float> getMps_atp() {
		return mps_atp;
	}

	public void setMps_atp(Map<String, Float> mps_atp) {
		this.mps_atp = mps_atp;
	}

	public Map<String, Float> getMps_atp_adjust() {
		return mps_atp_adjust;
	}

	public void setMps_atp_adjust(Map<String, Float> mps_atp_adjust) {
		this.mps_atp_adjust = mps_atp_adjust;
	}
}
