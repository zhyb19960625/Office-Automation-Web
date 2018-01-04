package org.csta.taoke.oa.common;

import java.sql.Date;
import java.util.Calendar;
/**
 * 功能：
 * 日期相关的工具类
 * 
 * 修订版本：
 * 2018-01-04 首次编写
 * 
 * @author 路伟饶
 *
 */
public class DateTimeTools {
	/**
	 * 将日期字符串转换为Date对象
	 * @param formatDate 格式化的日期字符串
	 * @param split 分隔符
	 * @return Date对象
	 * @throws IllegalArgumentException 日期格式错误
	 */
	public static Date conventDateStringToObject(String formatDate,String split) throws IllegalArgumentException{
		Calendar calendar = Calendar.getInstance();
		String[] para=formatDate.split(split);
		if (para.length == 3) {
			calendar.set(Integer.valueOf(para[0]), Integer.valueOf(para[1]), Integer.valueOf(para[2]));
			return new Date(calendar.getTimeInMillis());
		}
		else {
			throw new IllegalArgumentException("日期格式错误");
		}
	}
}
