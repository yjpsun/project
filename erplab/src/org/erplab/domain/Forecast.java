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
@Table(name = "forecast")
public class Forecast extends IdEntity{

	private static final long serialVersionUID = 5312704336393930111L;
	
	private Material material;
	private Date weekStart;
	private Float weekForecast;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name="weekStart")
	public Date getWeekStart() {
		return weekStart;
	}

	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}

	@Column(name="weekForecast")
	public Float getWeekForecast() {
		return weekForecast;
	}

	public void setWeekForecast(Float weekForecast) {
		this.weekForecast = weekForecast;
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
