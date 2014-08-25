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
	@Column(name = "model_id")
	private long modelId;// 统计模型ID
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

	public long getModelId() {
		return modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
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