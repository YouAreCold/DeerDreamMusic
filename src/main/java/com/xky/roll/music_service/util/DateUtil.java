package com.xky.roll.music_service.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 时间处理
 * DateUtil.java
 * 
 */
public class DateUtil {

	public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd"; // 默认日期格式

	public static Timestamp getNowDatetime() {
		Timestamp datetime = new Timestamp(System.currentTimeMillis());
		return datetime;
	}

	/**
	 * 验证日期是否合法 验证日期是否是2015-01-01这样的完整格式
	 * 验证正确的日期（闰年2月有29天，平年2月只有28天），（月大，月小） @Title: validateForDate @Description:
	 */
	public static boolean validateForDate(String date) {
		boolean flag;
		Pattern pattern = Pattern.compile("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])");

		// 验证日期是否是2015-01-01这样的完整格式,使用三元运算符，如果开始日期为true则再判断截止日期，如果开始日期为false则直接为false
		flag = pattern.matcher(date).matches();

		if (flag) {
			pattern = Pattern.compile(
					"^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");

			// 验证正确的日期（闰年2月有29天，平年2月只有28天）
			flag = pattern.matcher(date).matches();
		}
		return flag;
	}

	/**
	 * 取两个日期中间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daySub(Date date1, Date date2) {
		long second = (date1.getTime() - date2.getTime()) / 1000;
		return (int) (second / 24 / 60 / 60);
	}

	/**
	 * 取两个日期中间相差的分钟数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int minuteSub(Date date1, Date date2) {
		long second = (date1.getTime() - date2.getTime()) / 1000;
		return (int) (second / 60);
	}

	/**
	 * 日期加天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * 日期加年数
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYear(Date date, int years) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, years);
		return c.getTime();
	}

	/**
	 * 日期加小时数
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	/**
	 * 日期加分钟数
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}

	/**
	 * 日期加秒钟数
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSeconds(Date date, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, seconds);
		return c.getTime();
	}

	/**
	 * 以yyyy+separator+mm+separator+dd的格式显示年月日
	 * 
	 * @param time
	 * @param separator
	 *            分隔符，如果为“年月日” 则用"yyyy年MM月dd日"格式
	 * @return
	 */
	public static String formatDay(java.util.Date time, String separator) {
		if (separator == null) {
			separator = "-";
		}
		String formatStr = "";
		if (separator.equals("年月日")) {
			formatStr = "yyyy年MM月dd日";
		} else {
			formatStr = "yyyy" + separator + "MM" + separator + "dd";
		}
		return formatTime(time, formatStr);
	}

	/**
	 * 以MM月dd日格式显示日期
	 * 
	 * @param time
	 * @return
	 */
	public static String formatShortDay(java.util.Date time, String separator) {
		if (separator == null) {
			separator = "-";
		}
		String formatStr = "";
		if (separator.equals("月日")) {
			formatStr = "MM月dd日";
		} else {
			formatStr = "MM" + separator + "dd";
		}
		return formatTime(time, formatStr);
	}

	public static String formatDateForLate(java.util.Date time) {
		if (time == null) {
			return "";
		}
		int t = isSameDay(new java.util.Date(), time);
		if (t == 0)
			return "今天";
		else if (t == 1)
			return "昨天";
		else
			return formatTime(time, "yyyy-MM-dd");
	}

	/**
	 * 以yyyy-MM-dd HH:mm的格式转换日期
	 * 
	 * @param time
	 *            java.util.Date
	 * @return
	 */
	public static String formatMinute(java.util.Date time) {
		return formatTime(time, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 以yyyy-MM-dd HH:mm:ss的格式转换日期
	 * 
	 * @param time
	 * @return
	 */
	public static String formatSecond(java.util.Date time) {
		return formatTime(time, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将时间转换成指定格式的字符串
	 * 
	 * @param time
	 *            Date
	 * @param format
	 *            格式描述:例如:yyyy-MM-dd yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatTime(java.util.Date time, String format) {
		if (time != null) {
			java.text.SimpleDateFormat DATE_FORMAT = new java.text.SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
			return DATE_FORMAT.format(time);
		} else {
			return "";
		}

	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param time
	 *            Date
	 * @param format
	 *            格式描述:例如:yyyy-MM-dd
	 * @return
	 */
	public static String formatDate(java.util.Date date) {
		if (date != null) {
			java.text.SimpleDateFormat DATE_FORMAT = new java.text.SimpleDateFormat(DATE_FORMAT_DEFAULT,
					Locale.SIMPLIFIED_CHINESE);
			return DATE_FORMAT.format(date);
		} else {
			return "";
		}

	}

	/**
	 * 将秒计算出还有多少天、小时、分、秒,用于显示，例如：63s显示为：1分3秒
	 * 
	 * @param seconds
	 * @return
	 */
	public static String showTimes(int second) {
		StringBuffer r = new StringBuffer();
		int days = second / (3600 * 24);
		int hours = second % (3600 * 24) / 3600;
		int minutes = second % 3600 / 60;
		int s = second % 60;
		if (days > 0)
			r.append(days).append("天");
		if (hours > 0)
			r.append(hours).append("小时");
		if (minutes > 0)
			r.append(minutes).append("分");
		if (s > 0)
			r.append(s).append("秒");
		return r.toString();
	}

	public static String showTimes(Date date) {
		return showTimes((int) ((System.currentTimeMillis() - date.getTime()) / 1000));
	}

	/**
	 * 比较两个日期是否为同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return 0:是同一天 正数：date1>date2多少天 负数 date1<date2多少天
	 */
	public static int isSameDay(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		int r = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);
		r = c1.compareTo(c2);
		if (r != 0) {
			r = (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 24 / 3600 / 1000);
		}
		return r;
	}

	/**
	 * 截断日期到天
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp truncDate(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	/**
	 * 取当前日期
	 * 
	 * @return
	 */
	public static Timestamp getCurrentDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Timestamp time = new Timestamp(c.getTimeInMillis());
		return time;
	}

	/**
	 * 取当月的第一天
	 * 
	 * @return
	 */
	public static Timestamp getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Timestamp time = new Timestamp(c.getTimeInMillis());
		return time;
	}

	/**
	 * 从字符串中获取时间
	 * 
	 * @param s
	 * @param format
	 * @return
	 */
	public static Timestamp parseDate(String s) {
		return parseDate(s, "yyyy-MM-dd");
	}

	/**
	 * 从字符串中获取时间
	 * 
	 * @param s
	 * @param format
	 * @return
	 */
	public static Timestamp parseDate(String s, String format) {
		if (s == null || s.trim().length() == 0) {
			return null;
		}
		try {
			java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
			return new Timestamp(dateFormat.parse(s).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从字符串生成Date对象
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseStrToDate(String string, String format) {
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		Date date = null;
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		try {
			date = dateformat.parse(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算年龄
	 * 
	 * @param birthYear
	 * @return
	 */
	public static int calAge(String birthYear) {
		return calAge(birthYear, null);
	}

	/**
	 * 计算年龄
	 * 
	 * @param birthYear
	 * @return
	 */
	public static int calAge(Date birth, String cardNo) {
		if (birth == null) {
			if (StringUtil.isEmpty(cardNo)) {
				return 0;
			} else {
				IdcardInfoExtractor idcardInfo = new IdcardInfoExtractor(cardNo);
				return idcardInfo.getAge();
			}
		}
		Calendar cald = Calendar.getInstance();
		cald.setTime(new Date(birth.getTime()));

		String year = "" + cald.get(Calendar.YEAR);
		return calAge(year, null);
	}

	/**
	 * 计算年龄
	 * 
	 * @param birthYear
	 * @param currentDay
	 * @return
	 */
	public static int calAge(String birthYear, Date currentDay) {
		Calendar n = Calendar.getInstance();
		if (currentDay == null) {
			n.setTime(getNowDatetime());
		} else {
			n.setTime(currentDay);
		}
		
		String[] year = birthYear.split("-");
		
		return n.get(Calendar.YEAR) - convert2Int(year[0]);
	}

	/**
	 * 以yyyy年MM月dd日的格式转换日期
	 * 
	 * @param time
	 * @return
	 */
	public static String formatDateCN(java.util.Date time) {
		return formatTime(time, "yyyy年MM月dd日");
	}

	/**
	 * 以yyyy-MM-dd的格式转换日期
	 * 
	 * @param time
	 * @return
	 */
	public static String formatDateEN(java.util.Date time) {
		return formatTime(time, "yyyy-MM-dd");
	}

	public static Date getBeginDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static Date getEndDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * @Title: getTime @Description: 获取毫秒数，传值格式：yyyy-MM-dd HH:mm @param @param
	 *         date @param @return @param @throws Exception @return long @author
	 */
	public static long getTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long tl = 0L;
		try {
			tl = sdf.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tl;
	}

	public static long getTime(String time, String partent) {
		long result = 0L;
		if (!StringUtil.isEmpty(time)) {
			SimpleDateFormat sdf = null;
			try {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 获取当前日期
				time = sdf.format(new Date()) + " " + time;
				sdf = new SimpleDateFormat(partent);
				Date date = sdf.parse(time);
				result = date.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @Title getDayAfter
	 * @Description 获得date时间的后day天,精确到天
	 * @param date
	 * @param day
	 * @return
	 * @return String
	 */
	public static String getDayAfter(Date date, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		Date tempDate = now.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(tempDate);
	}

	/**
	 * @Title getDeadLine
	 * @Description 获取截止时间
	 * @param now
	 *            当前时间(获取年月日)
	 * @param baseTime
	 *            截止时间片段
	 * @return 返回yyyy-MM-dd HH:mm格式的时间字符串
	 * @return String
	 */
	public static String getDeadLine(Date now, String baseTime) {
		String result = null;
		if (!StringUtil.isEmpty(baseTime)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			result = format.format(now) + " " + baseTime.trim();
		}
		return result;
	}

	/**
	 * String转Date yyyy-MM-dd
	 * 
	 * @return
	 */
	public static Date getStringToDateTime(String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = null;
		try {
			currentDate = dateFormat.parse(time);
		} catch (Exception e) {
			currentDate = new Date();
			return currentDate;
		}
		return currentDate;
	}

	/**
	 * String转Date yyyy-MM-dd
	 * 
	 * @return
	 */
	public static Date getStringToDate(String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = null;
		try {
			currentDate = dateFormat.parse(time);
		} catch (ParseException e) {
			currentDate = new Date();
			return currentDate;
		}
		return currentDate;
	}

	/**
	 * 把String转换为数据，如果 String=null则返回0
	 */
	public static Integer convert2Int(String id) {
		return convert2Int(id, 0);
	}

	public static Integer convert2Int(String id, int defaultValue) {
		int tmpid = defaultValue;
		if (id != null) {
			try {
				tmpid = Integer.parseInt(id);
			} catch (Exception e) {

			}
		}
		return tmpid;
	}

}