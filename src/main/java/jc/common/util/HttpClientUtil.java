package jc.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * util of apache http client 
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class HttpClientUtil {

	/**
	 * @Remark 构建客户端
	 * @return
	 */
	public static CloseableHttpClient buildCloseableHttpClient() {
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		CloseableHttpClient client = clientBuilder.build();
		return client;
	}
	/**
	 * @Remark 执行POST请求，UTF-8编码
	 * @date 2018年9月30日
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url,Map<String,Object> params) {
		//2、创建post请求对象
		HttpPost httpPost = new HttpPost(url);
		//3、构造请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for(Map.Entry<String, Object> param:params.entrySet()) {
			list.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
		}
		if(list.size()>0) {
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String result = doExecute(httpPost);
		return result;
	}	
	/**
	 * @Remark 执行POST请求，UTF-8编码
	 * @date 2018年9月30日
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url,Map<String,String> headers,Map<String,String> params) {
		//2、创建post请求对象
		HttpPost httpPost = new HttpPost(url);
		for(Map.Entry<String, String> header:headers.entrySet()) {
			httpPost.setHeader(header.getKey(), header.getValue());
		}
		//3、构造请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for(Map.Entry<String, String> param:params.entrySet()) {
			list.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
		}
		if(list.size()>0) {
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String result = doExecute(httpPost);
		return result;
	}		
	/**
	 * @Remark 执行post请求
	 * @param url 请求url
	 * @param params 请求参数
	 * @param charset 编码
	 * @return
	 */
	public static String doPost(String url,Map<String,Object> params,String charset) {
		
		//2、创建post请求对象
		HttpPost httpPost = new HttpPost(url);
		//3、构造请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for(Map.Entry<String, Object> param:params.entrySet()) {
			list.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
		}
		if(list.size()>0) {
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(list,charset));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String result = doExecute(httpPost);
		return result;
	}
	
	/**
	 * @Remark 执行get请求，UTF-8编码
	 * @param url 请求url
	 * @param charset 编码
	 * @return
	 */
	public static String doGet(String url) {
		return doExecute(new HttpGet(url));
	}	
	
	/**
	 * @Remark 执行get请求，UTF-8编码
	 * @param url 请求url
	 * @param params 请求参数
	 * @param charset 编码
	 * @return
	 */
	public static String doGet(String url,Map<String,Object> params) {
		StringBuffer sb = new StringBuffer();
		if(params!=null && !params.isEmpty()) {
			for(Map.Entry<String, Object> param:params.entrySet()) {
				sb.append(param.getKey());
				sb.append("=");
				try {
					sb.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				sb.append("&");
			}
		}
		if(sb!=null&&sb.length()>0) {
			url += "?"+sb.substring(0,sb.length()-1);
		}
		String result = doExecute(new HttpGet(url));
		return result;
	}	
	/**
	 * @Remark 执行get请求
	 * @param url 请求url
	 * @param params 请求参数
	 * @param charset 编码
	 * @return
	 */
	public static String doGet(String url,Map<String,Object> params,String charset) {
		StringBuffer sb = new StringBuffer();
		if(params!=null && !params.isEmpty()) {
			for(Map.Entry<String, Object> param:params.entrySet()) {
				sb.append(param.getKey());
				sb.append("=");
				try {
					sb.append(URLEncoder.encode(String.valueOf(param.getValue()),charset));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				sb.append("&");
			}
		}
		if(sb!=null&&sb.length()>0) {
			url += "?"+sb.substring(0,sb.length()-1);
		}
		String result = doExecute(new HttpGet(url));
		return result;
	}
	public static String doGetString(String url,Map<String,String> params,String charset) {
		StringBuffer sb = new StringBuffer();
		if(params!=null && !params.isEmpty()) {
			for(Map.Entry<String, String> param:params.entrySet()) {
				sb.append(param.getKey());
				sb.append("=");
				try {
					sb.append(URLEncoder.encode(String.valueOf(param.getValue()),charset));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				sb.append("&");
			}
		}
		if(sb!=null&&sb.length()>0) {
			url += "?"+sb.substring(0,sb.length()-1);
		}
		String result = doExecute(new HttpGet(url));
		return result;
	}
	
    /**
     * get请求
     * @param url 请求地址（get请求时参数自己组装到url上）
     * @param headerMap 请求头
     * @return 响应文本
     */
    public static String doGet(String url, Map<String, String> headers, Map<String,String> params) {
        // 请求地址，以及参数设置
        HttpGet get = new HttpGet(url);
        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                get.setHeader(entry.getKey(), entry.getValue());
            }
        }
		StringBuffer urlsb = new StringBuffer();
		if(params!=null && !params.isEmpty()) {
			for(Map.Entry<String, String> param:params.entrySet()) {
				urlsb.append(param.getKey());
				urlsb.append("=");
				try {
					urlsb.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				urlsb.append("&");
			}
		}
		if(urlsb!=null&&urlsb.length()>0) {
			url += "?"+urlsb.substring(0,urlsb.length()-1);
		}
        // 获取响应对象
        HttpResponse response = null;
        try {
            response = HttpClients.createDefault().execute(get);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response == null) {
            return null;
        }
        // 获取Entity对象
        HttpEntity entity = response.getEntity();
        // 获取响应信息流
        InputStream in = null;
        if (entity != null) {
            try {
                in =  entity.getContent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        StringBuilder sb = new StringBuilder();
        String line;
 
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = sb.toString();
        return str;
    }
	/**
	/**
	 * @Remark 执行请求
	 * @param requset
	 * @return
	 */
	private static String doExecute(HttpRequestBase requset){
		StringBuffer resultBuffer = null;
		BufferedReader br = null;
		try {
			HttpResponse response = buildCloseableHttpClient().execute(requset);
			br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String temp;
			resultBuffer = new StringBuffer();
			while((temp = br.readLine()) != null) {
				resultBuffer.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                }  
            } 
		}
		return resultBuffer.toString();
	}
	/**
	 * @Remark 含file的http请求
	 * @date 2018年11月18日
	 * @param url
	 * @param params
	 * @param file
	 * @param filename
	 * @return
	 */
	public static String doPost(String url, Map<String, String> params, File file,String filename) {
		try {
			HttpClient httpclient = buildCloseableHttpClient();
			HttpPost post = new HttpPost(url);
			FileBody fileBody = new FileBody(file);
			MultipartEntityBuilder entity = MultipartEntityBuilder.create();
			entity.addPart(filename, fileBody);
			for(Map.Entry<String, String> entry:params.entrySet()) {
				entity.addPart(entry.getKey(), new StringBody(entry.getValue(),ContentType.TEXT_PLAIN));
			}
			
			post.setEntity(entity.build());

			HttpResponse response = httpclient.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entitys = response.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(entitys.getContent()));
				String line = reader.readLine();
				System.out.println(line);
				return line;
			}else{
				HttpEntity r_entity = response.getEntity();
				String responseString = EntityUtils.toString(r_entity);
				System.out.println("错误码是："+response.getStatusLine().getStatusCode()+"  "+response.getStatusLine().getReasonPhrase());
				System.out.println("出错原因是："+responseString);
	             //你需要根据出错的原因判断错误信息，并修改
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void main(String args[]) {
		String url = "http://zb.xcmzb.xyz/Appapi/Pay/aliAutoNotify?gmt_create=2020-01-23 12:21:11&charset=UTF-8&seller_email=$$$&subject=充值&sign=$$$&body=直播-50&buyer_id=2088802391595912&invoice_amount=38.00&notify_id=2020012300222122112095911424252108&fund_bill_list=[{\\\"amount\\\":\\\"38.00\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]&notify_type=trade_status_sync&trade_status=TRADE_SUCCESS&receipt_amount=38.00&app_id=2019071365811491&buyer_pay_amount=38.00&sign_type=RSA2&seller_id=2088031841613494&gmt_payment=2020-01-23 12:21:12&notify_time=2020-01-23 12:21:13&version=1.0&out_trade_no=55585_20200123122106570&total_amount=38.00&trade_no=2020012322001495911414153074&auth_app_id=2019071365811491&buyer_logon_id=470***@qq.com&point_amount=0.00";
		System.out.println(url.charAt(65));
//		System.out.println(HttpClientUtil.doGet(url, new HashMap<String,Object>()));
	}
}
