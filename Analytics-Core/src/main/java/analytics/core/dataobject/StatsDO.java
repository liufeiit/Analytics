package analytics.core.dataobject;

import tulip.data.annotation.Column;

/**
 * 统计数据.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月20日 下午3:59:26
 */
public class StatsDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "label_id")
	private long labelId;// 所在的事件分类标签ID
	// 用于划分统计时间领域
	@Column(name = "year")
	private int year;
	@Column(name = "month")
	private int month;
	@Column(name = "day")
	private int day;
	@Column(name = "hour")
	private int hour;

	@Column(name = "accumulation")
	private double accumulation;// 统计数据值
	
	@Column(name = "attr")
	private long attr;

	public long getLabelId() {
		return labelId;
	}

	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public double getAccumulation() {
		return accumulation;
	}

	public void setAccumulation(double accumulation) {
		this.accumulation = accumulation;
	}

	public long getAttr() {
		return attr;
	}

	public void setAttr(long attr) {
		this.attr = attr;
	}
}