package com.imooc.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class WeatherReportByCity {
	    /**
	     * 根据城市名获取
	     * @param cityName
	     * @return
	     */
	    public static String excute(String cityName){
	        String url=//此处以返回json格式数据示例,所以format=2,以根据城市名称为例,cityName传入中文
	                "http://v.juhe.cn/weather/index?cityname="+cityName+"&key=3b5b0d8b25498d027e33f13bc87ef602";
	        return PureNetUtil.get(url);//通过工具类获取返回数据
	    }
	    
	    
	    /**
		 * 发送GET请求
		 * @param path 请求路径
		 * @param params 请求参数
		 * @param encoding 编码
		 * @return 请求是否成功
		 */
		public static boolean sendGETRequest(String path, Map<String, String> params, String ecoding) throws Exception{
			// http://192.168.1.100:8080/web/ManageServlet?title=xxx&timelength=90
			StringBuilder url = new StringBuilder(path);
			url.append("?");
			for(Map.Entry<String, String> entry : params.entrySet()){
				url.append(entry.getKey()).append("=");
				url.append(URLEncoder.encode(entry.getValue(), ecoding));
				url.append("&");
			}
			url.deleteCharAt(url.length() - 1);
			HttpURLConnection conn = (HttpURLConnection)new URL(url.toString()).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == 200){
				return true;
			}
			return false;
		}
		
		/**
		 * 发送Post请求
		 * @param path 请求路径
		 * @param params 请求参数
		 * @param encoding 编码
		 * @return 请求是否成功
		 */
		public static boolean sendPOSTRequest(String path, Map<String, String> params, String encoding) throws Exception{
			//  title=liming&timelength=90
			StringBuilder data = new StringBuilder();
			if(params!=null && !params.isEmpty()){
				for(Map.Entry<String, String> entry : params.entrySet()){
					data.append(entry.getKey()).append("=");
					data.append(URLEncoder.encode(entry.getValue(), encoding));
					data.append("&");
				}
				data.deleteCharAt(data.length() - 1);
			}
			byte[] entity = data.toString().getBytes();//生成实体数据
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);//允许对外输出数据
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
			OutputStream outStream = conn.getOutputStream();
			outStream.write(entity);
			if(conn.getResponseCode() == 200){
				return true;
			}
			return false;
		}
}
