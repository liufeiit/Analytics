package analytics.core.service.syn;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tulip.util.CalendarUtil;
import analytics.core.dao.DAOException;
import analytics.core.util.Static;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月29日 下午1:49:52
 */
public class SynEventTask extends SynSource implements Runnable {
	protected final Log log = LogFactory.getLog(getClass());

	final Date date;
	final String dateString;
	final long dateTime;

	private final int year;
	private final int month;
	private final int day;
	private final int hour;

	private int accumulation = Static.DEFAULT_ACCUMULATION;
	private long labelId;

	private final TaskCommand command;

	private final String taskXML;
	private static final String XML_TEMPLATE = "<syn-task date=\"%s\" year=\"%s\" month=\"%s\" day=\"%s\" hour=\"%s\" acc=\"%s\" cmd=\"%s\"/>";

	public SynEventTask(Date date, TaskCommand command) {
		super();
		this.date = date;
		this.command = command;
		this.dateString = CalendarUtil.formatDate(this.date, CalendarUtil.S_YYYY_MM_DD_HH_MM_SS);
		this.dateTime = this.date.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.date);
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		taskXML = toXML();
	}

	@Override
	public final void run() {
		if (command == null) {
			return;
		}
		log.error("Begin execute SynTask : " + toString());
		call();
		log.error("End execute SynTask : " + toString());
	}
	
	protected void call() {
		switch (command) {
		case Event:
			event();
			break;
		case BeginEvent:
			beginEvent();
			break;
		case EndEvent:
			endEvent();
			break;
		default:
			break;
		}
	}

	private void event() {
		try {
			statsDAO.incrStat(labelId, year, accumulation);
		} catch (DAOException e) {
			log.error("IncrStat Year Error.", e);
		}
		try {
			statsDAO.incrStat(labelId, year, month, accumulation);
		} catch (DAOException e) {
			log.error("IncrStat Month Error.", e);
		}
		try {
			statsDAO.incrStat(labelId, year, month, day, accumulation);
		} catch (DAOException e) {
			log.error("IncrStat Day Error.", e);
		}
		try {
			statsDAO.incrStat(labelId, year, month, day, hour, accumulation);
		} catch (DAOException e) {
			log.error("IncrStat Hour Error.", e);
		}
	}

	private void beginEvent() {
		
	}

	private void endEvent() {
		
	}

	public void setAccumulation(int accumulation) {
		this.accumulation = accumulation;
	}
	
	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}

	private String toXML() {
		return String.format(XML_TEMPLATE, dateString, year, month, day, hour, accumulation, command.name());
	}

	@Override
	public String toString() {
		return taskXML;
	}

	public static void main(String[] args) {
		SynEventTask task = new SynEventTask(new Date(), TaskCommand.Event);
		System.out.println("year : " + task.year);
		System.out.println("month : " + task.month);
		System.out.println("day : " + task.day);
		System.out.println("hour : " + task.hour);
	}
}