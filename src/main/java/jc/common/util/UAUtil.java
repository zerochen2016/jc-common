package jc.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Util Class of UserAgent
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class UAUtil {

	public static String getUA(HttpServletRequest request) {
		return StringUtil.isEmpty(request.getHeader("user-agent")) ? "noua" : request.getHeader("user-agent");
	}
	
	public static boolean isWechat(HttpServletRequest request) {
		return getUA(request).toLowerCase().indexOf("micromessenger") != -1 ? true : false;	
	}
	
	public static boolean isQQ(HttpServletRequest request) {
		return getUA(request).toLowerCase().indexOf(" qq/") != -1 ? true : false;
	}
	
	public static boolean isIOS(HttpServletRequest request) {
		String userAgent = getUA(request).toLowerCase();
		return (userAgent.indexOf("iphone") != -1 || userAgent.indexOf("ipad") != -1 || userAgent.indexOf("ipod") != -1) ? true : false;
	}
	
	public static boolean isAndroid(HttpServletRequest request) {
		return getUA(request).toLowerCase().indexOf("android") != -1 ? true : false;
	}
}
