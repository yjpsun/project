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
@Table(name = "timeGrating")
public class TimeGrating extends IdEntity{

	private static final long serialVersionUID = 5927628780095818057L;
	
	private Material material;
	private Long dtf;
	private long ptf;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name="dtf")
	public Long getDtf() {
		return dtf;
	}

	public void setDtf(Long dtf) {
		this.dtf = dtf;
	}

	@Column(name="ptf")
	public long getPtf() {
		return ptf;
	}

	public void setPtf(long ptf) {
		this.ptf = ptf;
	}
}
