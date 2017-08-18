package createPreOut;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionUtil {
	private static String sessionId; 	
//	private static final Logger log = LoggerFactory.getLogger(HttpConnectionUtil.class);
	
	/**
	 * 发送post数据
	 *
	 * @param path
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequestByForm(String path, String params) throws Exception {
//		log.warn("=============================发送地址："+path);
//		log.warn("=============================发送内容："+params);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");// 提交模式
		// conn.setConnectTimeout(10000);//连接超时 单位毫秒
		// conn.setReadTimeout(2000);//读取超时 单位毫秒
		conn.setUseCaches(false);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
//		if(sessionId==null){
//			String session_value = conn.getHeaderField("Set-Cookie");  
//			sessionId = session_value.split(";")[0]; 
//		}else{
//			conn.setRequestProperty("Cookie", "720EFE58E23FE8495A5EAA28E54E9E67"); 
//		}
		
		// conn.setChunkedStreamingMode(5);
		conn.setDoOutput(true);// 是否输入参数
		byte[] bypes = params.toString().getBytes();
		conn.getOutputStream().write(bypes);// 输入参数
		InputStream inStream = conn.getInputStream();
		return readInputStream(inStream,"UTF-8");
	}
	/**
	 * 发送post数据
	 *
	 * @param path
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequestByForm(String path, String params,String charset) throws Exception {
//		log.info("=============================发送地址："+path);
//		log.info("=============================发送内容："+params);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setRequestMethod("POST");// 提交模式
		// conn.setConnectTimeout(10000);//连接超时 单位毫秒
		// conn.setReadTimeout(2000);//读取超时 单位毫秒
		conn.setUseCaches(false);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		// conn.setChunkedStreamingMode(5);
		conn.setDoOutput(true);// 是否输入参数
		byte[] bypes = params.getBytes();
		conn.getOutputStream().write(bypes);// 输入参数
		InputStream inStream = conn.getInputStream();
		return readInputStream(inStream,charset);
	}
	/**
	 * 读取返回的结果
	 *@param inStream
	 *@return
	 *@throws Exception
	 */
	public static String readInputStream(InputStream inStream,String charset) throws Exception {
		StringBuffer buffer = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(inStream,charset);
		BufferedReader br = new BufferedReader(isr);
		String content = "";
		while ((content = br.readLine()) != null) {
			buffer.append(content);
		}
		br.close();
		isr.close();
//		log.warn("=============================返回内容："+buffer.toString());
		return buffer.toString();
	}
}
