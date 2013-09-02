/**
 * 
 */
package org.erplab.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.erplab.common.Constants;
import org.erplab.utils.DateTool;
import org.workin.core.entity.IdEntity;

/**
 * @author lawrence.wang
 * 
 *         2011-4-29
 */
@Entity
@Table(name = "materialDelivering")
@NamedQueries(@NamedQuery(name="getMDByMidAndRecDate",query="select m from MaterialDelivering m where m.material.id = ? and m.recDate = ?"))
public class MaterialDelivering extends IdEntity {

	private static final long serialVersionUID = 8742447902433345024L;

	private Date recDate;
	private Material material;
	private Float sr;
	private Float matSr;

	@Column(name = "recDate")
	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name = "sr")
	public Float getSr() {
		return sr;
	}

	public void setSr(Float sr) {
		this.sr = sr;
	}

	@Column(name = "matSr")
	public Float getMatSr() {
		return matSr;
	}

	public void setMatSr(Float matSr) {
		this.matSr = matSr;
	}
	
	@Transient
	public String getRecDateF1(){
		return DateTool.dateToString(this.getRecDate(),Constants.DATE_FORMAT_YYYYMMDD);
	}
	
	@Transient
	public String getRecDateF2(){
		return DateTool.dateToString(this.getRecDate(),Constants.DATE_FORMAT_YYYY_MM_DD);
	}
}
