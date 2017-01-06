package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * 网络请求工具类
 * @author Rex
 */
public class HttpUtil {
	private static final Logger logger = Logger.getLogger(HttpUtil.class);	
	private static final Integer SUCCESS = 200;
	private static final String UTF = "UTF-8";
	
	/**
	 * Http Get 请求
	 * @param requestUrl 请求地址
	 * @return 服务器返回信息
	 * @throws Exception
	 * @author: Rex
	 * @date: 2017年1月1日
	 */
	public static String doHttpGet(String requestUrl){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(requestUrl);
		CloseableHttpResponse response = null;
		String result = null;
		try{
			response = httpClient.execute(httpGet);
			result = EntityUtils.toString(response.getEntity());
			
			if(response.getStatusLine().getStatusCode()==HttpUtil.SUCCESS){//请求成功
				return result;
			}else{//请求失败
				throw new Exception("请求失败");
			}
		} catch(Exception e){
			logger.error(e.getMessage());
			logger.error(result);
		} finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return result;
	}
	
	/**Http Post 请求
	 * @param requestUrl 请求地址
	 * @param params 请求参数
	 * @return 服务器返回信息
	 * @author: Rex
	 * @date: 2017年1月1日
	 */
	public static String doHttpPost(String requestUrl, Map<String, String> params){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(requestUrl);
		CloseableHttpResponse response = null;
		String result = null;
		
		try{
			//设置请求参数
			if(params!=null && !params.isEmpty()){
				List<NameValuePair> pairList = new ArrayList<NameValuePair>();
				for(Entry<String, String> entry : params.entrySet()){
					pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(pairList, HttpUtil.UTF));
			}
			
			response = httpClient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), UTF);
			
			if(response.getStatusLine().getStatusCode()==HttpUtil.SUCCESS){//请求成功
				return result;
			}else{//请求失败
				throw new Exception("请求失败");
			}
		} catch(Exception e){
			logger.error(e.getMessage());
			logger.error(result);
		} finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @param requestUrl 请求地址
	 * @param json 请求参数
	 * @return 服务器返回信息
	 * @author: Rex
	 * @date: 2017年1月1日
	 */
	public static String doHttpPost(String requestUrl, JSONObject json){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(requestUrl);
		CloseableHttpResponse response = null;
		String result = null;
		
		try{
			//设置请求参数
			if(json!=null){
				StringEntity entity = new StringEntity(json.toString(), UTF);
				entity.setContentEncoding(UTF);
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			
			response = httpClient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), UTF);
			
			if(response.getStatusLine().getStatusCode()==HttpUtil.SUCCESS){//请求成功
				return result;
			}else{//请求失败
				throw new Exception("请求失败");
			}
		} catch(Exception e){
			logger.error(e.getMessage());
			logger.error(result);
		} finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return result;
	}
}
