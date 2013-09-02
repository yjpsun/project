/**
 * 
 */
package org.erplab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.workin.core.entity.IdEntity;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "supplyer")
//@NamedQueries(
//	@NamedQuery(name = "orderSumQuery", query="select o from Order o where o.material.id = ? and o.demDate >= ? and o.demDate< ?")
//)
public class Supplyer extends IdEntity{

	private static final long serialVersionUID = 4519933401551491758L;
	private Material material;
	private String code;
	private Float sls;
	private Float unitP;
	private Float st;
	private Float maxS;
	private String slsr;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name = "sls")
	public Float getSls() {
		return sls;
	}

	public void setSls(Float sls) {
		this.sls = sls;
	}

	@Column(name = "unitP")
	public Float getUnitP() {
		return unitP;
	}

	public void setUnitP(Float unitP) {
		this.unitP = unitP;
	}

	@Column(name = "st")
	public Float getSt() {
		return st;
	}

	public void setSt(Float st) {
		this.st = st;
	}

	@Column(name = "maxS")
	public Float getMaxS() {
		return maxS;
	}

	public void setMaxS(Float maxS) {
		this.maxS = maxS;
	}

	@Column(name = "slsr")
	public String getSlsr() {
		return slsr;
	}

	public void setSlsr(String slsr) {
		this.slsr = slsr;
	}

	@Column(name = "code",length=50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
