/**
 * 
 */
package org.erplab.domain;

import java.util.Date;

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
@Table(name = "storage")
public class Storage extends IdEntity{
	
	private static final long serialVersionUID = 1722434556478317932L;
	
	private Material material;
	private Float oh;
	private Date weekStart;
	private Float al;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name="weekStart")
	public Date getWeekStart() {
		return weekStart;
	}

	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}
	
	@Column(name="oh")
	public Float getOh() {
		return oh;
	}
	public void setOh(Float oh) {
		this.oh = oh;
	}
	
	@Column(name="al")
	public Float getAl() {
		return al;
	}
	public void setAl(Float al) {
		this.al = al;
	}
}
