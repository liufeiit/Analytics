package analytics.core.dataobject;

import java.util.List;

import tulip.data.annotation.Column;
import tulip.util.CollectionUtil;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月25日 上午11:22:22
 */
public class AppDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private long userId;// 用户ID
	
	@Column(name = "name")
	private String name;// App名称
	@Column(name = "token")
	private String token;// 访问token，注册之后系统自动分配
	@Column(name = "description")
	private String description;// App描述
	
	private List<EventDO> eventList;

	public List<EventDO> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventDO> eventList) {
		this.eventList = eventList;
	}
	
	public boolean hasEvent() {
		return !CollectionUtil.isEmpty(eventList);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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