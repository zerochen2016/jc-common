package jc.common.util;

/**
 * util of bean
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class BeanUtil {

	public static <T> T mapToBean(java.util.Map<String,Object> map,Class<T> beanType){
		try {
			T t=beanType.getDeclaredConstructor().newInstance();
			java.beans.PropertyDescriptor [] pds=java.beans.Introspector.getBeanInfo(beanType,Object.class)
					.getPropertyDescriptors();
			for (java.beans.PropertyDescriptor pd : pds) {
				for (java.util.Map.Entry<String,Object> entry : map.entrySet()) {
					if(entry.getKey().equals(pd.getName())) {
						pd.getWriteMethod().invoke(t, entry.getValue());
					}
				}
			}
			return t;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static java.util.Map<String,Object> beanToMap(Object bean){
		try {
			java.util.Map<String,Object> map=new java.util.HashMap<>();
			java.beans.BeanInfo info=java.beans.Introspector.getBeanInfo(bean.getClass(),Object.class);
			java.beans.PropertyDescriptor []pds=info.getPropertyDescriptors();
			for(java.beans.PropertyDescriptor pd:pds) {
				String key=pd.getName();
				Object value= pd.getReadMethod().invoke(bean);
				map.put(key, value);
			}		
			return map;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
