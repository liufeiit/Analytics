package analytics.core.dataobject;

import tulip.data.annotation.Column;

/**
 * 事件细分类别标签统计领域模型.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月11日 上午11:15:57
 */
public class LabelDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "event_id")
	private long eventId;// 所在的Event事件ID
	@Column(name = "type_id")
	private long typeId;// 统计类型ID
	@Column(name = "name")
	private String name;// Label名称
	@Column(name = "description")
	private String description;// Label描述

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}