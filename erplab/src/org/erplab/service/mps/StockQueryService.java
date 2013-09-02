/**
 * 
 */
package org.erplab.service.mps;

import org.erplab.domain.StockQuery;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface StockQueryService {
	
	public StockQuery queryStock(Long matId);

}
