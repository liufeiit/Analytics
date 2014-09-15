package analytics.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月29日 下午1:40:25
 */
public class CalendarUtil {
	public static final String S_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String S_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String Y_M_D_Z = "yyyy-MM-dd'T'HH:mm:ssZZZ";

	public static String formatDate(Date date, String pattern) {
		if (date == null)
			throw new IllegalArgumentException("date is null");
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static Date parseDate(String date, String pattern) throws ParseException {
		if (date == null)
			throw new IllegalArgumentException("date is null");
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(date);
	}
	
	public static int year(Calendar calendar) {
		return calendar.get(Calendar.YEAR);
	}
	
	public static int month(Calendar calendar) {
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public static int day(Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int hour(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int year() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int month() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	
	public static int day() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	
	public static int hour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	
	/*public static List<Integer> month(int year, int month, Comparator<Integer> c) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		
	}
	*/
	public static List<Integer> years(int count, Comparator<Integer> c) {
		List<Integer> list = new ArrayList<Integer>();
		int year = CalendarUtil.year();
		list.add(year);
		for(int i = 0; i < count - 1; i++) {
			year -= 1;
			list.add(year);
		}
		if(c != null) {
			Collections.sort(list, c);
		}
		return list;
	}
	
	/**升序*/
	public static final Comparator<Integer> INT_ASC = new Comparator<Integer>() {
		@Override
		public int compare(Integer i1, Integer i2) {
			if(i1 == null || i2 == null) {
				return 0;
			}
			return i1 > i2 ? 1 : (i1 == i2 ? 0 : -1);
		}
	};
	
	/**降序*/
	public static final Comparator<Integer> INT_DESC = new Comparator<Integer>() {
		@Override
		public int compare(Integer i1, Integer i2) {
			if(i1 == null || i2 == null) {
				return 0;
			}
			return i1 > i2 ? -1 : (i1 == i2 ? 0 : 1);
		}
	};
	
	public static void main(String[] args) {
		System.out.println("INT_ASC : " + years(10, INT_ASC));
		System.out.println("INT_DESC : " + years(10, INT_DESC));
	}
}