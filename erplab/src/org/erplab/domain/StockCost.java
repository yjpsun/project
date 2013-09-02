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
@Table(name = "StockCost")

public class StockCost extends IdEntity{

	private static final long serialVersionUID = 786841657979980025L;
	
	private Material material;
	private Float storeLimit;
	private Float ustorecost;
	private Float beyondUSC;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name="storeLimit")
	public Float getStoreLimit() {
		return storeLimit;
	}
	public void setStoreLimit(Float storeLimit) {
		this.storeLimit = storeLimit;
	}
	
	@Column(name="ustorecost")
	public Float getUstorecost() {
		return ustorecost;
	}
	public void setUstorecost(Float ustorecost) {
		this.ustorecost = ustorecost;
	}
	
	@Column(name="beyondUSC")
	public Float getBeyondUSC() {
		return beyondUSC;
	}
	public void setBeyondUSC(Float beyondUSC) {
		this.beyondUSC = beyondUSC;
	}
	
}
