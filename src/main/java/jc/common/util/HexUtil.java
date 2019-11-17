package jc.common.util;

/**
 * util of hex
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class HexUtil {

	/**
	 * @Remark 十进制转十六进制
	 * @param value
	 * @return
	 */
	public static String toHex(int value) {
		return String.format("%x", value);
	}
	
}
