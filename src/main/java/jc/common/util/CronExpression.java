package jc.common.util;

/**
 * cron expression
 * @author JC
 * @Date 2019年12月10日
 * @since 1.0.1
 */
public class CronExpression {

	public static final String CLOCK_ZERO = "0 0 0 * * ?";//0点
	public static final String CLOCK_ONE = "0 0 1 * * ?";//凌晨1点
	public static final String CLOCK_TWO = "0 0 2 * * ?";//凌晨2点
	public static final String CLOCK_THREE = "0 0 3 * * ?";//凌晨3点
	public static final String CLOCK_FOUR = "0 0 4 * * ?";//凌晨4点
	public static final String CLOCK_FIVE = "0 0 5 * * ?";//凌晨5点
	public static final String CLOCK_SIX = "0 0 7 * * ?";//凌晨6点
	public static final String CLOCK_SEVEN = "0 0 7 * * ?";//凌晨7点
	public static final String CLOCK_EIGHT = "0 0 8 * * ?";//凌晨8点
	public static final String CLOCK_NIGHT = "0 0 9 * * ?";//凌晨9点
	public static final String CLOCK_TEN = "0 0 10 * * ?";//凌晨10点
	public static final String CLOCK_ELEVEN = "0 0 11 * * ?";//凌晨11点
	public static final String CLOCK_TWELVE = "0 0 12 * * ?";//凌晨12点
	public static final String CLOCK_THIRTEEN = "0 0 13 * * ?";//凌晨13点
	public static final String CLOCK_FOURTEEN = "0 0 14 * * ?";//凌晨14点
	public static final String CLOCK_FIFTEEN = "0 0 15 * * ?";//凌晨15点
	public static final String CLOCK_SIXTEEN = "0 0 16 * * ?";//凌晨16点
	public static final String CLOCK_SEVENTEEN = "0 0 17 * * ?";//凌晨17点
	public static final String CLOCK_EIGHTEEN = "0 0 18 * * ?";//凌晨18点
	public static final String CLOCK_NIGHTEEN = "0 0 19 * * ?";//凌晨19点
	public static final String CLOCK_TWENTY = "0 0 20 * * ?";//凌晨20点
	public static final String CLOCK_TWENTYONE = "0 0 21 * * ?";//凌晨21点
	public static final String CLOCK_TWENTYTWO = "0 0 22 * * ?";//晚上22点
	public static final String CLOCK_TWENTYTHREE = "0 0 23 * * ?";//晚上23点
	
	public static final String FIVE_SECOND = "*/5 * * * * ?";//5秒钟
	public static final String ONE_MINUTES = "0 */1 * * * ?" ;//每分钟
	public static final String FIVE_MINUTES = "0 0/5 * * * ?" ;//5分钟
	
	public static final String PER_HOUR = "0 0 0/1 * * ?";//每小时
	public static final String PER_TWO_HOUR = "0 0 0/2 * * ?";//每两小时
	
	public static final String TWELVE_FIVE = "0 5 0 * * ?";//0点5
	public static final String TWELVE_TEN = "0 10 0 * * ?";//0点10
	public static final String TWELVE_TWEENTY = "0 20 0 * * ?";//0点20
	public static final String TWELVE_THIRTY = "0 30 0 * * ?";//0点30
	
	public static final String CLOCK_ONE_FIVE = "0 5 1 * * ?";//凌晨1点5
	public static final String CLOCK_ONE_TEN = "0 10 1 * * ?";//凌晨1点10
	
	public static final String CLOCK_TWENTYTHREE_FIFTYFIVE = "0 55 23 * * ?";//每天23点55分
	public static final String EVERYMONTH_TWENTYTHREE_DAYONE = "0 0 23 1 1/1 ?";//每月1日23时
	
}
