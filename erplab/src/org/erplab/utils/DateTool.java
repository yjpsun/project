/**
 * 
 */
package org.erplab.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.workin.util.DateUtils;

/**
 * @author Administrator
 *
 */
public class DateTool {

	
	public static String nowToString(){
		return dateToString(new Date(),"");
	}
	
	public static String dateToString(Date date,String formate){
		SimpleDateFormat sdf = null;
		if(StringUtils.isEmpty(formate)){
			sdf = new SimpleDateFormat(DateUtils.PATTEN_DATE_FORMAT_DEFAULT);
		}else{
			sdf = new SimpleDateFormat(formate);
		}
		return sdf.format(date);
	}
	
	@SuppressWarnings("deprecation")
	public static Date removeTimeValue(Date date){
		if(date != null){
			int year = date.getYear();
			int month =date.getMonth();
			int day = date.getDate();
			return new Date(year,month,day);
		}
		return null;
	}
	
	
	@SuppressWarnings("deprecation")
	public static Date nextNday(Date date,int n){
		if(date != null){
			int year = date.getYear();
			int month =date.getMonth();
			int day = date.getDate()+n;
			return new Date(year,month,day);
		}
		return null;
	}
}
