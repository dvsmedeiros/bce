package com.dvsmedeiros.bce.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;

public class BceUtils {
	
	private static SimpleDateFormat sdf;
	
	public static String CalendarToString(Calendar calendar, String pattern) {
		sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}
}
