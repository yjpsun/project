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
 * @author lawrence.wang
 *
 * 2011-5-17
 */
@Entity
@Table(name = "outStock")
public class OutStock extends IdEntity{

	private static final long serialVersionUID = -861464946943479820L;
	private Material material;
	private Long salesId;
	private Float poutQ;
	private Float routQ;
	private Date outDate;
	private String outStockType;
	
	@Column(name="salesId")
	public Long getSalesId() {
		return salesId;
	}
	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}
	
	@Column(name="poutQ")
	public Float getPoutQ() {
		return poutQ;
	}
	public void setPoutQ(Float poutQ) {
		this.poutQ = poutQ;
	}
	
	@Column(name="routQ")
	public Float getRoutQ() {
		return routQ;
	}
	public void setRoutQ(Float routQ) {
		this.routQ = routQ;
	}
	
	@Column(name="outDate")
	public Date getOutDate() {
		return outDate;
	}
	
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	@Transient
	public String getOutDateF1(){
		return DateTool.dateToString(this.getOutDate(),Constants.DATE_FORMAT_YYYYMMDD);
	}
	
	@Transient
	public String getOutDateF2(){
		return DateTool.dateToString(this.getOutDate(),Constants.DATE_FORMAT_YYYY_MM_DD);
	}
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	@Column(name="outStockType",length=20)
	public String getOutStockType() {
		return outStockType;
	}
	public void setOutStockType(String outStockType) {
		this.outStockType = outStockType;
	}
}
