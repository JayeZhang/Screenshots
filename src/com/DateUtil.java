package com;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 类名称：	DateUtil  
 * 类描述：	时间操作工具类
 * 创建人：  jie.zhang
 * 创建时间：	2017-8-9 下午5:21:51
 * 修改人：
 * 修改时间：
 * @version 1.0.0
 */
public class DateUtil extends PropertyEditorSupport {
	// 各种时间格式
	public static final SimpleDateFormat date_sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 各种时间格式
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	// 各种时间格式
	public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat time_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat("HH:mm");
	public static final  SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 以毫秒表示的时间
	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
	private static final long HOUR_IN_MILLIS = 3600 * 1000;
	private static final long MINUTE_IN_MILLIS = 60 * 1000;
	private static final long SECOND_IN_MILLIS = 1000;

	/**
	 * 
	 * getSDFormat( 指定模式的时间格式)  
	 * @param pattern				指定的格式
	 * @return  SimpleDateFormat	时间格式
	 * @since  1.0.0
	 */
	private static SimpleDateFormat getSDFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	/**
	 * 
	 * getDate(获取系统当前日期)  
	 * @return  Date       系统当前时间
	 * @since  1.0.0
	 */
	public static Date getDate() {
		return new Date();
	}
	/**
	 * 
	 * getCurrentTime(获取"yyyy-MM-dd HH:mm:ss"格式化后的系统当前时间)  
	 * @return  String   系统当前时间
	 * @since  1.0.0
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return s.format(date);
	}
	
	/**
	 * 
	 * date2Str(把指定日期转换为指定格式的字符串)  
	 * @param date          日期
	 * @param date_sdf      时间格式
	 * @return  String      格式化后的时间字符串
	 * @since  1.0.0
	 */
	public static String dateToString(Date date, SimpleDateFormat date_sdf) {
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}
	
	/**
	 * 
	 * getTimestamp(获取系统当前的时间戳)  
	 * @return  Timestamp      系统当前的时间戳
	 * @since  1.0.0
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 
	 * getMillis(获取指定日历的毫秒数) 
	 * 后面计算时间差会用到 
	 * @param cal       指定日历
	 * @return  long    指定日历的毫秒数   
	 * @since  1.0.0
	 */
	public static long getMillis(Calendar cal) {
		return cal.getTime().getTime();
	}

	/**
	 * 
	 * formatDate(默认方式为date_sdf的系统当前日期，具体格式：年-月-日)  
	 * @return  String   默认日期按“年-月-日“格式显示的时间字符串
	 * @since  1.0.0
	 */
	public static String formatDate() {
		return date_sdf.format(getDate().getTime());
	}

	/**
	 * 
	 * getDateString(按指定的时间格式获取时间字符串)  
	 * @param formatstr      时间格式,例如:date_sdf_wz   
	 * @return  String       格式化后的时间字符串
	 * @since  1.0.0
	 */
	public static String getDateString(SimpleDateFormat formatstr) {
		return formatstr.format(getDate().getTime());
	}
	
	/**
	 * 
	 * formatDate(默认日期按指定格式显示)  
	 * @param pattern     指定的格式      例如:"yyyy/MM/dd"
	 * @return  String    默认日期按指定格式显示   
	 * @since  1.0.0
	 */
	public static String formatDate(String pattern) {
		return getSDFormat(pattern).format(getDate().getTime());
	}

	/**
	 * 
	 * dateDiff(计算两个时间之间的差值，根据标志的不同而不同)  
	 * @param flag       计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param calSrc     减数
	 * @param calDes     被减数
	 * @return  int      两个日期之间的差值   
	 * @since  1.0.0
	 */
	@SuppressWarnings("static-access")
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return (calSrc.get(calSrc.YEAR) - calDes.get(calDes.YEAR));
		}

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h') {
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm') {
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's') {
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}
	
	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src 		将要转换的原始字符窜
	 * @param pattern 	转换的匹配格式
	 * @return 			如果转换成功则返回转换后的日期
	 * @throws ParseException
	 */
	public static Date parseDate(String src, String pattern)
			throws ParseException {
		return getSDFormat(pattern).parse(src);

	}
	
}