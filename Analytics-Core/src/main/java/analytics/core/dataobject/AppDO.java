package analytics.core.dataobject;

import tulip.data.annotation.Column;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 上午11:22:22
 */
public class AppDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;// App名称
	@Column(name = "token")
	private String token;// 访问token，注册之后系统自动分配
	@Column(name = "description")
	private String description;// App描述

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}