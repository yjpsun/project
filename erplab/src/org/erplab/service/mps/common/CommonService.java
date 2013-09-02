/**
 * 
 */
package org.erplab.service.mps.common;

import java.util.List;

import org.erplab.domain.WeekPriod;

/**
 * @author lawrence.wang
 *
 * 2011-5-1
 */
public interface CommonService {

	public List<WeekPriod> getCurPriods();
	
	public List<WeekPriod> getPeriodsByMatId(Long materialId);
}
