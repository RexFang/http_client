package test;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import util.HttpUtil;

public class HttpUtilTest {

	@Test
	@Ignore
	public void testDoHttpGet() {
		try {
			String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=@APPID@&secret=@APPSECRET@";
			requestUrl = requestUrl.replace("@APPID@", "wx808053292ada9384");
			requestUrl = requestUrl.replace("@APPSECRET@", "7321eed75f95c383d28c249110cd024f");
			String result = HttpUtil.doHttpGet(requestUrl);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testDoHttpPost(){
		try {
			String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=@APPID@&secret=@APPSECRET@";
			requestUrl = requestUrl.replace("@APPID@", "wx808053292ada9384");
			requestUrl = requestUrl.replace("@APPSECRET@", "7321eed75f95c383d28c249110cd024f");
			String token = HttpUtil.doHttpGet(requestUrl);
			JSONObject tokenJson = new JSONObject(token);
			
			requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=@token@";
			requestUrl = requestUrl.replace("@token@", tokenJson.getString("access_token"));
			JSONObject tag = new JSONObject();
			tag.put("name", "天河");
			JSONObject json = new JSONObject();
			json.put("tag", tag);
			String result = HttpUtil.doHttpPost(requestUrl, json);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDoHttpPost1(){
		try{
			String requestUrl = "https://www.baidu.com";
			Map<String, String> params = new HashMap<String, String>();
			params.put("key", "test");
			String result = HttpUtil.doHttpPost(requestUrl, params);
			System.out.println(result);
		} catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
