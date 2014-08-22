package analytics.core.dataobject;

import java.io.Serializable;
import java.util.Date;

import tulip.data.annotation.Column;

/**
 * 基础统计数据结构.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月11日 上午11:01:11
 */
public class BaseDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	private long id;
	@Column(name = "gmt_created")
	private Date gmt_created;
	@Column(name = "gmt_modified")
	private Date gmt_modified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getGmt_created() {
		return gmt_created;
	}

	public void setGmt_created(Date gmt_created) {
		this.gmt_created = gmt_created;
	}

	public Date getGmt_modified() {
		return gmt_modified;
	}

	public void setGmt_modified(Date gmt_modified) {
		this.gmt_modified = gmt_modified;
	}
}