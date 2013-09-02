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
import javax.persistence.Transient;

import org.erplab.common.Constants;
import org.erplab.utils.DateTool;
import org.workin.core.entity.IdEntity;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "saleApply")
public class SaleApply extends IdEntity{

	private static final long serialVersionUID = 4233543677127767676L;
	private Material material;
	private Float purQ;
	private Date demDate;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name = "demDate")
	public Date getDemDate() {
		return demDate;
	}

	public void setDemDate(Date demDate) {
		this.demDate = demDate;
	}
	
	@Column(name = "purQ")
	public Float getPurQ() {
		return purQ;
	}

	public void setPurQ(Float purQ) {
		this.purQ = purQ;
	}
	
	@Transient
	public String getDemDateF1(){
		return DateTool.dateToString(this.getDemDate(),Constants.DATE_FORMAT_YYYYMMDD);
	}
	
	@Transient
	public String getDemDateF2(){
		return DateTool.dateToString(this.getDemDate(),Constants.DATE_FORMAT_YYYY_MM_DD);
	}
}
