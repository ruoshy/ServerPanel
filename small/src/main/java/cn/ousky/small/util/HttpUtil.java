package cn.ousky.small.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
	private final static String ENCODE = "utf-8";

	public static String sendMessage(String phone, String message) {
		InputStreamReader isr = null;
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL url = new URL("http://api.smsbao.com/sms?u=5342393&p=0a0158fb1c775fdd01c89b582a5cd359&m=" + phone
					+ "&c=" + getUrlString("【咸鱼商城】您的验证码为" + message + "，打死不要告诉任何人哦！"));
			URLConnection urlConnection = url.openConnection();
			urlConnection.setAllowUserInteraction(false);
			isr = new InputStreamReader(url.openStream());
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String getString(String str) {
		if (str == null)
			return "";

		String result = "";
		try {
			result = java.net.URLDecoder.decode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getUrlString(String str) {
		if (str == null)
			return "";

		String result = "";
		try {
			result = java.net.URLEncoder.encode(str, ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
