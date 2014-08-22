package analytics.core.dataobject;

import tulip.data.annotation.Column;

/**
 * 数据统计类型，不同的统计类型可以确定一种默认的统计展示模型。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月20日 下午3:59:58
 */
public class TypeDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;// 类型名称
	@Column(name = "description")
	private String description;// 类型描述

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