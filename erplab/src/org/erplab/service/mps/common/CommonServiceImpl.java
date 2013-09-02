/**
 * 
 */
package org.erplab.service.mps.common;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.erplab.common.Constants;
import org.erplab.domain.Forecast;
import org.erplab.domain.WeekPriod;
import org.erplab.service.mps.ForecastService;
import org.erplab.utils.CalendarUtils;
import org.erplab.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workin.core.persistence.support.AbstractBeanService;

import com.google.common.collect.Lists;

/**
 * @author lawrence.wang
 * 
 *         2011-5-1
 */
@SuppressWarnings("rawtypes")
@Service
public class CommonServiceImpl extends AbstractBeanService implements CommonService {

	@Autowired
	private ForecastService forecastService;

	@SuppressWarnings("deprecation")
	@Override
	public List<WeekPriod> getCurPriods() {
		List<WeekPriod> curPriods = Lists.newArrayList();
		CalendarUtils cu = new CalendarUtils();
		Date monday1 = cu.getMondayOFWeek();
		for (int i = 0; i < 8; i++) {
			WeekPriod priod = new WeekPriod();
			Date temp = new Date();
			temp.setDate(monday1.getDate() + 7 * i);
			priod.setPriodCount(String.valueOf(i));
			priod.setPriodDate(temp);
			priod.setPriodDateF1(DateTool.dateToString(temp, Constants.DATE_FORMAT_YYYYMMDD));
			priod.setPriodDateF2(DateTool.dateToString(temp, Constants.DATE_FORMAT_YYYY_MM_DD));
			curPriods.add(priod);
		}
		return curPriods;
	}

	@Override
	public List<WeekPriod> getPeriodsByMatId(Long materialId) {
		List<WeekPriod> wp = Lists.newArrayList();
		List<Forecast> forecastList = forecastService.findForecastByMaterialId(materialId);
		if (CollectionUtils.isNotEmpty(forecastList)) {
			for (int i = 0; i < forecastList.size(); i++) {
				if (forecastList.get(i) != null) {
					WeekPriod tempWp = new WeekPriod();
					tempWp.setPriodCount(String.valueOf(i + 1));
					tempWp.setPriodDate(forecastList.get(i).getWeekStart());
					tempWp.setPriodDateF1(DateTool.dateToString(tempWp.getPriodDate(),
							Constants.DATE_FORMAT_YYYYMMDD));
					tempWp.setPriodDateF2(DateTool.dateToString(tempWp.getPriodDate(),
							Constants.DATE_FORMAT_YYYY_MM_DD));
					wp.add(tempWp);
				}
			}
		}
		return wp;
	}
}
