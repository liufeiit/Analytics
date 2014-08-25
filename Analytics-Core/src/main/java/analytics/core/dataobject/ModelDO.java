package analytics.core.dataobject;

import tulip.data.annotation.Column;

/**
 * 统计数据模型, 表示这样一组统计所属的数据统计计算以及展示模式。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月21日 下午5:47:13
 */
public class ModelDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "model")
	private int model;
	@Column(name = "name")
	private String name;// 模型名称
	@Column(name = "description")
	private String description;// 模型描述

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
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