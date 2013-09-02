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
@Table(name = "purchaseOrder")
public class PurchaseOrder extends IdEntity {

	private static final long serialVersionUID = 4519933401551491758L;
	private Material material;
	private Float sls;
	private Float unitP;
	private Float st;
	private Float purQ;
	private Date demDate;
	private Date purDate;
	private Date delDate;
	private Long supplyerId;
	private Float sumption;
	private String slsr;
	
	@ManyToOne
	@JoinColumn(name = "material_id")
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

	@Column(name = "purQ")
	public Float getPurQ() {
		return purQ;
	}

	public void setPurQ(Float purQ) {
		this.purQ = purQ;
	}

	@Column(name = "demDate")
	public Date getDemDate() {
		return demDate;
	}

	public void setDemDate(Date demDate) {
		this.demDate = demDate;
	}

	@Transient
	public String getDemDateF1() {
		return DateTool.dateToString(this.getDemDate(), Constants.DATE_FORMAT_YYYYMMDD);
	}

	@Transient
	public String getDemDateF2() {
		return DateTool.dateToString(this.getDemDate(), Constants.DATE_FORMAT_YYYY_MM_DD);
	}

	@Column(name = "purDate")
	public Date getPurDate() {
		return purDate;
	}

	public void setPurDate(Date purDate) {
		this.purDate = purDate;
	}

	@Transient
	public String getPurDateF1() {
		return DateTool.dateToString(this.getPurDate(), Constants.DATE_FORMAT_YYYYMMDD);
	}

	@Transient
	public String getPurDateF2() {
		return DateTool.dateToString(this.getPurDate(), Constants.DATE_FORMAT_YYYY_MM_DD);
	}

	@Column(name = "delDate")
	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	@Transient
	public String getDelDateF1() {
		return DateTool.dateToString(this.getDelDate(), Constants.DATE_FORMAT_YYYYMMDD);
	}

	@Transient
	public String getDelDateF2() {
		return DateTool.dateToString(this.getDelDate(), Constants.DATE_FORMAT_YYYY_MM_DD);
	}

	@Column(name = "supplyerId")
	public Long getSupplyerId() {
		return supplyerId;
	}

	public void setSupplyerId(Long supplyerId) {
		this.supplyerId = supplyerId;
	}

	@Column(name = "sumption")
	public Float getSumption() {
		return sumption;
	}

	public void setSumption(Float sumption) {
		this.sumption = sumption;
	}

	@Column(name = "slsr")
	public String getSlsr() {
		return slsr;
	}

	public void setSlsr(String slsr) {
		this.slsr = slsr;
	}
}
