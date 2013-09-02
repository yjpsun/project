/**
 * 
 */
package org.erplab.domain;

/**
 * @author lawrence.wang
 * 
 *         2011-5-24
 */
public class StockQuery {

	private Material material;
	private Float oh;
	private Float sr;
	private Float matSr;

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Float getOh() {
		return oh;
	}

	public void setOh(Float oh) {
		this.oh = oh;
	}

	public Float getSr() {
		return sr;
	}

	public void setSr(Float sr) {
		this.sr = sr;
	}

	public Float getMatSr() {
		return matSr;
	}

	public void setMatSr(Float matSr) {
		this.matSr = matSr;
	}
}
