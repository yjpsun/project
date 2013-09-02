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
 * @author Administrator
 *
 */
@Entity
@Table(name = "apply")
@NamedQueries({
	@NamedQuery(name = "getMinDate", query="select min(o.demDate) from Apply o where o.material.id = ?"),
	@NamedQuery(name = "getSumApplyList", query="select o from Apply o where o.material.id = ? and o.demDate >= ? and o.demDate< ?")
})
public class Apply extends IdEntity{

	private static final long serialVersionUID = 4233543677127767676L;
	private Material material;
	private Float purQ;
	private Date demDate;
	
	private Date purDate;
	private Date delDate;
	
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

	@Transient
	public Date getPurDate() {
		return purDate;
	}
	

	public void setPurDate(Date purDate) {
		this.purDate = purDate;
	}
	
	@Transient
	public String getPurDateF1(){
		return DateTool.dateToString(this.getPurDate(),Constants.DATE_FORMAT_YYYYMMDD);
	}
	
	@Transient
	public String getPurDateF2(){
		return DateTool.dateToString(this.getPurDate(),Constants.DATE_FORMAT_YYYY_MM_DD);
	}


	@Transient
	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	
	@Transient
	public String getDelDateF1(){
		return DateTool.dateToString(this.getDelDate(),Constants.DATE_FORMAT_YYYYMMDD);
	}
	
	@Transient
	public String getDelDateF2(){
		return DateTool.dateToString(this.getDelDate(),Constants.DATE_FORMAT_YYYY_MM_DD);
	}
}
