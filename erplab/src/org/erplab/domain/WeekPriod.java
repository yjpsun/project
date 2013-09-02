/**
 * 
 */
package org.erplab.domain;

import java.util.Date;

/**
 * @author lawrence.wang
 * 
 *         2011-5-1
 */
public class WeekPriod {

	public String priodCount;
	public Date priodDate;
	public String priodDateF1;
	public String priodDateF2;

	public String getPriodCount() {
		return priodCount;
	}

	public void setPriodCount(String priodCount) {
		this.priodCount = priodCount;
	}

	public Date getPriodDate() {
		return priodDate;
	}

	public void setPriodDate(Date priodDate) {
		this.priodDate = priodDate;
	}

	public String getPriodDateF1() {
		return priodDateF1;
	}

	public void setPriodDateF1(String priodDateF1) {
		this.priodDateF1 = priodDateF1;
	}

	public String getPriodDateF2() {
		return priodDateF2;
	}

	public void setPriodDateF2(String priodDateF2) {
		this.priodDateF2 = priodDateF2;
	}
}
