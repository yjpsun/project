/**
 * 
 */
package org.erplab.service.mps;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.domain.Forecast;
import org.erplab.domain.Material;
import org.erplab.utils.CalendarUtils;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;
import org.workin.core.persistence.support.PaginationSupport;

import com.google.common.collect.Lists;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Service
@SuppressWarnings("unchecked")
public class ForecastServiceImpl extends AbstractBeanService<Forecast, Serializable> implements ForecastService {

	@Override
	public Forecast saveForecast(Forecast forecast) {
		return (Forecast)this.getPersistenceService().merge(forecast);
	}

	@Override
	public void removeForecast(Long id) {
		this.getPersistenceService().remove(Forecast.class,id);
	}

	@Override
	public PaginationSupport<Forecast> findByPagination(Forecast forecast,
			int startIndex, int pageSize) {
		String queryHql = "select m from Forecast m";
		return this.getPersistenceService().findPaginationSupport(queryHql, startIndex, pageSize);	}

	@Override
	public Forecast findForecastById(Long id) {
		return this.findById(Forecast.class, id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Forecast> findForecastByMaterialId(Long materialId) {
		List<Forecast> forecastList = Lists.newArrayList();
		if(materialId != null){
			forecastList = this.getPersistenceService().find("select f from Forecast f where f.material.id = "+materialId +" order by f.weekStart ASC");
		}
		if(CollectionUtils.isEmpty(forecastList)){
			CalendarUtils cu = new CalendarUtils(); 
			Date monday1 = cu.getMondayOFWeek();
			for(int i=0;i<8;i++){
				Forecast forecast = new Forecast();
				Date temp = new Date();
				temp.setDate(monday1.getDate()+7*i);
				forecast.setWeekStart(temp);
				forecastList.add(forecast);
			}
		}
		return forecastList;
	}

	@Override
	public List<Forecast> saveForecastList(List<Forecast> forecastList,
			Long materialId) {
		List<Forecast> newList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(forecastList)){
			Material material = new Material();
			material.setId(materialId);
			for(Forecast forecast:forecastList){
				if(forecast != null){
					forecast.setMaterial(material);
					newList.add((Forecast)this.getPersistenceService().merge(forecast));
				}
			}
		}
		return newList;
	}

	@Override
	public List<Forecast> findAllForecast() {
		return this.getPersistenceService().find("select f from Forecast f");
	}
}
