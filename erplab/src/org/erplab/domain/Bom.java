/**
 * 
 */
package org.erplab.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.workin.core.entity.IdEntity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author lawrence.wang
 * 
 *         2011-4-29
 */
@Entity
@Table(name = "bom")
public class Bom extends IdEntity {

	private static final long serialVersionUID = -6466053783528066088L;

	private String structure_id;
	private String father_material_id;
	private String child_material_id;
	private Float qp;
	private Float scraprate;
	private Integer ot;
	private String edition;
	private String matPs;
	
	private Material material;
	private Map<String,Float> mps_gr = Maps.newHashMap();
	private Map<String,Float> mps_sr = Maps.newHashMap();
	private Map<String,Float> mps_poh = Maps.newHashMap();
	private Map<String,Float> mps_pab = Maps.newHashMap();
	private Map<String,Float> mps_nr = Maps.newHashMap();
	private Map<String,Float> mps_porc = Maps.newHashMap();
	private Map<String,Float> mps_por = Maps.newHashMap();
	private Map<String,Float> mps_al = Maps.newHashMap();
	private Map<String,Float> mps_oh = Maps.newHashMap();
	private Map<String,Float> mps_sumption = Maps.newHashMap();
	private Map<String,Float> mps_mps = Maps.newHashMap();
	private Map<String,Float> mps_atp = Maps.newHashMap();
	private Map<String,Float> mps_atp_adjust = Maps.newHashMap();
	
	
	List<Map<String,Float>> grList = Lists.newArrayList(); 

	@Column(name = "father_material_id", length = 5)
	public String getFather_material_id() {
		return father_material_id;
	}

	public void setFather_material_id(String father_material_id) {
		this.father_material_id = father_material_id;
	}

	@Column(name = "child_material_id", length = 5)
	public String getChild_material_id() {
		return child_material_id;
	}

	public void setChild_material_id(String child_material_id) {
		this.child_material_id = child_material_id;
	}

	@Column(name = "qp")
	public Float getQp() {
		return qp;
	}

	public void setQp(Float qp) {
		this.qp = qp;
	}

	@Column(name = "scraprate")
	public Float getScraprate() {
		return scraprate;
	}

	public void setScraprate(Float scraprate) {
		this.scraprate = scraprate;
	}

	@Column(name = "ot")
	public Integer getOt() {
		return ot;
	}

	public void setOt(Integer ot) {
		this.ot = ot;
	}

	@Column(name = "edition", length = 10)
	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@Column(name = "structure_id", length = 10)
	public String getStructure_id() {
		return structure_id;
	}

	public void setStructure_id(String structure_id) {
		this.structure_id = structure_id;
	}

	@Transient
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Transient
	public Map<String, Float> getMps_gr() {
		return mps_gr;
	}

	public void setMps_gr(Map<String, Float> mps_gr) {
		this.mps_gr = mps_gr;
	}

	@Transient
	public Map<String, Float> getMps_sr() {
		return mps_sr;
	}

	public void setMps_sr(Map<String, Float> mps_sr) {
		this.mps_sr = mps_sr;
	}

	@Transient
	public Map<String, Float> getMps_poh() {
		return mps_poh;
	}

	public void setMps_poh(Map<String, Float> mps_poh) {
		this.mps_poh = mps_poh;
	}

	@Transient
	public Map<String, Float> getMps_pab() {
		return mps_pab;
	}

	public void setMps_pab(Map<String, Float> mps_pab) {
		this.mps_pab = mps_pab;
	}

	@Transient
	public Map<String, Float> getMps_nr() {
		return mps_nr;
	}

	public void setMps_nr(Map<String, Float> mps_nr) {
		this.mps_nr = mps_nr;
	}

	@Transient
	public Map<String, Float> getMps_porc() {
		return mps_porc;
	}

	public void setMps_porc(Map<String, Float> mps_porc) {
		this.mps_porc = mps_porc;
	}

	@Transient
	public Map<String, Float> getMps_por() {
		return mps_por;
	}

	public void setMps_por(Map<String, Float> mps_por) {
		this.mps_por = mps_por;
	}

	@Transient
	public Map<String, Float> getMps_al() {
		return mps_al;
	}

	public void setMps_al(Map<String, Float> mps_al) {
		this.mps_al = mps_al;
	}

	@Transient
	public Map<String, Float> getMps_oh() {
		return mps_oh;
	}

	public void setMps_oh(Map<String, Float> mps_oh) {
		this.mps_oh = mps_oh;
	}

	@Transient
	public Map<String, Float> getMps_sumption() {
		return mps_sumption;
	}

	public void setMps_sumption(Map<String, Float> mps_sumption) {
		this.mps_sumption = mps_sumption;
	}

	@Transient
	public List<Map<String, Float>> getGrList() {
		return grList;
	}

	public void setGrList(List<Map<String, Float>> grList) {
		this.grList = grList;
	}

	@Transient
	public Map<String, Float> getMps_mps() {
		return mps_mps;
	}

	public void setMps_mps(Map<String, Float> mps_mps) {
		this.mps_mps = mps_mps;
	}

	@Transient
	public Map<String, Float> getMps_atp() {
		return mps_atp;
	}

	public void setMps_atp(Map<String, Float> mps_atp) {
		this.mps_atp = mps_atp;
	}

	@Transient
	public Map<String, Float> getMps_atp_adjust() {
		return mps_atp_adjust;
	}

	public void setMps_atp_adjust(Map<String, Float> mps_atp_adjust) {
		this.mps_atp_adjust = mps_atp_adjust;
	}

	@Transient
	public String getMatPs() {
		return matPs;
	}

	public void setMatPs(String matPs) {
		this.matPs = matPs;
	}
}
