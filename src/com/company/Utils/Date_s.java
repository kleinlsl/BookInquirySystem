package com.company.Utils;

import java.util.Date;//导入日期类
import java.text.SimpleDateFormat;//导入简单的日期格式类
public class Date_s{
	public static String h_Date(){  //静态方法
		long startTime = System.currentTimeMillis();//获取当前时间（以毫秒为单位）
		Date time=new Date(startTime);//创建日期对象；Date t=new Date(long ms)
		String pattern="yyyy/MM/dd  E HH:mm";//创建字符型日期格式模板
		SimpleDateFormat SDF=new SimpleDateFormat(pattern);//创建简单日期格式；以模板为参数
		String timePattern=SDF.format(time);
		return timePattern;
	}
}