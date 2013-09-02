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
@Table(name = "inventory")
public class Inventory extends IdEntity{
	private static final long serialVersionUID = 4960259478663238795L;
	private Material material;
	private Float bookOH;
	private Float bookvalue;
	private Float inventoryOH;
	private Float inventoryvalue;
	private Float ohlp;
	private Float valueLP;
	private Float noftolerance;
	private Float aoftolerance;
	private String countpeople;
	private Date inventorytime;
	
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
	@Column(name="bookOH",length=5)
	public Float getBookOH() {
		return bookOH;
	}
	public void setBookOH(Float bookOH) {
		this.bookOH = bookOH;
	}
	
	@Column(name="bookvalue")
	public Float getBookvalue() {
		return bookvalue;
	}
	public void setBookvalue(Float bookvalue) {
		this.bookvalue = bookvalue;
	}
	@Column(name="inventoryOH")
	public Float getInventoryOH() {
		return inventoryOH;
	}
	public void setInventoryOH(Float inventoryOH) {
		this.inventoryOH = inventoryOH;
	}
	@Column(name="inventoryvalue")
	public Float getInventoryvalue() {
		return inventoryvalue;
	}
	public void setInventoryvalue(Float inventoryvalue) {
		this.inventoryvalue = inventoryvalue;
	}
	@Column(name="ohlp")
	public Float getOhlp() {
		return ohlp;
	}
	public void setOhlp(Float ohlp) {
		this.ohlp = ohlp;
	}
	@Column(name="valueLP")
	public Float getValueLP() {
		return valueLP;
	}
	public void setValueLP(Float valueLP) {
		this.valueLP = valueLP;
	}
	@Column(name="noftolerance")
	public Float getNoftolerance() {
		return noftolerance;
	}
	public void setNoftolerance(Float noftolerance) {
		this.noftolerance = noftolerance;
	}
	@Column(name="aoftolerance")
	public Float getAoftolerance() {
		return aoftolerance;
	}
	public void setAoftolerance(Float aoftolerance) {
		this.aoftolerance = aoftolerance;
	}
	
	
	@Column(name="countpeople",length=100)
	public String getCountpeople() {
		return countpeople;
	}
	public void setCountpeople(String countpeople) {
		this.countpeople = countpeople;
	}
	
	@Column(name = "inventorytime")
	public Date getInventorytime() {
		return inventorytime;
	}

	public void setInventorytime(Date inventorytime) {
		this.inventorytime = inventorytime;
	}
	
	@Transient
	public String getInventorytimeF1(){
		return DateTool.dateToString(this.getInventorytime(),Constants.DATE_FORMAT_YYYYMMDD);
	}
	
	@Transient
	public String getInventorytimeF2(){
		return DateTool.dateToString(this.getInventorytime(),Constants.DATE_FORMAT_YYYY_MM_DD);
	}
	
}
