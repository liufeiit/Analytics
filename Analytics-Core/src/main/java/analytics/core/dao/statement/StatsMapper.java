package analytics.core.dao.statement;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月1日 上午9:53:29
 */
public interface StatsMapper {
	String ADD_SQL = "INSERT INTO stats "
			+ "(label_id, year, month, day, hour, type, accumulation, attr, gmt_created, gmt_modified) VALUES "
			+ "(:label_id, :year, :month, :day, :hour, :type, :accumulation, :attr, :gmt_created, :gmt_modified);";

	String UPDATE_SQL = "UPDATE stats SET "
			+ "label_id = :label_id, year = :year, month = :month, day = :day, hour = :hour, type = :type, "
			+ "accumulation = :accumulation, attr = :attr, gmt_modified = NOW() WHERE id = :id;";

	String SELECT_SQL = "SELECT id, label_id, year, month, day, hour, type, accumulation, attr, gmt_created, gmt_modified "
			+ "FROM stats WHERE id = :id;";

	String DELETE_SQL = "DELETE FROM stats WHERE id = :id;";

	String YEAR_WHERE = "WHERE label_id = :labelId AND year = :year AND type = :type";
	String CHECK_STAT_YEAR = "SELECT COUNT(id) FROM stats " + YEAR_WHERE;
	String INCR_STAT_YEAR = "UPDATE stats SET accumulation = accumulation + :accumulation, gmt_modified = :gmt_modified "
			+ YEAR_WHERE;

	String MONTH_WHERE = "WHERE label_id = :labelId AND year = :year AND month = :month AND type = :type";
	String CHECK_STAT_MONTH = "SELECT COUNT(id) FROM stats " + MONTH_WHERE;
	String INCR_STAT_MONTH = "UPDATE stats SET accumulation = accumulation + :accumulation, gmt_modified = :gmt_modified "
			+ MONTH_WHERE;

	String DAY_WHERE = "WHERE label_id = :labelId AND year = :year AND month = :month AND day = :day AND type = :type";
	String CHECK_STAT_DAY = "SELECT COUNT(id) FROM stats " + DAY_WHERE;
	String INCR_STAT_DAY = "UPDATE stats SET accumulation = accumulation + :accumulation, gmt_modified = :gmt_modified "
			+ DAY_WHERE;

	String HOUR_WHERE = "WHERE label_id = :labelId AND year = :year AND month = :month AND day = :day AND hour = :hour AND type = :type";
	String CHECK_STAT_HOUR = "SELECT COUNT(id) FROM stats " + HOUR_WHERE;
	String INCR_STAT_HOUR = "UPDATE stats SET accumulation = accumulation + :accumulation, gmt_modified = :gmt_modified "
			+ HOUR_WHERE;
}