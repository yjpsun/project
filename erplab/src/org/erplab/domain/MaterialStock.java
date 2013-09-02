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
@Table(name = "materialStock")
@NamedQueries(@NamedQuery(name="getMSByMidAndRecDate",query="select m from MaterialStock m where m.material.id = ? and m.weekStart = ?"))
public class MaterialStock extends IdEntity {

	private static final long serialVersionUID = -1497895388634228417L;
	
	private Date weekStart;
	private Material material;
	private Float oh;
	private Float al;


	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name = "weekStart")
	public Date getWeekStart() {
		return weekStart;
	}

	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}

	@Column(name = "oh")
	public Float getOh() {
		return oh;
	}

	public void setOh(Float oh) {
		this.oh = oh;
	}

	@Column(name = "al")
	public Float getAl() {
		return al;
	}

	public void setAl(Float al) {
		this.al = al;
	}
	
	@Transient
	public String getWeekStartF1(){
		return DateTool.dateToString(this.getWeekStart(),Constants.DATE_FORMAT_YYYYMMDD);
	}
	
	@Transient
	public String getWeekStartF2(){
		return DateTool.dateToString(this.getWeekStart(),Constants.DATE_FORMAT_YYYY_MM_DD);
	}
}
