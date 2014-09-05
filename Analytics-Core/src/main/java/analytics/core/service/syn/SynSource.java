package analytics.core.service.syn;

import analytics.core.dao.AppDAO;
import analytics.core.dao.EventDAO;
import analytics.core.dao.LabelDAO;
import analytics.core.dao.ModelDAO;
import analytics.core.dao.StatsDAO;
import analytics.core.dao.UserDAO;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年8月29日 下午3:11:00
 */
public class SynSource {
	protected AppDAO appDAO;
	protected EventDAO eventDAO;
	protected LabelDAO labelDAO;
	protected ModelDAO modelDAO;
	protected StatsDAO statsDAO;
	protected UserDAO userDAO;
	
	public void initialize(SynSourceInitialize initialize) {
		initialize.initialize(this);
	}

	public void setAppDAO(AppDAO appDAO) {
		this.appDAO = appDAO;
	}

	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	public void setLabelDAO(LabelDAO labelDAO) {
		this.labelDAO = labelDAO;
	}

	public void setModelDAO(ModelDAO modelDAO) {
		this.modelDAO = modelDAO;
	}

	public void setStatsDAO(StatsDAO statsDAO) {
		this.statsDAO = statsDAO;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public interface SynSourceInitialize {
		void initialize(SynSource source);
	}
}