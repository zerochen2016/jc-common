package jc.common.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * util of db
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class DBUtil {
	
	/**
	 * 
	 * @param url: jdbc:mysql://IP:PORT/DATABASE?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&useSSL=false
	 * @param user: user
	 * @param password: password
	 * @param driverClassName: com.mysql.jdbc.Driver
	 * @return
	 */
	public static Connection getConnection(String url, String user, String password, String driverClassName) {
		try {
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, user, password);
			if(conn!=null) {
				System.out.println("--");
			}
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeAll(AutoCloseable ...autoCloseables) {
		for(AutoCloseable ac:autoCloseables) {
			if(ac!=null) {
				try {
					ac.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
