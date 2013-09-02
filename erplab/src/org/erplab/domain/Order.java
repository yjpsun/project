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
@Table(name = "orderDetail")
@NamedQueries(
	@NamedQuery(name = "orderSumQuery", query="select o from Order o where o.material.id = ? and o.demDate >= ? and o.demDate< ?")
)
public class Order extends IdEntity{

	private static final long serialVersionUID = 8524545698669437447L;

	private Material material;
	private Float quantity;
	private Date demDate;
	private String recRej;
	private String computation;
	
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

	@Column(name = "recRej",length=5)
	public String getRecRej() {
		return recRej;
	}

	public void setRecRej(String recRej) {
		this.recRej = recRej;
	}

	@Column(name = "quantity")
	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Column(name = "computation", length=10)
	public String getComputation() {
		return computation;
	}

	public void setComputation(String computation) {
		this.computation = computation;
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
