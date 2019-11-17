package jc.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * extension of spring bean factory
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class BeanFactory {
	
	private static ApplicationContext appCtx;
	
	public static void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		appCtx=ctx;
		
	}
	
	public static <T>T getBean(Class<T>entityClass)
	{
		return appCtx.getBean(entityClass);
	}
	
	public static Object getBean(String beanName) {
		return appCtx.getBean(beanName);
	}
	
}

