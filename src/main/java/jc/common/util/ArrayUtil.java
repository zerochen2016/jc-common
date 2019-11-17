package jc.common.util;

/**
 * util of array
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class ArrayUtil {

	public static boolean isEmpty(Object[] objects) {
		return (objects == null || objects.length <= 0) ? true : false; 
	}
}
