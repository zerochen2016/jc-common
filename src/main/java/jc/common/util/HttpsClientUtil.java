package jc.common.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.HttpClient;  

/**
 * util of https client
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class HttpsClientUtil {
	
	/**
	 * @param url
	 * @param body 如:image=http://www.imageim.com/dkdfjd.jpg
	 * @param authorization 如Basic XXXXXX
	 * @return
	 */
	public static String doPost(String url,String body,String authorization) {
	    OutputStream outputStream = null;
	    OutputStreamWriter outputStreamWriter = null;
	    InputStream inputStream = null;
	    InputStreamReader inputStreamReader = null;
	    BufferedReader reader = null;
	    StringBuffer resultBuffer = new StringBuffer();
	    String tempLine = null;    
		try{
	    	HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
		    httpURLConnection.setDoOutput(true);
		    httpURLConnection.setRequestMethod("POST");
		    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    httpURLConnection.setRequestProperty("Authorization", authorization);
		    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(body.length()));
	        outputStream = httpURLConnection.getOutputStream();
	        outputStreamWriter = new OutputStreamWriter(outputStream);
	        outputStreamWriter.write(body);
	        outputStreamWriter.flush();
	        //响应失败
	        if (httpURLConnection.getResponseCode() != 200) {
	            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
	        }
	        //接收响应流
	        inputStream = httpURLConnection.getInputStream();
	        inputStreamReader = new InputStreamReader(inputStream);
	        reader = new BufferedReader(inputStreamReader);
	        while ((tempLine = reader.readLine()) != null) {
	            resultBuffer.append(tempLine);
	        }
	        return resultBuffer.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (outputStreamWriter != null)outputStreamWriter.close();
				if (outputStream != null)outputStream.close();
				if (reader != null)reader.close();
				if (inputStreamReader != null)inputStreamReader.close();
				if (inputStream != null)inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
        return "";
	}
	
	public static String doPost(String url,Map<String,Object> headers,Map<String, Object> params) {
		try{
			HttpClient httpClient = new HttpClient();
			//设置httpclient读取内容时使用的字符集    
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");            
			//创建POST方法的实例    
			PostMethod method = new PostMethod(url);
			for(Map.Entry<String, Object> header:headers.entrySet()) {
				method.addRequestHeader(header.getKey(), String.valueOf(header.getValue()));
			}
			RequestEntity entity = new StringRequestEntity(GsonExtension.GsonString(params),"application/json","UTF-8");
			method.setRequestEntity(entity);
			//执行POST方法并取得返回状态码，200表示正常，其它代码为异常    
			int statusCode = httpClient.executeMethod(method);  
			System.out.println("@zero.chen------:https request status Code:"+statusCode);
			InputStream is = method.getResponseBodyAsStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));  
			StringBuffer response = new StringBuffer();  
			String readLine;  
          	while (((readLine = br.readLine()) != null)) {  
          		response.append(readLine);  
          	}  
          	if(statusCode!=200) {
          		System.out.print("@zero.chen------:https response="+response);	
          	}
          	return response.toString();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return "";  
	}
	
	public static String doGet(String url,Map<String, Object> params) {
		try{
			HttpClient httpClient = new HttpClient();
			//设置httpclient读取内容时使用的字符集    
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
			int i = 1;
			for(Map.Entry<String, Object> param:params.entrySet()) {
				String key = param.getKey();
				String value = param.getValue().toString();
				if(i==1) {
					url += "?"+key + "=" + value;
				}else {
					url += "&"+key + "=" + value;
				}
				i++;

			}
			//创建GET方法的实例    
			GetMethod method = new GetMethod(url);
			//执行Get方法并取得返回状态码，200表示正常，其它代码为异常    
			int statusCode = httpClient.executeMethod(method);  
			System.out.println("@zero.chen------:https request status Code:"+statusCode);
			InputStream is = method.getResponseBodyAsStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));  
			StringBuffer response = new StringBuffer();  
			String readLine;  
          	while (((readLine = br.readLine()) != null)) {  
          		response.append(readLine);  
          	}  
          	if(statusCode!=200) {
          		System.out.print("@zero.chen------:https response="+response);	
          	}
          	return response.toString();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return "";  
	}	
}  
