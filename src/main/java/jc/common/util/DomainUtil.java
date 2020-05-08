package jc.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * util of domain name
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class DomainUtil {

	public static String getDomainName(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		int last = url.indexOf(uri);
		if("/".equals(uri)) {
			last = url.length() - 1;
		}
		return url.substring(url.indexOf(":") + 3, last);
	}
	
	public static String getTopDomainName(HttpServletRequest request) {
		return getTopDomainName(request.getRequestURL().toString());
	}
	
	public static String getTopDomainName(String url) {
		url = url.substring(url.indexOf("//") + 2, StringUtil.indexOfChar(url, '/', 3));
		int countAppear = StringUtil.countOccuranceOfChar(url, '.');
		return url.substring(StringUtil.indexOfChar(url, '.', countAppear - 1) + 1, url.length()).replace("/", "");
	}

	public static String getHost(HttpServletRequest request) {
		String domainName = getDomainName(request);
		return request.getRequestURL().toString().contains("https") ? new StringBuffer("https://").append(domainName).append("/").toString() 
				: new StringBuffer("http://").append(domainName).append("/").toString();
	}
}
