package com.xrb.util;

import java.text.DecimalFormat;

public class FormatSize {
	public static String size(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String size = "参数错误,转换失败";
		
		if(fileSize<1024) {
			size = df.format((double)fileSize)+"B";
		}else if(fileSize<(1024*1024)) {
			size = df.format((double)fileSize/1024)+"KB";
		}else if(fileSize<(1024*1024*1024)) {
			size = df.format((double)fileSize/(1024*1024))+"MB";
		}else {
			size = df.format((double)fileSize/(1024*1024*1024))+"GB";
		}
		return size;
	}
}
