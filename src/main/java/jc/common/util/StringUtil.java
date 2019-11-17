package jc.common.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * Util Class of String
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class StringUtil extends StringUtils{

	public static boolean anyEmpty(String ...args) {
		for(String arg : args) {
			if(isEmpty(arg)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * count occurance of Character
	 * @param string
	 * @param character
	 * @return times
	 */
	public static int countOccuranceOfChar(String string, Character character) {
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		char[] arr = string.toCharArray();
	        
		for (char ch : arr) {
			if (map.containsKey(ch)) {
				Integer old = map.get(ch);
				map.put(ch, old + 1);
			} else {
				map.put(ch,1);
			}
		}
		return map.containsKey(character) ? map.get(character) : 0;
	}
	
	/**
	 * index of character
	 * @param string
	 * @param character
	 * @param occuranceTimes
	 * @return
	 */
	public static int indexOfChar(String string, Character character, int occuranceTimes) {
		if(occuranceTimes == 0) {
			return -1;
		}
		char[] arr = string.toCharArray();
	    int index = -1; 
	    int appearTimes = 0;
		for (int i = 0; i<arr.length; i++) {
			index++;
			char ch = arr[i];
			if(character.equals(ch)) {
				appearTimes++;
			}
			if(occuranceTimes == appearTimes) {
				return index;
			}
			
		}
		return index;
	}
}
