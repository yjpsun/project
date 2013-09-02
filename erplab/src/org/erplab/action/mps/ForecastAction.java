/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.domain.Forecast;
import org.erplab.domain.Material;
import org.erplab.service.mps.ForecastService;
import org.erplab.service.mps.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.web.struts2.GenericActionSupport;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reInput", location = "/WEB-INF/content/mps/forecast.jsp"),
	@Result(name = "reload", location = "forecast.do", type = "redirectAction")})
public class ForecastAction extends GenericActionSupport<Forecast>{

	private static final long serialVersionUID = -8819383918450519916L;
	private Long id;
	private Forecast forecast;
	private List<Forecast> forecastList;
	private List<Material> materialList;
	private Long materialId;

	@Autowired
	private ForecastService forecastService;
	@Autowired
	private MaterialService materialService;
	

	@Override
	public String list() throws Exception {
		materialList = materialService.findAllMaterial();
		forecastList = forecastService.findForecastByMaterialId(materialId);
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			forecast = forecastService.findForecastById(id);
		}
		return INPUT;
	}
	

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		forecastService.removeForecast(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		materialList = materialService.findAllMaterial();
		forecastList = forecastService.saveForecastList(forecastList, materialId);
		this.addActionMessage("Save or Update successfully!");
		return "reInput";
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}
	
	@Override
	public Forecast getModel() {
		return forecast;
	}
	public Forecast getForecast() {
		return forecast;
	}

	public void setForecast(Forecast forecast) {
		this.forecast = forecast;
	}


	public List<Forecast> getForecastList() {
		return forecastList;
	}


	public void setForecastList(List<Forecast> forecastList) {
		this.forecastList = forecastList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
}
