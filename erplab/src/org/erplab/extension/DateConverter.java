package org.erplab.extension;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workin.util.DateUtils;
import org.workin.util.StringUtils;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class DateConverter extends DefaultTypeConverter {
	private static final transient Logger logger = LoggerFactory.getLogger(DateConverter.class);
	private static final String DATE_PATTERN = "dd-MMM-yyyy";
	private static final String DATE_PATTERN_CN = "yyyy-MM-dd";
	private static final String DATE_PATTERN_SLASH = "yyyy/MM/dd";
	private static final String DATE_PATTERN_XX = "yyyyMMdd";

	@SuppressWarnings("rawtypes")
	public Object convertValue(Map ognlContext, Object value, Class toType) {
		Object result = null;
		if (Date.class == toType)
			result = doConvertToDate(value);
		else if ((String.class == toType) && (value instanceof Date))
			result = DateUtils.dateToString((Date) value, "dd-MMM-yyyy");

		return result;
	}

	private Date doConvertToDate(Object value) {
		Date result = null;

		if (value instanceof String) {
			try {
				result = DateUtils.parseDate((String) value, new String[] {DATE_PATTERN,DATE_PATTERN_CN,DATE_PATTERN_SLASH,DATE_PATTERN_XX});
			} catch (ParseException ex) {
				ex.printStackTrace();
				logger.error(" Parse date with PATTERN hit error!" + ex.getMessage());
			}

			if ((result == null) && (!(StringUtils.isBlankOrNull((String) value))))
				try {
					result = new Date(Long.valueOf((String) value).longValue());
				} catch (Exception ex) {
					ex.printStackTrace();
					logger.error(" Converting from milliseconds to Date fails!" + ex.getMessage());
				}

		} else if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			if ((array != null) && (array.length >= 1)) {
				value = array[0];
				if (!(StringUtils.isBlankOrNull(value.toString())))
					result = doConvertToDate(array[0]);
			}

		} else if (Date.class.isAssignableFrom(value.getClass())) {
			result = (Date) value;
		}

		return result;
	}
}
