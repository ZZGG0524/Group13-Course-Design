package com.zhuang.group13projectdesign.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {
	public static final String CNUMBER_PATTERN = "^[0-9]*$";// 判断数字的正则表达式
	
	

	private static SimpleDateFormat bartDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static int getYear(Date date) {
		try {
			String form_date = bartDateFormat.format(date);
			return Integer.parseInt(form_date.substring(0, 4));
		} catch (Exception ex) {
			return Integer.parseInt(getCurrentTime().substring(0, 4));
		}
	}

	public static int getMonth(Date date) {
		try {
			String form_date = bartDateFormat.format(date);
			return Integer.parseInt(form_date.substring(5, form_date
					.lastIndexOf("-")));
		} catch (Exception ex) {
			String currenttimel = getCurrentTime();
			return Integer.parseInt(currenttimel.substring(5, currenttimel
					.lastIndexOf("-")));
		}
	}

	public static int getDay(Date date) {
		try {
			String form_date = bartDateFormat.format(date);
			return Integer.parseInt(form_date.substring(form_date
					.lastIndexOf("-") + 1, form_date.length()));
		} catch (Exception ex) {
			String currenttimel = getCurrentTime();
			return Integer.parseInt(currenttimel.substring(5, currenttimel
					.lastIndexOf("-")));
		}

	}

	public static String[] getMonth_days(Date date) {
		String[] month_day = { "31", "28", "31", "30", "31", "30", "31", "31",
				"30", "31", "30", "31" };
		int year = getYear(date);
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
			month_day[1] = "29";
		return month_day;
	}

	public static int getCount(String str, String sign) {
		if (str == null) {
			return 0;
		}
		StringTokenizer s = new StringTokenizer(str, sign);
		return s.countTokens();
	}

	public static String[] getArray(String str, String sign) {
		int count = getCount(str, sign);
		int j = 0;
		String[] arr = new String[count];
		for (int i = 0; i < count; i++) {
			if (str.indexOf(sign) != -1) {
				j = str.indexOf(sign);
				arr[i] = str.substring(0, j);
				str = str.substring(j + 1);
			} else {
				arr[i] = str;
			}
			// System.out.println(arr[i]);
		}
		return arr;
	}

	public static String getCurrentTime() {
		java.sql.Timestamp temp = new java.sql.Timestamp(System
				.currentTimeMillis());
		return (temp.toString()).substring(0, 19);
	}

	public static Date toDateType(String s) {
		DateFormat df = DateFormat.getDateInstance();
		df.setLenient(false);
		s = s.replace('/', '-');
		s = s.replace('.', '.');
		try {
			return df.parse(s);
		} catch (Exception e) {
			return null;
		}
	}

	public static String unicodeToGB(String strIn) {
		byte[] b;
		String strOut = null;
		if (strIn == null || (strIn.trim()).equals("")) {
			return strIn;
		}
		try {
			b = strIn.getBytes("GBK");
			strOut = new String(b, "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
		}
		return strOut;
	}

	public static String GBToUnicode(String strIn) {
		String strOut = null;
		if (strIn == null || (strIn.trim().equals(""))) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("ISO8859_1");
			strOut = new String(b, "GBK");
		} catch (Exception e) {
		}
		return strOut;
	}

	public static String UTFToUnicode(String strIn) {
		String strOut = null;
		if (strIn == null || (strIn.trim().equals(""))) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("ISO8859_1");
			strOut = new String(b, "UTF-8");
		} catch (Exception e) {
		}
		return strOut;
	}

	public static String UTFToGBK(String strIn) {
		String strOut = null;
		if (strIn == null || (strIn.trim().equals(""))) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("GBK");
			strOut = new String(b, "UTF-8");
		} catch (Exception e) {
		}
		return strOut;
	}

	public static String buildURL(Map<String,String[]> parameters) {
		StringBuffer url = new StringBuffer();
		if (parameters != null && parameters.size() > 0) {
			url.append("?");
			for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = iter.next();
				String[] values = parameters.get(key);
				for (int i = 0; i < values.length; i++) {
					url.append(key).append("=").append(values[i]).append("&");
				}
			}
		}
		return url.toString();
	}

	/**
	 * 获得当前时间(根据格式字符串)
	 * 
	 * @param format
	 *            String 格式字符串
	 * @return String
	 */
	public static String getDateByFormat(String format) {
	
		Calendar date = Calendar.getInstance();
		//System.out.print("date.getTime()==="+date.getTime());
		SimpleDateFormat sim = new SimpleDateFormat(format);
		String str = sim.format(date.getTime());
		return str;

	}

	/**
	 * 获得当前时间
	 *
	 * @return String
	 */
	public static String getDate() {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String str = sim.format(date.getTime());
		return str;

	}


	/**
	 * 获得当前时间
	 *
	 * @return Date
	 */
	public static Date getNowDate() {
		/*Calendar date = Calendar.getInstance();
		return date.getTime();*/
		return getDate(getDate());
	}

	/**
	 * 字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 *            String
	 * @return Date
	 */
	public static Date getDate(String date) {

		try {
			SimpleDateFormat localTime = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date1 = localTime.parse(date);
			return date1;
		}

		catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * 字符串转换为时间2 yyyy-MM-dd
	 *
	 * @param date
	 *            String
	 * @return Date
	 */
	public static Date getDate2(String date) {

		try {
			SimpleDateFormat localTime = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = localTime.parse(date);
			return date1;
		}

		catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * 字符串转换为时间3 yyyy-MM
	 *
	 * @param date
	 *            String
	 * @return Date
	 */
	public static Date getDate3(String date) {

		try {
			SimpleDateFormat localTime = new SimpleDateFormat("yyyy-MM");
			Date date1 = localTime.parse(date);
			return date1;
		}

		catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * 字符串转换为时间3 "HH:mm"
	 *
	 * @param date
	 *            String
	 * @return Date
	 */
	public static Date getDateByHHMM(String date) {

		try {
			SimpleDateFormat localTime = new SimpleDateFormat("HH:mm");
			Date date1 = localTime.parse(date);
			return date1;
		}

		catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * 字符串转换为时间3 yyyy
	 *
	 * @param date
	 *            String
	 * @return Date
	 */
	public static Date getDate4(String date) {

		try {
			SimpleDateFormat localTime = new SimpleDateFormat("yyyy");
			Date date1 = localTime.parse(date);
			return date1;
		}

		catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * 取得秒数
	 */
	public static Long getDateDiff_Second(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) / 1000;
	}

	/**
	 * 取得分钟
	 *
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return Long
	 */
	public static Long getDateDiff_Minute(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) / (1000 * 60);
	}

	/**
	 * 取得小时
	 *
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return Long
	 */
	public static Long getDateDiff_Hour(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) / (1000 * 3600);
	}

	public static Long getDateDiff_Hour(String d1, String d2) {
		return (getDate(d2).getTime() - getDate(d1).getTime()) / (1000 * 3600);
	}

	/**
	 * 取得天数
	 *
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return Long
	 */
	public static Long getDateDiff_Day(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) / (1000 * 3600 * 24);
	}

	public static Long getDateDiff_Day(String d1, String d2) {
		return (getDate(d2).getTime() - getDate(d1).getTime())
				/ (1000 * 3600 * 24);
	}

	/**
	 * 取得星期间隔
	 *
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return Long
	 */
	public static Long getDateDiff_Week(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) / (1000 * 3600 * 24 * 7);
	}

	/**
	 * 取得当前时间的 间隔多少小时之后的时间
	 *
	 * @param hour
	 *            int
	 * @return String
	 */
	public static String getDateAdd(int hour) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sd = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		calendar.set(Calendar.HOUR, hour + calendar.get(Calendar.HOUR));
		String enddate = sd.format(calendar.getTime());
		return enddate;

	}

	/**
	 * 取得当前时间的 间隔多少小时之后的时间
	 *
	 * @param hour
	 *            int
	 * @return String
	 */
	public static String getDateAdd(String starttime, int hour) {

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sd = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		calendar.setTime(getDate(starttime));
		calendar.set(Calendar.HOUR, hour + calendar.get(Calendar.HOUR));
		String date = sd.format(calendar.getTime());
		return date;

	}
	public static Date getDateAdd(Date starttime, int hour) {

		Calendar calendar = Calendar.getInstance();

//		java.text.SimpleDateFormat sd = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
		calendar.setTime(starttime);
		calendar.set(Calendar.HOUR, hour + calendar.get(Calendar.HOUR));
		return calendar.getTime();

	}

	/**
	 * 取得当前时间的 间隔多少小时之后的时间
	 *
	 * @param hour
	 *            int
	 * @return String
	 */
	public static String getDateAdd2(String starttime, int hour) {

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(getDate2(starttime));
		calendar.set(Calendar.HOUR, hour + calendar.get(Calendar.HOUR));
		String date = sd.format(calendar.getTime());
		return date;

	}

	/**
	 * 取得当前时间的 间隔多少秒之后的时间
	 *
	 * @param hour
	 *            int
	 * @return String
	 */
	public static String getDateAddSecond(String starttime, long second) {

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sd = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		calendar.setTime(getDate2(starttime));
		calendar.set(Calendar.SECOND, (int) (second + calendar
				.get(Calendar.SECOND)));
		String date = sd.format(calendar.getTime());
		return date;

	}

	/**
	 * 获得时间格式的文件名称
	 *
	 * @return String
	 */
	public static String getFileName() {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sim = new SimpleDateFormat(
				"yyyyMMdd_hhmmss");
		String str = sim.format(date.getTime());
		return str;
	}

	// 获得月日
	public static String get_MM_DD(String s) {
		SimpleDateFormat sim = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date;
		String str = "";
		try {
			date = sim.parse(s);
			sim = new SimpleDateFormat("[MM-dd]");
			str = sim.format(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
		}
		return str;
	}

	// 获得年月日

	public static String get_YYYY_MM_DD(String s) {
		SimpleDateFormat sim = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date;
		String str = "";
		try {
			date = sim.parse(s);
			sim = new SimpleDateFormat("yyyy-MM-dd");
			str = sim.format(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {

		}
		return str;
	}

	// 获得年月

	public static String get_YYYY_MM(String s) {
		SimpleDateFormat sim = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date;
		String str = "";
		try {
			date = sim.parse(s);
			sim = new SimpleDateFormat("yyyy-MM");
			str = sim.format(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {

		}
		return str;
	}

	// 获得年

	public static String get_YYYY(String s) {
		SimpleDateFormat sim = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date;
		String str = "";
		try {
			date = sim.parse(s);
			sim = new SimpleDateFormat("yyyy");
			str = sim.format(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {

		}
		return str;
	}

	/**
	 * 检查IP是否合法
	 *
	 * @param value
	 * @return
	 */

	public static boolean ipValid(String s) {
		String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
		String regex1 = "1\\d{2}";
		String regex2 = "[1-9]\\d";
		String regex3 = "\\d";
		String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|("
				+ regex3 + ")";
		regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex
				+ ")";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss'(24小时制)<br>
	 * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'<br>
	 *
	 * @param time
	 *            Date 日期<br>
	 * @return String 字符串<br>
	 */
	public static String dateToString(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = formatter.format(time);
		return ctime;
	}
	/**
	 * yyyyMMddHHmmss
	 * @param time
	 * @return yyyyMMddHHmmss
	 */
	public static String dateToyyyyMMddHHmmss(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String ctime = formatter.format(time);
		return ctime;
	}
	/**
	 * yyyy-MM-dd
	 * @param time
	 * @return yyyy-MM-dd
	 */
	public static String dateToString2(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ctime = formatter.format(time);
		return ctime;
	}
	/**
	 * yyyyMMdd
	 * @param time
	 * @return yyyyMMdd
	 */
	public static String yymmdddateToString(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyyMMdd");
		String ctime = formatter.format(time);
		return ctime;
	}

	/**
	 * hhmmss
	 * @param time
	 * @return hhmmss
	 */
	public static String hhmmssdateToString(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("hhmmss");
		String ctime = formatter.format(time);
		return ctime;
	}

	/**
	 * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss a'(12小时制)<br>
	 * 如Sat May 11 17:23:22 CST 2002 to '2002-05-11 05:23:22 下午'<br>
	 *
	 * @param time
	 *            Date 日期<br>
	 * @param x
	 *            int 任意整数如：1<br>
	 * @return String 字符串<br>
	 */
	public static String dateToString(Date time, int x) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
		String ctime = formatter.format(time);

		return ctime;
	}

	/**
	 *  判断字符串是否为Null或""
	 * @param str
	 * @return
	 */
	public static boolean isNotNullAndEmpty(String str) {
		if (null != str && !str.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断列表是否为Null或大小为0
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotNullAndZero(List list) {
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 *  判断字符串是否为Null，trim之后判断是否为空
	 * @param str
	 * @return
	 */
    public static boolean isNotNullAndEmptyByTrim(String str) {
        if (null != str && !str.trim().equals("")) {
            return true;
        }
        return false;
    }

	/**
	 *  删除ArrayList中重复元素
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	/**
	 *  判断当前天是否为月末时间即加一天是否为下个月
	 * @return
	 */
	public static boolean isTheLastMonthDAY() {
		String curtime = getDate();
		String nexttime = getDateAdd(curtime, 24);
		if (!get_YYYY_MM(curtime).equals(get_YYYY_MM(nexttime))) {
			return true;
		}
		return false;
	}

	/**
	 * 编码转换 ISO-8859-1  >> gb2312
	 *
	 * @param str
	 * @return gb2312
	 */
	public static String getReadStr(String str) {
		try {
			String temp_p = str;
			byte[] temp_t = temp_p.getBytes("ISO-8859-1");
			String unicode = new String(temp_t, "gb2312");
			return unicode;
		} catch (Exception e) {
			return "null";
		}
	}

	/**
	 * 编码转换ISO-8859-1 >> UTF-8
	 * @param str
	 * @return UTF-8
	 */
	public static String isoTOUTF(String str) {
		try {
			String temp_p = str;
			byte[] temp_t = temp_p.getBytes("ISO-8859-1");
			String unicode = new String(temp_t, "UTF-8");
			return unicode;
		} catch (Exception e) {
			return "null";
		}
	}

	/**
	 * 编码转换 UTF-8 >> ISO-8859-1
	 * @param str
	 * @return ISO-8859-1
	 */
	public static String utfTOISO(String str) {
		try {
			String temp_p = str;
			byte[] temp_t = temp_p.getBytes("UTF-8");
			String unicode = new String(temp_t, "ISO-8859-1");
			return unicode;
		} catch (Exception e) {
			return "null";
		}
	}

	public static String toHtml(String str) {
		if (str == null) {
			return "";
		} else {
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("'", "''");
			str = str.replaceAll(" ", "&nbsp;");
			str = str.replaceAll("\n", "<br>");
		}
		return str;
	}

	public static String toText(String str) {
		if (str == null) {
			return "";
		} else {
			str = str.replaceAll("&lt;", "<");
			str = str.replaceAll("&gt;", ">");
			str = str.replaceAll("''", "'");
			str = str.replaceAll("&nbsp;", " ");
			str = str.replaceAll("<br>", "\n");
		}
		return str;
	}

	/**
	 * 去掉字符串的html代码
	 *
	 * @param htmlStr
	 *            字符串
	 * @return 结果
	 */
	public static String htmlToStr(String htmlStr) {
		String result = "";
		boolean flag = true;
		if (htmlStr == null) {
			return null;
		}
		char[] a = htmlStr.toCharArray();
		int length = a.length;
		for (int i = 0; i < length; i++) {
			if (a[i] == '<') {
				flag = false;
				continue;
			}
			if (a[i] == '>') {
				flag = true;
				continue;
			}
			if (flag == true) {
				result += a[i];
			}
		}
		return result.toString().replaceAll("&nbsp;", " ");
	}

	/**
	 * 去掉字符串里面的html代码。<br>
	 * 要求数据要规范，比如大于小于号要配套,否则会被集体误杀。
	 *
	 * @paramcontent 内容
	 * @return去掉后的内容
	 */
	public static String stripHtml(String content) {
		content = content.replaceAll("<p.*?>", "rn");
		content = content.replaceAll("<brs*/?>", "rn");
		content = content.replaceAll("<.*?>", "");
		content = content.replaceAll("&nbsp;", " ");
		return content;
	}

	/**
	 * 判断某个数据是否在已知的List中存在的方法 存在则返回true否则返回faluse
	 *
	 * @param List
	 *            已知的List集合
	 * @param Strng
	 *            已知的字符串
	 * @return boolean true/false
	 */

	public static boolean theDateIsInThelist(List<String> thelist, String thestring) {

		if (null != thelist && thelist.size() > 0) {
			if (null != thestring) {
				for (int i = 0; i < thelist.size(); i++) {
					String curstring =  thelist.get(i);
					if (thestring.equals(curstring)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 *@param ms
	 *            为要转换的秒数
	 *@return 返回h m s数组
	 */
	public static long[] toHMS(long ms) {
		long d;// 天
		long s;// 秒
		long h;// 小时
		long m;// 分钟
		d = ms / 3600 / 24;
		h = (ms - d * 24 * 3600) / 3600;
		m = (ms - h * 60 * 60 - d * 24 * 3600) / 60;
		s = ms - h * 60 * 60 - m * 60 - d * 24 * 3600;
		return new long[] { d, h, m, s };
	}

	/**
	 * 获取当前时间的字符串 格式为"yyyyMMddHHmmss"
	 * @return 返回获取的当前时间
	 */
	public static String getCurrentTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String curDate = sdf.format(new Date());

		return curDate;
	}

	/**
	 *
	 * @return
	 */
	public static String getStartTimeStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String startDate = sdf.format(new Date(0));

		return startDate;
	}

	public static boolean isInteger(String value){
  	  /*Pattern p = Pattern.compile(CNUMBER_PATTERN);
  	  Matcher m = p.matcher(value);
  	  return m.find();*/
		try{
			Integer.parseInt(value);
		}catch(Exception e){
			return false;
		}
	  	return true;
  }
	public static boolean isFlost(String value){
		try{
			Float.parseFloat(value);
		}catch(Exception e){
			return false;
		}
	  	return true;
	  }
	
	public static boolean isDate(String value,String formatstr){
		try{
			SimpleDateFormat formatter = new SimpleDateFormat(formatstr);
			formatter.setLenient(false);
			formatter.parse(value);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	  	
	  }

	private static final String regEx = "[\u4e00-\u9fa5]";
	private static Pattern pat = Pattern.compile(regEx);
	/**
	 * 判断字符串是否有中文
	 * @param str
	 * @return 有中文返回true
	 */
	public static boolean isContainsChinese(String str){
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()){
			flg = true;
		}
		return flg;
	}

	/**
	 * 判断字符串是否全是数字
	 * @param str
	 * @return 全是数字返回true
	 */
   public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
   
	/**
	 *  判断一个字符串是否含有数字
	 * @param content
	 * @return
	 */
	public static boolean HasDigit(String content) {
	    boolean flag = false;
	    Pattern p = Pattern.compile(".*\\d+.*");
	    Matcher m = p.matcher(content);
	    if (m.matches()) {
	        flag = true;
	    }
	    return flag;
	}
	
	/**
	 * 
	 * @param object
	 * @return 值不为空返回true 为空返回false
	 */
	public static boolean isNotEmpty(Object object){
		if (object!=null&&!"".equals(object)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param object
	 * @return 值为空返回true 不为空 返回false
	 */
	public static boolean isEmpty(Object object){
		if (object!=null&&!"".equals(object)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符串中是否存在非法字符
	 * @param string
	 * @return 存在返回true 不存在返回false
	 */
	public static boolean isConSpeCharacters(String string){ 
	    if(string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*","").length()==0){ 
	        //不包含特殊字符 
	        return false; 
	    } 
	    return true; 
	} 
	
	/**
	 * 获取几天前的日期
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {  
	       Calendar calendar = Calendar.getInstance();  
	       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
	       Date today = calendar.getTime();  
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	       String result = format.format(today);  
	       return result;  
	} 
	
	/**
	 * 保留两位小数点
	 * @return
	 */
	public static String getM2(double f){
		DecimalFormat df = new DecimalFormat("#.00");
		String result="0";
		if(f!=0){
			result=df.format(f);
		}
		return result;
	}
	
	/**
	 * 判断字符串是否全是字母
	 * @param str
	 * @return
	 * returnType:boolean
	 * @author 作者: lvsongyang
	 * @version 创建时间：2018年1月17日 下午6:56:16
	 */
	 public static boolean isPhonticName(String str) {  
	        char[] chars=str.toCharArray();  
	        boolean isPhontic = false;  
	        for(int i = 0; i < chars.length; i++) {  
	            isPhontic = (chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z');  
	            if (!isPhontic) {  
	                return false;  
	            }  
	        }  
	        return true;  
	    } 
	 
	 /**
	  * 判断是否是double类型
	  * @param str
	  * @return
	  */
	 public static boolean isDouble(String str){
		try{
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException ex){
			return false;
		}
	  }
	 
	 /** 
	     * 提供精确的减法运算。 
	     * @param v1 被减数 
	     * @param v2 减数 
	     * @return 两个参数的差 
	     */
	 public static double subtract(double v1, double v2) {
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.subtract(b2).doubleValue();
	    }
	  /** 
	     * 提供精确的加法运算。 
	     * @param v1 被加数 
	     * @param v2 加数 
	     * @return 两个参数的和 
	     */
	    public static double add(double v1, double v2) {

	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.add(b2).doubleValue();
	    }
	    /** 
	     * 提供精确的乘法运算。 
	     * @param v1 被乘数 
	     * @param v2 乘数 
	     * @return 两个参数的积 
	     */
	    public static double multiply(double v1, double v2) {

	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.multiply(b2).doubleValue();
	    }
	    /** 
	     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 
	     * 定精度，以后的数字四舍五入。 
	     * @param v1 被除数 
	     * @param v2 除数 
	     * @param scale 表示表示需要精确到小数点以后几位。 
	     * @return 两个参数的商 
	     */
	    public static double divide(double v1, double v2, int scale) {

	        if (scale < 0) {
	            throw new IllegalArgumentException("The scale must be a positive integer or zero");
	        }
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	    }

	    /** 
	     * 提供精确的小数位四舍五入处理。 
	     * @param v 需要四舍五入的数字 
	     * @param scale 小数点后保留几位 
	     * @return 四舍五入后的结果 
	     */
	    public static double round(double v, int scale) {

	        if (scale < 0) {
	            throw new IllegalArgumentException("The scale must be a positive integer or zero");
	        }
	        BigDecimal b = new BigDecimal(Double.toString(v));
	        BigDecimal one = new BigDecimal("1");
	        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	    }
	    
	    /**
	     * 验证邮箱格式
	     * @param email
	     * @return
	     */
		 public static boolean checkEmail(String email){
			  boolean flag = false;
			  try{
			    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			    Pattern regex = Pattern.compile(check);
			    Matcher matcher = regex.matcher(email);
			    flag = matcher.matches();
			   }catch(Exception e){
			    flag = false;
			   }
			  return flag;
			 }
		 /**
		  * 验证手机号
		  * @param mobileNumber
		  * @return
		  */
		 public static boolean checkMobileNumber(String mobileNumber){
			  boolean flag = false;
			  try{
			    Pattern regex = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
			    Matcher matcher = regex.matcher(mobileNumber);
			    flag = matcher.matches();
			   }catch(Exception e){
			    flag = false;
			   }
			  return flag;
			 }
}
