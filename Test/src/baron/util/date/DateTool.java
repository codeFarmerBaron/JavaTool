package baron.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 处理Date日期、LocalDateTime日期相关的工具类：
 * 1，Date、LocalDateTime两者相互转换；
 * 2，以时间戳、日期字符串、年月日int序列为数据源，新建Date日期类型；
 * 3，Date日期类型转换成时间戳、日期字符串；
 * 4，Date日期加减年、月、日、时、分、秒；
 * 5，以时间戳、日期字符串为数据源，新建LocalDateTime日期类型，年月日序列可直接调用LocalDateTime.of(..)；
 * 6，LocalDateTime日期类型转换成时间戳、日期字符串；
 * 7，LocalDateTime日期加减年、月、日等可直接使用对象调用plus/minus..(..)方法。
 * 8，获取
 * 注意：
 * 1，LocalDateTime为时区敏感，此工具类皆以“+8”时区为准，未采用系统默认时区；
 * 2，当LocalDateTime、Date以“+8”时区为准和时间戳转换时，0毫秒时间戳为1970-01-01 08:00:00
 * 3，日期字符串转成日期类时注意格式字母的大小写
 * @author Baron
 * @version 0.0.1
 *
 */
public final class DateTool {

	/**
	 * 日期格式 ，只有日期，英文短横杠连接
	 */
	public static final String FORMAT_DATE="yyyy-MM-dd";
	/**
	 * 日期格式 ，只有日期，中文年月日连接
	 */
	public static final String FORMAT_DATE_CN="yyyy年MM月dd日";
	/**
	 * 日期格式 ，日期和时间（24小时制），日期英文短横杠、时间英文冒号连接
	 */
	public static final String FORMAT_DATETIME="yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式 ，日期和时间（24小时制），中文年月日时分秒连接
	 */
	public static final String FORMAT_DATETIME_CN="yyyy年MM月dd日 HH时mm分ss秒";
	/**
	 * 日期格式 ，日期和时间（24小时制），年月日时分秒数字堆叠
	 */
	public static final String FORMAT_DATETIME_STR="yyyyMMddHHmmss";
	/**
	 * 日期格式 ，只有日期，年月日数字堆叠
	 */
	public static final String FORMAT_DATE_STR="yyyyMMdd";
	/**
	 * 日期格式 ，只有时间（24小时制），时分秒数字堆叠
	 */
	public static final String FORMAT_TIME_STR="HHmmss";

	/**
	 * 隐藏默认构造器
	 */
	private DateTool(){}

	/**
	 * Date类型转成LocalDateTime类型（+8时区）
	 * @param date 日期
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		if (date==null) {
			return null;
		}
		return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.of("+8"));
	}


	/**
	 * 毫秒秒级时间戳转成LocalDateTime（注意“+8”时区0时间戳为1970-01-01 08:00:00）
	 * @param timeStampMilli 毫秒级时间戳
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(Long timeStampMilli){
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStampMilli), ZoneOffset.of("+8"));
	}

	/**
	 * 毫秒秒级时间戳转成LocalDateTime（注意“+8”时区0时间戳为1970-01-01 08:00:00）
	 * @param timeStampMilli 毫秒级时间戳
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(String timeStampMilli){
		return toLocalDateTime(Long.parseLong(timeStampMilli));
	}

	/**
	 * String格式日期转成LocalDateTime
	 * @param localDateTimeStr 如“1970-01-01”类的日期字符串
	 * @param format 格式字符串：y年， M月，d日，H时(不可小写)，m分，s秒。
	 * 注意大小写，且保证dateString和format格式一致。
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(String localDateTimeStr,String format) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(localDateTimeStr,df);
	}


	/**
	 * LocalDateTime类型转成Date类型（+8时区）
	 * @param localDateTime 日期
	 * @return Date
	 */
	public static Date toDate(LocalDateTime localDateTime){
		if (localDateTime==null) {
			return null;
		}
		return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
	}

	/**
	 * 毫秒级时间戳转成date日期
	 * @param timeStampSecond 毫秒级时间戳
	 * @return Date
	 */
	public static Date toDate(Long timeStampMilli){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(timeStampMilli);
		return calendar.getTime();
	}

	/**
	 * 毫秒级时间戳转成date日期
	 * @param timeStampMilli 毫秒级时间戳
	 * @return Date
	 */
	public static Date toDate(String timeStampMilli){
		return toDate(Long.parseLong(timeStampMilli));
	}

	/**
	 * String格式日期转成Date
	 * @param dateStr 如“1970-01-01”类的日期字符串
	 * @param format 格式字符串：y年， M月，d日，h/H时，m分，s秒。注意大小写，且保证dateString和format格式一致。
	 * @return Date日期
	 * @throws ParseException 日期转化异常
	 */
	public static Date toDate(String dateStr,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}

	/**
	 * date日期转成秒级时间戳
	 * @param date 日期
	 * @return 秒级时间戳
	 */
	public static Long toTimeStampSecond(Date date){
		return date.getTime();
	}

	/**
	 * date日期转成毫秒级时间戳
	 * @param date 日期
	 * @return 毫秒级时间戳
	 */
	public static Long toTimeStampMilli(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}


	/**
	 * LocalDateTime日期转成秒级时间戳（+8时区）
	 * @param localDateTime 日期
	 * @return 秒级时间戳
	 */
	public static Long toTimeStampSecond(LocalDateTime localDateTime){
		return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
	}

	/**
	 * LocalDateTime日期转成毫秒级时间戳（+8时区）
	 * @param localDateTime 日期
	 * @return 毫秒级时间戳
	 */
	public static Long toTimeStampMilli(LocalDateTime localDateTime){
		return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
	}

	/**
	 * Date日期转成String格式
	 * @param date 日期
	 * @param format 格式字符串：y年， M月，d日，H时(大写24制,小写12制)，m分，s秒。注意大小写。
	 * @return 日期字符串
	 */
	public static String toDateStr(Date date,String format) {
		if (date==null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * LocalDateTime日期转成String格式
	 * @param localDateTime 日期
	 * @param format 格式字符串：y年， M月，d日，H时(大写24制,小写12制)，m分，s秒。注意大小写。
	 * @return 日期字符串
	 */
	public static String toDateStr(LocalDateTime localDateTime,String format) {
		if (localDateTime==null) {
			return null;
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
		return df.format(localDateTime);

	}

	/**
	 * 把毫秒级时间戳转成时间字符串，注意0毫秒时间戳对应的时间为1970-01-01 08:00:00 。
	 * @param timeStampMilli 毫秒级时间戳
	 * @param format 格式字符串：y年， M月，d日，H时(大写24制,小写12制)，m分，s秒。注意大小写。
	 * @return 日期字符串
	 */
	public static String toDateStr(Long timeStampMilli,String format) {
		return toDateStr(toLocalDateTime(timeStampMilli), format);
	}

	/**
	 * 把formatFrom格式的日期字符串转成formatTo格式
	 * @param dateStr 日期字符串
	 * @param formatFrom 日期源格式，需和dateString格式相同。字符串：y年， M月，d日，H时（不可小写），m分，s秒。
	 * @param formatTo 想要转成的日期格式
	 * @return 日期字符串
	 */
	public static String tranDateStr(String dateStr,String formatFrom,String formatTo){
		return toDateStr(toLocalDateTime(dateStr, formatFrom), formatTo);
	}

	/**
	 * 把毫秒级时间戳转成formatTo格式日期字符串
	 * @param timeStampMilli 毫秒级时间戳
	 * @param formatTo 想要转成的日期格式
	 * @return 日期字符串
	 */
	public static String tranDateStr(String timeStampMilli,String formatTo){
		LocalDateTime localDateTime=toLocalDateTime(timeStampMilli);
		return toDateStr(localDateTime, formatTo);
	}

	/**
	 * 把毫秒级时间戳转成formatTo格式日期字符串
	 * @param timeStampMilli 毫秒级时间戳
	 * @param formatTo 想要转成的日期格式
	 * @return 日期字符串
	 */
	public static String tranDateStr(Long timeStampMilli,String formatTo){
		LocalDateTime localDateTime=toLocalDateTime(timeStampMilli);
		return toDateStr(localDateTime, formatTo);
	}

	/**
	 * Date日期加上（负数减去）一段时间
	 * @param date 原始日期
	 * @param calenderType Calendar类型，如Calendar.YEAR为年
	 * @param time 变化的时间长度，如Calendar.YEAR、-2：两年前
	 * @return 新的Date日期
	 */
	public static Date add(Date date,int calenderType,int time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calenderType, time);
		return calendar.getTime();
	} 

	/**
	 * Date日期加上（负数减去）N年
	 * @param date 原始日期
	 * @param year 正加负减
	 * @return 新的Date日期
	 */
	public static Date addYear(Date date,int year){
		return add(date,Calendar.YEAR, year);
	} 

	/**
	 * Date日期加上（负数减去）N月
	 * @param date 原始日期
	 * @param month 正加负减
	 * @return 新的Date日期
	 */
	public static Date addMonth(Date date,int month){
		return add(date,Calendar.MONTH, month);
	} 

	/**
	 * Date日期加上（负数减去）N天
	 * @param date 原始日期
	 * @param day 正加负减
	 * @return 新的Date日期
	 */
	public static Date addDay(Date date,int day){
		return add(date,Calendar.DAY_OF_MONTH, day);
	} 

	/**
	 * Date日期加上（负数减去）N小时
	 * @param hour 原始日期
	 * @param year 正加负减
	 * @return 新的Date日期
	 */
	public static Date addHour(Date date,int hour){
		return add(date,Calendar.HOUR, hour);
	} 

	/**
	 * Date日期加上（负数减去）N分钟
	 * @param date 原始日期
	 * @param minute 正加负减
	 * @return 新的Date日期
	 */
	public static Date addMinute(Date date,int minute){
		return add(date,Calendar.MINUTE, minute);
	} 

	/**
	 * Date日期加上（负数减去）N秒
	 * @param date 原始日期
	 * @param second 正加负减
	 * @return 新的Date日期
	 */
	public static Date addSecond(Date date,int second){
		return add(date,Calendar.SECOND, second);
	} 

	/**
	 * Date日期加上（负数减去）N毫秒
	 * @param date 原始日期
	 * @param millisecond 正加负减
	 * @return 新的Date日期
	 */
	public static Date addMillisecond(Date date,int millisecond){
		return add(date,Calendar.MILLISECOND, millisecond);
	} 

	/**
	 * 根据Date获取当天0点时间
	 * @param date 日期
	 * @return 0点日期
	 */
	public static Date getStartTimeOfDay(Date date){
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据年月日获取当天0点时间
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 0点日期
	 */
	public static Date getStartTimeOfDay(int year,int month,int day){
		Calendar calendar = Calendar.getInstance();	
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DAY_OF_MONTH,day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据Date获取当天结束时间
	 * @param date 日期
	 * @return 23:59:59.999点日期
	 */
	public static Date getEndTimeOfDay(Date date){
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 根据年月日获取当天结束时间
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 当天23:59:59.999点日期
	 */
	public static Date getEndTimeOfDay(int year,int month,int day){
		Calendar calendar = Calendar.getInstance();	
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DAY_OF_MONTH,day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 根据输入（数组）数据生成Date日期
	 * @param times 根据输入长度自动设置为年、月、日、时、分、秒、毫秒（7项），长度小于7时，日期补“1”，时间补“0”。
	 * @return Date日期
	 */
	public static Date getDate(int...times){
		if (times==null||times.length==0) {
			return new Date();
		}
		int[] timesNew=new int[7];
		for (int i = 0; i < timesNew.length; i++) {
			if (i <times.length) {
				timesNew[i]=times[i];
			}else{
				if (i<3) {
					timesNew[i]=1;
				}else{
					timesNew[i]=0;
				}
			}
		}
		Calendar calendar = Calendar.getInstance();	
		calendar.set(Calendar.YEAR,timesNew[0]);
		calendar.set(Calendar.MONTH,timesNew[1]-1);
		calendar.set(Calendar.DAY_OF_MONTH,timesNew[2]);
		calendar.set(Calendar.HOUR_OF_DAY, timesNew[3]);
		calendar.set(Calendar.MINUTE, timesNew[4]);
		calendar.set(Calendar.SECOND, timesNew[5]);
		calendar.set(Calendar.MILLISECOND, timesNew[6]);
		return calendar.getTime();
	}
}
