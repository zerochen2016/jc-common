package jc.common.util;

import java.util.Random;


/**
 * util of random
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class RandomUtil {
	
	public static Random random = new Random();
	
	public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   
    public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   
    public static final String NUMBER_CHAR = "0123456789";   
    
	/**
	 * @Remark 生成带前缀和时间的随机字符串，包含字母和数字
	 * @Remark 时间格式为：YYYYMMDDHHMMSS
	 * @date 2018年10月29日
	 * @param prefix 前缀
	 * @param appendLength 除前缀和时间追加的长度
	 * @return
	 */
	public static String getRandomCharTimestampPre(String prefix,int appendLength) {
		return prefix+DateUtil.getSystemTimeStr(DateUtil.TIMEFORMAT.YYYYMMDDHHMMSS)+getRandomChar(appendLength);
	}
	/**
	 * @Remark 生成带前缀和时间的随机字符串，包含字母和数字
	 * @date 2018年10月29日
	 * @param prefix 前缀
	 * @param timeFormat 时间格式
	 * @param appendLength 除前缀和时间追加的长度
	 * @return
	 */
	public static String getRandomCharTimestampPre(String prefix,String timeFormat,int appendLength) {
		return prefix+DateUtil.getSystemTimeStr(timeFormat)+getRandomChar(appendLength);
	}
	/**
	 * 生成n位随机字符串，包含字母和数字
	 * @param length
	 * @return
	 */
	public static String getRandomChar(int length) {
		 StringBuffer sb = new StringBuffer();   
         for (int i = 0; i < length; i++) {   
                 sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));   
         }   
         return sb.toString(); 
	}
	
	/**
	 * 生成n位随机字符串，包含字母
	 * @param length
	 * @return
	 */
	public static String getRandomLetter(int length) {
		 StringBuffer sb = new StringBuffer();   
         for (int i = 0; i < length; i++) {   
                 sb.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length())));   
         }   
         return sb.toString(); 
	}
	
	/**
	 * 生成n位随机字符串，包含数字
	 * @param length
	 * @return
	 */
	public static String getRandomNumber(int length) {
		 StringBuffer sb = new StringBuffer();   
         for (int i = 0; i < length; i++) {   
                 sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));   
         }   
         return sb.toString(); 
	}
	
	/**
	 * 生成随机字符串，包含字母数字
	 * @param prefix 前缀
	 * @param length 长度
	 * @return
	 */
	public static String getRandomChar(String prefix,int length) {
		int loopLength = length-prefix.length();
		StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < loopLength; i++) {   
        	sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));   
        }   
        return prefix+sb.toString(); 
	}
	
	/**
	 * 生成随机字符串，包含字母
	 * @param prefix 前缀
	 * @param length 长度
	 * @return
	 */
	public static String getRandomLetter(String prefix,int length) {
		int loopLength = length-prefix.length();
		 StringBuffer sb = new StringBuffer();   
         for (int i = 0; i < loopLength; i++) {   
                 sb.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length())));   
         }   
         return prefix+sb.toString(); 
	}
	
	/**
	 * 生成随机字符串，包含数字
	 * @param prefix 前缀
	 * @param length 长度
	 * @return
	 */
	public static String getRandomNumber(String prefix,int length) {
		int loopLength = length-prefix.length();
		 StringBuffer sb = new StringBuffer();   
         for (int i = 0; i < loopLength; i++) {   
                 sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));   
         }   
         return prefix+sb.toString(); 
	}
	/**
	 * 随机取范围内的值
	 * @param min 包含
	 * @param max 不包含
	 * @return
	 */
	public static int getRandomRank(int min,int max) {
		int j = max - min;
		return new Random().nextInt(j) + min;
	}
	
	public static Object getRandomWordChars(int wordNumber, int length) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i< wordNumber; i ++) {
			sb.append(RandomUtil.getRandomChar(length)).append(",");
		}
		return sb.subSequence(0, sb.length() - 1);
	}
	
}
