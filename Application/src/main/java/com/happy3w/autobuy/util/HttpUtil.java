/**
 *
 */
package com.happy3w.autobuy.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.happy3w.autobuy.exp.SysException;

/**
 * http发送请求常用工具。
 * 
 * @version 2016年6月25日 下午3:32:34
 * @author Happy3W Cherry
 *
 */
@Component
public class HttpUtil {
	public static HttpUtil http = new HttpUtil();

	public HttpUtil getInstance() {
		return http;
	}

	public String sendGet(String url, String param) {
		String result = "";
		try {
			String urlName = url + "?" + param;

			URL U = new URL(urlName);
			URLConnection connection = U.openConnection();
			connection.connect();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e) {
			throw new SysException("sendGet Error", e);
		}
		return result;
	}

	public String sendPost(String url, String param) {
		String result = "";
		try {
			URL httpurl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			PrintWriter out = new PrintWriter(httpConn.getOutputStream());
			out.print(param);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e) {
			throw new SysException("sendPost Error", e);
		}
		return result;
	}

	/**
	 * 上传文件。
	 *
	 * @param urlStr
	 * @param textMap
	 * @param fileMap
	 * @param fileStream
	 * @return
	 */
	public String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap,
			InputStream fileStream) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------"; // boundary标识requestͷ���ϴ��ļ����ݵķָ���
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator<Map.Entry<String, String>> iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = entry.getKey();
					String inputValue = entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			// file
			if (fileMap != null) {
				Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = entry.getKey();
					String inputValue = entry.getValue();
					if (inputValue == null) {
						continue;
					}
					String filename = inputValue+".jpg";
					String contentType = "image/jpeg";

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(fileStream);
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读入文件
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			throw new SysException("formUpload Error", e);
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

}