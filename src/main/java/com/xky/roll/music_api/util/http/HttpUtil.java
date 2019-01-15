package com.xky.roll.music_api.util.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


/**
* @ClassName: HttpUtil
* @Description: 通用Rest接口工具类
* @author ysg
* @date 2017年5月26日 下午11:47:32
*/
public class HttpUtil {
	private final static Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
	
	/**
	 * Map类型调用post接口
	 * @param url
	 * @param paramMap
	 * @return
	 */
	
	public String startInvoke(String url, Map<String, Object> headMap,Map<String, Object> paramMap) {
		log.debug("-------Interface url："+url);
		String resStr = (String)httpPost(url, headMap, paramMap, new DealResponse() {
			@Override
			public Object onSuccess(CloseableHttpResponse arg0) throws IOException {
				String str = null;
				if(arg0.getStatusLine().getStatusCode()==200) {
					HttpEntity entity = arg0.getEntity();
					byte[] bytes = IOUtils.toByteArray(entity.getContent());
					if (null != entity) {
						EntityUtils.consume(entity); // Consume response content
					}
					str = new String(bytes);
				}else {
					HttpEntity entity = arg0.getEntity();
					byte[] bytes = IOUtils.toByteArray(entity.getContent());
					if (null != entity) {
						EntityUtils.consume(entity); // Consume response content
					}
					str = new String(bytes);
				}
				System.out.println("-----system----Response："+arg0.getStatusLine().getStatusCode()+"count---："+str);
				return str;
			}

			@Override
			public void onFailure(HttpUriRequest arg0, Exception arg) {
				log.error("error", arg);
			}

			@Override
			public void callBack(String url,Object message) {
				// TODO Auto-generated method stub
				
			}
		});
		return resStr;
	}
	
	/**
	 * Object类型调用post接口
	 * @param url
	 * @param param
	 * @return
	 */
	public String startInvoke(String url,Object param,Map<String, String> headMap) {
		log.debug("-------Interface url："+url);
		String resStr = (String)httpPost(url, param, new DealResponse() {
			@Override
			public Object onSuccess(CloseableHttpResponse arg0) throws IOException {
				String str = null;
				if(arg0.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = arg0.getEntity();
					byte[] bytes = IOUtils.toByteArray(entity.getContent());
					if (null != entity) {
						EntityUtils.consume(entity); // Consume response content
					}
					str = new String(bytes);
				}else {
					HttpEntity entity = arg0.getEntity();
					byte[] bytes = IOUtils.toByteArray(entity.getContent());
					if (null != entity) {
						EntityUtils.consume(entity); // Consume response content
					}
					str = new String(bytes);
				}
				log.info("---1------Response："+arg0.getStatusLine().getStatusCode()+"，count---："+str);
				System.out.println("---2------Response："+arg0.getStatusLine().getStatusCode()+"，count---："+str);
				return str;
			}

			@Override
			public void onFailure(HttpUriRequest arg0, Exception arg) {
				log.error("error", arg);
			}

			@Override
			public void callBack(String url,Object message) {
				// TODO Auto-generated method stub
				
			}
		},headMap);
		return resStr;
	}
	
	/**
	 * 调用Get接口
	 * @param url
	 * @param param
	 * requestProperty 　　设置请求头
	 * @return
	 */
	public String doGet(String url,Object param,String requestProperty) {
		log.debug("url："+url);
		String resStr = (String)httpGet(url, new DealResponse() {
			@Override
			public Object onSuccess(CloseableHttpResponse arg0) throws IOException {
				String str = null;
				if(arg0.getStatusLine().getStatusCode()==200) {
					HttpEntity entity = arg0.getEntity();
					byte[] bytes = IOUtils.toByteArray(entity.getContent());
					if (null != entity) {
						EntityUtils.consume(entity); // Consume response content
					}
					str = new String(bytes);
				}else {
					HttpEntity entity = arg0.getEntity();
					byte[] bytes = IOUtils.toByteArray(entity.getContent());
					if (null != entity) {
						EntityUtils.consume(entity); // Consume response content
					}
					str = new String(bytes);
				}
				//log.info("调用 接口 完成，返回状态码："+arg0.getStatusLine().getStatusCode()+"，消息内容："+str);
				System.out.println("----system-----Response："+arg0.getStatusLine().getStatusCode()+"，count----："+str);
				log.info("----log-----Response："+arg0.getStatusLine().getStatusCode()+"，count----："+str);
				return str;
			}

			@Override
			public void onFailure(HttpUriRequest arg0, Exception arg) {
				log.error("error", arg);
			}

			@Override
			public void callBack(String url,Object message) {
				// TODO Auto-generated method stub
				
			}
		},requestProperty);
		return resStr;
	}
	
	public Object httpPost(String url, Map<String, String> head, DealResponse callback) {
		HttpPost httpPost = new HttpPost(url);
		if (null != head) {
			for (Iterator<String> iterator = head.keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next();
				httpPost.addHeader(key, head.get(key));
			}
		}
		RequestConfig config = RequestConfig.DEFAULT;
		httpPost.setConfig(config);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		HttpEntity httpEntity = new StringEntity(new Gson().toJson(paramMap), "UTF-8");
		httpPost.setEntity(httpEntity);
		return this.execute(httpPost, callback);
	}
	
	public Object httpPost(String url, Map<String, Object> head, Map<String, Object> body, DealResponse callback) {
		HttpPost httpPost = new HttpPost(url);
		RequestConfig config = RequestConfig.DEFAULT;
		httpPost.setConfig(config);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("head", null == head ? new HashMap<String, Object>() : head);
		paramMap.put("body", null == body ? new LinkedHashMap<String, Object>() : body);
		HttpEntity httpEntity = new StringEntity(new Gson().toJson(paramMap), "UTF-8");
		httpPost.setEntity(httpEntity);
		return this.execute(httpPost, callback);
	}
	
	public Object httpPost(String url, Object param, DealResponse callback,Map<String, String> headMap) {
		HttpPost httpPost = new HttpPost(url);
		RequestConfig config = RequestConfig.DEFAULT;
		httpPost.setConfig(config);
		httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		httpPost.setHeader("Accept-Encoding","gzip, deflate");
		httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.9");
		httpPost.setHeader("Cache-Control","max-age=0");
		httpPost.setHeader("Connection","keep-alive");
		httpPost.setHeader("Host","music.163.com");
		httpPost.setHeader("Upgrade-Insecure-Requests","1");
		httpPost.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Mobile Safari/537.36");
		if(headMap==null){
			httpPost.setHeader("content-type", "application/json");
		}else{
			// 设置请求头
	        Set<Entry<String, String >> entrySet=headMap.entrySet();
	        // 循环遍历出
	        for(Iterator<Entry<String, String>> iterator= entrySet.iterator();iterator.hasNext();)
	        {
	            Entry<String, String> entry=iterator.next();
	            httpPost.setHeader(entry.getKey(),entry.getValue());
	        }
		}
		HttpEntity httpEntity = new StringEntity(param.toString(), "UTF-8");
		httpPost.setEntity(httpEntity);
		return this.execute(httpPost, callback);
	}
	
	protected Object execute(HttpUriRequest request, DealResponse callback) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = getHttpsClient();
			response = client.execute(request);
			return callback.onSuccess(response);
		} catch (ClientProtocolException e) {
			callback.onFailure(request, e);
			log.error(e.getMessage(), e.getCause());
		} catch (IllegalStateException e) {
			callback.onFailure(request, e);
			log.error(e.getMessage(), e.getCause());
		} catch (IOException e) {
			callback.onFailure(request, e);
			log.error(e.getMessage(), e.getCause());
		} catch (Exception e) {
			callback.onFailure(request, e);
			log.error(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != client) {
					client.close();
				}
			} catch (IOException e) {
				log.error("close error  " + e.getMessage(), e);
			}
		}
		return null;
	}
	
	private static TrustManager manager = new X509TrustManager() {

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	};
	private SSLConnectionSocketFactory socketFactory;

	private void enableSSL() throws KeyManagementException, NoSuchAlgorithmException {
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, new TrustManager[] { manager }, null);
		socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
	}
	
	public CloseableHttpClient getHttpsClient() throws Exception {
		enableSSL();
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
				.setExpectContinueEnabled(true)
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).setConnectionRequestTimeout(5000)
				//.setConnectTimeout(5000).setSocketTimeout(5000).build();
				.setConnectTimeout(30000).setSocketTimeout(30000).build();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connectionManager)
				.setDefaultRequestConfig(requestConfig).build();
		return client;
	}

	
	/**
	 * @param url
	 * @param callback
	 * @return
	 */
	public Object httpGet(String url, DealResponse callback,String requestProperty) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpGet httpGet = null;
		try {
			client = getHttpsClient();
			httpGet = new HttpGet(url);
			httpGet.setHeader("content-type", "application/json");
			if(requestProperty != null){
				httpGet.setHeader("Referer", requestProperty);
			}
			// 执行POST请求
			response = client.execute(httpGet);
			return callback.onSuccess(response);
		} catch (ClientProtocolException e) {
			callback.onFailure(httpGet, e);
		} catch (IOException e) {
			callback.onFailure(httpGet, e);
		} catch (Exception e) {
			callback.onFailure(httpGet, e);
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != client) {
					client.close();
				}
			} catch (IOException e) {
				log.error("---close error  " + e.getMessage(), e);
			}
		}
		return null;
	}
	
	public static void main(String[] arges){
		String url = "http://218.18.109.237:8610/opss_web/mock/formal/cancelReg_jksz/0";
		Map<String,Object> map = new HashMap<String,Object>();
		Object param = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:web='http://www.bsoft.com.cn/webservices/'><soapenv:Header/><soapenv:Body><web:MainWebInterface><web:MethordName>cancelReg</web:MethordName><web:PassWord>888</web:PassWord><web:UserId>1001</web:UserId><web:DbType>ORA</web:DbType><web:InXML><![CDATA[<request><params><cancelTime>2017-05-25 11:44:24</cancelTime><hisOrdNum>28364</hisOrdNum><cancelMode>7</cancelMode><psOrdNum>1000001477</psOrdNum><channel>JKSZ</channel><branchCode>1</branchCode><cancelReason></cancelReason></params></request>]]></web:InXML></web:MainWebInterface></soapenv:Body></soapenv:Envelope>";
		HttpUtil h = new HttpUtil();
		//String a = h.startInvoke(url, param);
		
		
		String ulr1="http://121.15.139.21:8888/AppService";
		
		ulr1 = ulr1+"/queryDeptList/2836/ny104250/2/-1";
		
		//String b = h.startInvoke(ulr1,param);
		
	}
}
