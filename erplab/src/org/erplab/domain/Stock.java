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
@Table(name = "stock")
public class Stock extends IdEntity{
	private static final long serialVersionUID = 8598281962045254814L;
	private Material material;
	private Float rincomingQ;
	private Date incomingdate;
	private String stocktype;
	private Float pincomingQ;
	private Long purchaseOrderId;
	private Float unitP;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name = "incomingdate")
	public Date getIncomingdate() {
		return incomingdate;
	}

	public void setIncomingdate(Date incomingdate) {
		this.incomingdate = incomingdate;
	}
	
	@Column(name="stocktype",length=20)
	public String getStocktype() {
		return stocktype;
	}
	public void setStocktype(String stocktype) {
		this.stocktype = stocktype;
	}
	
	@Transient
	public String getIncomingdateF1(){
		return DateTool.dateToString(this.getIncomingdate(),Constants.DATE_FORMAT_YYYYMMDD);
	}
	
	@Transient
	public String getIncomingdateF2(){
		return DateTool.dateToString(this.getIncomingdate(),Constants.DATE_FORMAT_YYYY_MM_DD);
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	@Column(name="rincomingQ")
	public Float getRincomingQ() {
		return rincomingQ;
	}

	public void setRincomingQ(Float rincomingQ) {
		this.rincomingQ = rincomingQ;
	}

	@Column(name="pincomingQ")
	public Float getPincomingQ() {
		return pincomingQ;
	}

	public void setPincomingQ(Float pincomingQ) {
		this.pincomingQ = pincomingQ;
	}
	
	@Column(name="unitP")
	public Float getUnitP() {
		return unitP;
	}

	public void setUnitP(Float unitP) {
		this.unitP = unitP;
	}
}
