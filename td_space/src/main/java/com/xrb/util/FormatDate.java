package com.xrb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
	
	public static String date(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

}
