/**
 * 
 */
package org.erplab.service.mps;

import java.util.List;

import org.erplab.domain.Forecast;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface ForecastService {
	
	public Forecast saveForecast(Forecast forecast);

	public void removeForecast(Long id);
	
	public PaginationSupport<Forecast> findByPagination(Forecast forecast,int startIndex,
			int pageSize);
	
	public Forecast findForecastById(Long id);
	
	public List<Forecast> findAllForecast();
	
	public List<Forecast> findForecastByMaterialId(Long materialId);
	
	public List<Forecast> saveForecastList(List<Forecast> forecastList, Long materialId);
}
