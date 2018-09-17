package com.dvsmedeiros.bce.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BceUtils {
	
	private static SimpleDateFormat sdf;
	
	public static String CalendarToString(Calendar calendar, String pattern) {
		sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}
		
	public static Calendar startDay(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTime(calendar.getTime());
		
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c;
	}
	
	public static Calendar endDay(Calendar calendar) {
		Calendar c = Calendar.getInstance();
		c.setTime(calendar.getTime());
		
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c;
	}
}
