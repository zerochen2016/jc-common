package jc.common.util;

import java.util.regex.Pattern;

/**
 * util of regular expression
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class RegExpUtil {

    
    public static boolean isMobile(String content) {
    	return Pattern.matches("1[3|4|5|6|7|8|9]\\d{9}", content);	
    }
}
