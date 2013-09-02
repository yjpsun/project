/**
 * 
 */
package org.erplab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.workin.core.entity.IdEntity;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "material")
public class Material extends IdEntity{

	private static final long serialVersionUID = 5927628780095818057L;
	
	private String code;
	private String computation;
	private String matPs;
	private String matLsr;
	private Float matLs;
	private String structureId;
	private String sourceOrigin;
	private Long matLt;
	private String virtualMat;
	private Float matSs;
	private Float minpur;
	private Float maxpur;
	private Float purInt;
	private Long mul;
	private Float yield;
	private Float betwDemDate;
	
	private String oldCode;
	
	@Column(name="code",length=5)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="computation",length=5)
	public String getComputation() {
		return computation;
	}
	public void setComputation(String computation) {
		this.computation = computation;
	}
	
	@Column(name="matPs",length=5)
	public String getMatPs() {
		return matPs;
	}
	public void setMatPs(String matPs) {
		this.matPs = matPs;
	}
	
	@Column(name="matLsr",length=10000)
	public String getMatLsr() {
		return matLsr;
	}
	public void setMatLsr(String matLsr) {
		this.matLsr = matLsr;
	}
	
	@Column(name="matLs")
	public Float getMatLs() {
		return matLs;
	}
	public void setMatLs(Float matLs) {
		this.matLs = matLs;
	}
	
	@Column(name="structureId",length=10)
	public String getStructureId() {
		return structureId;
	}
	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	
	@Column(name="sourceOrigin",length=10000)
	public String getSourceOrigin() {
		return sourceOrigin;
	}
	public void setSourceOrigin(String sourceOrigin) {
		this.sourceOrigin = sourceOrigin;
	}
	
	@Column(name="matLt")
	public Long getMatLt() {
		return matLt;
	}
	public void setMatLt(Long matLt) {
		this.matLt = matLt;
	}
	
	@Column(name="virtualMat",length=5)
	public String getVirtualMat() {
		return virtualMat;
	}
	public void setVirtualMat(String virtualMat) {
		this.virtualMat = virtualMat;
	}
	
	@Column(name="matSs")
	public Float getMatSs() {
		return matSs;
	}
	public void setMatSs(Float matSs) {
		this.matSs = matSs;
	}
	
	@Column(name="minpur")
	public Float getMinpur() {
		return minpur;
	}
	public void setMinpur(Float minpur) {
		this.minpur = minpur;
	}
	
	@Column(name="maxpur")
	public Float getMaxpur() {
		return maxpur;
	}
	public void setMaxpur(Float maxpur) {
		this.maxpur = maxpur;
	}
	
	@Column(name="purInt")
	public Float getPurInt() {
		return purInt;
	}
	public void setPurInt(Float purInt) {
		this.purInt = purInt;
	}
	
	@Column(name="mul")
	public Long getMul() {
		return mul;
	}
	public void setMul(Long mul) {
		this.mul = mul;
	}
	
	@Transient
	public String getOldCode() {
		return oldCode;
	}
	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}
	
	@Column(name="yield")
	public Float getYield() {
		return yield;
	}
	public void setYield(Float yield) {
		this.yield = yield;
	}
	
	@Column(name="betwDemDate")
	public Float getBetwDemDate() {
		return betwDemDate;
	}
	public void setBetwDemDate(Float betwDemDate) {
		this.betwDemDate = betwDemDate;
	}
}
