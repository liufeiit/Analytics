package analytics.core.service.syn;

import java.util.Calendar;
import java.util.Date;

import analytics.core.service.Static;
import tulip.util.CalendarUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月29日 下午1:49:52
 */
public class SynEventTask extends SynSource implements Runnable {

	private final Date date;

	private final int year;
	private final int month;
	private final int day;
	private final int hour;

	private int accumulation = Static.DEFAULT_ACCUMULATION;

	private final TaskCommand command;

	public SynEventTask(Date date, TaskCommand command) {
		super();
		this.date = date;
		this.command = command;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
	}

	@Override
	public void run() {
		
	}
	
	public void setAccumulation(int accumulation) {
		this.accumulation = accumulation;
	}

	public static void main(String[] args) {
		System.out.println(CalendarUtil.formatDate(new Date(), CalendarUtil.S_YYYY_MM_DD_HH_MM_SS));
		SynEventTask task = new SynEventTask(new Date(), TaskCommand.Event);
		System.out.println("year : " + task.year);
		System.out.println("month : " + task.month);
		System.out.println("day : " + task.day);
		System.out.println("hour : " + task.hour);
	}
}