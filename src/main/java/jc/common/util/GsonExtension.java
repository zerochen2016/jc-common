package jc.common.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * extension of gson
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class GsonExtension {
	
	private static Gson gson = null;
	
	static {
		if (gson == null) {
			gson =new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();;
        }
    }

    
	public static Gson getGson() {
		return gson;
	}
    
	
	private GsonExtension() {
	}

    
	public static String GsonString(Object object) {
		String gsonString = null;
		if (gson != null) {
			gsonString = gson.toJson(object);
        }
   return gsonString;
	}

    /**
     * 转成bean
     * 
     * @param gsonString
     * @param cls
     * @return
     */
	public static <T> T GsonToBean(String gsonString, Class<T> cls) {
		T t = null;
		if (gson != null) {
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}

	public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
		List<T> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
			}.getType());
		}
   return list;
	}
    
	public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
		List<Map<String, T>> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString,new TypeToken<List<Map<String, T>>>() {}.getType());
        }
		return list;
	}

	public static Map<String, Object> GsonToMaps(String gsonString) {
		Map<String, Object> map = null;
		if (gson != null) {
			map = gson.fromJson(gsonString, new TypeToken<Map<String, Object>>() {}.getType());
		}
		return map;
    }
	
}