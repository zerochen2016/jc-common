package jc.common.util;

/**
 * cron expression
 * @author JC
 * @Date 2019年12月10日
 * @since 1.0.1
 */
public class CronExpression {

	public static final String FIVE_SECOND = "*/5 * * * * ?";//5秒钟
	public static final String ONE_MINUTES = "0 */1 * * * ?" ;//每分钟
	public static final String FIVE_MINUTES = "0 0/5 * * * ?" ;//5分钟
	public static final String TWELVE_FIVE = "0 5 0 * * ?";//0点5
	public static final String TWELVE_TEN = "0 10 0 * * ?";//0点10
	public static final String TWELVE_TWEENTY = "0 20 0 * * ?";//0点20
	public static final String TWELVE_THIRTY = "0 30 0 * * ?";//0点30
	public static final String CLOCK_ONE = "0 0 1 * * ?";//凌晨1点
	public static final String CLOCK_ONE_FIVE = "0 5 1 * * ?";//凌晨1点5
	public static final String CLOCK_ONE_TEN = "0 10 1 * * ?";//凌晨1点10
	public static final String CLOCK_TWO = "0 0 2 * * ?";//凌晨2点
	public static final String CLOCK_THREE = "0 0 3 * * ?";//凌晨3点		
	public static final String CLOCK_FIVE = "0 0 5 * * ?";//凌晨5点
	public static final String CLOCK_SEVEN = "0 0 7 * * ?";//凌晨7点
	public static final String CLOCK_TWENTYTWO = "0 0 22 * * ?";//每天22点
	public static final String CLOCK_TWENTYTHREE = "0 0 23 * * ?";//每天23点
	public static final String CLOCK_TWENTYTHREE_FIFTYFIVE = "0 55 23 * * ?";//每天23点55分
	public static final String PER_HOUR = "0 0 0/1 * * ?";//每小时
	public static final String PER_TWO_HOUR = "0 0 0/2 * * ?";//每两小时
	public static final String EVERYMONTH_TWENTYTHREE_DAYONE = "0 0 23 1 1/1 ?";//每月1日23时
	
}
