package jc.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


/**
 * util of date and time
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class DateUtil {
	
	/**
	 * 时间戳
	 * @author zero.chen
	 *
	 */
	public interface TIMESTAMP {
		final long SECONDS_MINUTE = 60;
		final long SECONDS_HOUR = 3600;
		final long SECONDS_DAY = 86400;
		final int SECONDS_DAY_THIRTY = 25920000;

		final long MILLIS_SECOND = 1000;
		final long MILLIS_MINUTE = 60000;
		final long MILLIS_HOUR = 3600000;
		final long MILLIS_DAY = 86400000;
		
		final long MILLIS_SEVEN_DAY = 604800000;//7点的毫秒数
		
		final long MILLIS_TEN_YEAR = 315360000000l;//十年毫秒数

		final long MILLIS_SECOND_EIGHT_TIMEZONE = 691200000;//8个时区毫秒数
	}

	/**
	 * 日期格式常量
	 * @author zero.chen
	 *
	 */
	public interface TIMEFORMAT {
		final String HH_MM = "HH:mm";
		final String HH_MM_SS = "HH:mm:ss";
		final String YYYY_MM_DD = "yyyy-MM-dd";
		final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
		final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

		final String YYYY_MM = "yyyyMM";
		final String MMdd = "MM/dd"; 
		final String YYYY_SALASH_MM_SLASH_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
		final String YYYYMMDDHH_MM_SS = "yyyyMMdd HH:mm:ss";
		final String YYYYMMDD = "yyyyMMdd";
		final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
		
		final String YYYY_MM_DD_HH_MM_SS_ZERO = "yyyy-MM-dd 00:00:00";
		final String YYYY_MM_DD_HH_DD_SS_LASE_SECOND = "yyyy-MM-dd 23:59:59";
		
		final String M_DD = "M.dd";
		
		final String D = "d";
	}
	
	/**
	 * 获取默认实例
	 * @return
	 */
	public static Calendar getInstance() {
		return Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
	}
	/**
	 * 获取当前所属年
	 * @return
	 */
	public static int getYear() {
		return getInstance().get(Calendar.YEAR);
	}
	
	/**
	 * 获取当前所属月
	 * @return
	 */
	public static int getMonth() {
		return getInstance().get(Calendar.MONTH)+1;
	}
	/**
	 * 获取明天零点
	 * @return
	 * @throws Exception
	 */
	public static Date getZeroTimeOfTomorrow(){
		try {
			// 获取昨天这时候的日期时间
			Date nowOfYesterday = addSeconds(new Date(), +24 * 60 * 60);
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS_ZERO);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(nowOfYesterday);
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取明天零点
	 * @return
	 * @throws Exception
	 */
	public static int getZeroTimeOfTomorrowInt(){
		try {
			// 获取昨天这时候的日期时间
			Date nowOfYesterday = addSeconds(new Date(), +24 * 60 * 60);
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS_ZERO);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(nowOfYesterday);
			return (int)(sdf.parse(s).getTime()/1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取当天零点
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Date getZeroTimeOfToday() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS_ZERO);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(System.currentTimeMillis());
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取当天零点
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int getZeroTimeOfTodayInt() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS_ZERO);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(System.currentTimeMillis());
			return (int)(sdf.parse(s).getTime()/1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取昨天零点
	 * @return
	 * @throws Exception
	 */
	public static Date getZeroTimeOfYesterDay(){
		try {
			// 获取昨天这时候的日期时间
			Date nowOfYesterday = addSeconds(new Date(), -24 * 60 * 60);
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS_ZERO);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(nowOfYesterday);
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @remark 昨天凌晨
	 * @return
	 */
	public static int getZeroTimeOfYesterDayInt() {
		return (int)(getZeroTimeOfYesterDay().getTime()/1000l);
	}
	/**
	 * 获取指定日期的凌晨时间戳
	 * @param timestamp
	 * @return
	 */
	public static Date getZeroTimeOfDate(long timestamp) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS_ZERO);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(timestamp);
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取系统时间
	 * 
	 * @return long 时间戳
	 */
	public static Long getSystemTimeLong() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(System.currentTimeMillis());
			return sdf.parse(s).getTime();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取系统时间
	 * @return
	 */
	public static Integer getSystemTimeInt() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(System.currentTimeMillis());
			long timestamp = sdf.parse(s).getTime();
			int timestampInt = (int) (timestamp/1000);
			return timestampInt;
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取系统时间
	 * @return
	 */
	public static Date getSystemTimeDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT.YYYY_MM_DD_HH_MM_SS);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String s = sdf.format(System.currentTimeMillis());
			return sdf.parse(s);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getSystemTimeStr(String timeFormat) {
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return df.format(System.currentTimeMillis());
	}
	/**
	 * 日期转换，long to string 
	 * @param long 时间戳
	 * @return timeFormat 日期格式
	 */
	public static String getDateString(long timestamp,String timeFormat) {
		SimpleDateFormat sd = new SimpleDateFormat(timeFormat);
		sd.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return sd.format(timestamp);
	}
	/**
	 * 日期转换，date to string
	 * @param date
	 * @param timeFormat
	 * @return
	 */
	public static String getDateString(Date date,String timeFormat) {
		SimpleDateFormat sd = new SimpleDateFormat(timeFormat);
		sd.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return sd.format(date);
	}
	/**
	 * 日期转换，string to long 
	 * @param date 日期
	 * @param dateFormat 日期格式
	 * @return
	 */
	public static long getDateLong(String date, String dateFormat) {
		long timestamp = 0;
		try {
			timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}
	
	public static int getDateInt(String date, String dateFormat) {
		long timestamp = 0;
		try {
			timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int)(timestamp / 1000);
	}
	public static Date getDateDate(String dateStr,String timeFormat) {
		SimpleDateFormat sd = new SimpleDateFormat(timeFormat);
		try {
			Date date = sd.parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date getDateDate(long date) {
		return new Date(date);
	}
	/**
	 * 与系统时间比较
	 * @param date 比较的时间
	 * @return 0等于1大于-1小于
	 */
	public static int compareToCurrent(Date date) {
		Date currentDate = new Date();
		int n = date.compareTo(currentDate);
		return n;
	}
	/**
	 * 与系统时间比较
	 * @param time 比较的时间戳
	 * @return 0等于1大于-1小于
	 */
	public static int compareToCurrent(long time) {
		long currentTime = System.currentTimeMillis();
		if(time > currentTime) {
			return 1;
		}else if(time < currentTime) {
			return -1;
		}else {
			return 0;
		}
	}
	/**
	 * 比较两个日期大小
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static int compareDate(Date arg1, Date arg2) {
		return arg1.compareTo(arg2);
	}
	/**
	 * 获取指定日期是星期几
	 * @param date
	 * @return 星期X
	 */
	public static String getWeekString(Date date) {
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}
	/**
	 * 获取指定日期是星期几
	 * @param date
	 * @return 1,2,3,4,5,6,7
	 */
	public static int getWeekInt(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index <= 0) {
			week_index = 7;
		}
		return week_index;
	}
	
	public static String getMonday(String timeformat) {
		SimpleDateFormat df = new SimpleDateFormat(timeformat);//设置日期格式
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(System.currentTimeMillis());//当前时间

 		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
		return df.format(cld.getTime());
	}
	
	public static String getSunday(String timeformat) {
		SimpleDateFormat df = new SimpleDateFormat(timeformat);//设置日期格式
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(System.currentTimeMillis());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
		return df.format(cld.getTime());
	}
	
	public static String getNextSunday(String timeformat) {
		SimpleDateFormat df = new SimpleDateFormat(timeformat);//设置日期格式
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(System.currentTimeMillis());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
 		cld.add(Calendar.DATE, 7);
		return df.format(cld.getTime());
	}
	
	public static String getNextMonday(String timeformat) {
		SimpleDateFormat df = new SimpleDateFormat(timeformat);//设置日期格式
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(System.currentTimeMillis());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
 		cld.add(Calendar.DATE, 7);
 		return df.format(cld.getTime());
	}
	
	public static Date getMondayDate() {
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(System.currentTimeMillis());//当前时间

 		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
		return cld.getTime();
	}
	
	public static Date getSundayDate() {
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(System.currentTimeMillis());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
 		return cld.getTime();
	}
	
	public static Date getNextSundayDate() {
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(System.currentTimeMillis());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
 		cld.add(Calendar.DATE, 7);
 		return cld.getTime();
	}
	
	public static Date getNextMondayDate() {
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(System.currentTimeMillis());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
 		cld.add(Calendar.DATE, 7);
 		return cld.getTime();
	}
	
	public static String getZeroOfMonday(String timeformat) {
		SimpleDateFormat df = new SimpleDateFormat(timeformat);//设置日期格式
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(getZeroTimeOfToday().getTime());//当前时间

 		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
		return df.format(cld.getTime());
	}
	
	public static String getZeroOfSunday(String timeformat) {
		SimpleDateFormat df = new SimpleDateFormat(timeformat);//设置日期格式
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(getZeroTimeOfToday().getTime());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
		return df.format(cld.getTime());
	}
	
	public static String getZeroOfNextSunday(String timeformat) {
		SimpleDateFormat df = new SimpleDateFormat(timeformat);//设置日期格式
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(getZeroTimeOfToday().getTime());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
 		cld.add(Calendar.DATE, 7);
		return df.format(cld.getTime());
	}
	
	public static String getZeroOfNextMonday(String timeformat) {
		SimpleDateFormat df = new SimpleDateFormat(timeformat);//设置日期格式
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(getZeroTimeOfToday().getTime());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
 		cld.add(Calendar.DATE, 7);
 		return df.format(cld.getTime());
	}
	
	public static Date getZeroOfMondayDate() {
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(getZeroTimeOfToday().getTime());//当前时间

 		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
		return cld.getTime();
	}
	
	public static Date getZeroOfSundayDate() {
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(getZeroTimeOfToday().getTime());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
 		return cld.getTime();
	}
	
	public static Date getZeroOfNextSundayDate() {
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(getZeroTimeOfToday().getTime());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
 		cld.add(Calendar.DATE, 7);
 		return cld.getTime();
	}
	
	public static Date getZeroOfNextMondayDate() {
		Calendar cld = Calendar.getInstance(Locale.CHINA);
		cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
		cld.setTimeInMillis(getZeroTimeOfToday().getTime());//当前时间
 		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
 		cld.add(Calendar.DATE, 7);
 		return cld.getTime();
	}
	/**
	 * 获取日期所在月第一天，按格式输出
	 * @param date
	 * @param timeFormat
	 * @return
	 */
	public static String getFirstDayInMonth(Date date,String timeFormat) {
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		return str.toString();

	}
	/**
	 * 获取日期所在月最后一天，按格式输出
	 * @param date
	 * @param timeFormat
	 * @return
	 */
	public static String getLastDayInMonth(Date date,String timeFormat) {
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
		return str.toString();

	}
	/**
	 * 增加n天
	 * @param datetime 日期
	 * @param days 天数
	 * @param timeFormat 日期格式
	 * @return
	 */
	public static String addDays(String datetime, int days, String timeFormat) {
		if (null==timeFormat||"".equals(timeFormat)) {
			timeFormat = TIMEFORMAT.YYYY_MM_DD_HH_MM_SS;
		}
		DateFormat df = new SimpleDateFormat(timeFormat);
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(datetime));
			c.add(Calendar.DATE, days);
			Date d = c.getTime();
			return df.format(d);
		} catch (ParseException e) {
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 增加n小时
	 * @param datetime 日期
	 * @param hours 增加的小时数
	 * @param timeFormat 日期格式
	 * @return
	 */
	public static String addHours(String datetime, int hours,String timeFormat) {
		DateFormat df = new SimpleDateFormat(timeFormat);
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(datetime));
			c.add(Calendar.HOUR, hours);
			Date d = c.getTime();
			return df.format(d);
		} catch (ParseException e) {
		} catch (Exception e) {
		}
		return null;
	}
	public static Date addHours(Date date,int hours) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, hours);
		return c.getTime();
	}
	/**
	 * 增加n分钟，按指定日期输出
	 * @param dateTime
	 * @param minutes
	 * @param timeFormat
	 * @return
	 */
	public static String addMinutes(String dateTime,int minutes,String timeFormat) {
		DateFormat df = new SimpleDateFormat(timeFormat);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(df.parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.MINUTE, minutes);
		return df.format(c.getTime());
	}
	/**
	 * 增加n秒
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addSeconds(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, amount);
		return c.getTime();
	}
	/**
	 * 增加n毫秒
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMilliseconds(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MILLISECOND, amount);
		return c.getTime();
	}

}