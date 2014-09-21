package analytics.web.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tulip.util.StringUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月9日 下午5:45:29
 */
public interface Static {
	
	Log log = LogFactory.getLog(Static.class);
	
	String ONLINE_USER = "online:user";
	
	String SELECTED_APP = "selected:app";

	Charset UTF_8 = Charset.forName("UTF-8");

	Charset GBK = Charset.forName("GBK");
	
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	public static class URLUtils {
		public static String encode(String src) {
			try {
				return URLEncoder.encode(src, "UTF-8");
			} catch (Exception e) {
				log.error("URLEncoder Error.", e);
			}
			return src;
		}
		
		public static String decode(String src) {
			try {
				return URLDecoder.decode(src, "UTF-8");
			} catch (Exception e) {
				log.error("URLDecoder Error.", e);
			}
			return src;
		}
	}
	
	public static class CharsetUtils {
		
		public static byte[] getBytes(String src, Charset charset) {
			if(StringUtil.isBlank(src)) {
				return new byte[]{};
			}
			return src.getBytes(charset);
		}
		
		public static String build(byte[] src, Charset charset) {
			if(src == null || src.length <= 0) {
				return "";
			}
			return new String(src, charset);
		}
		
		public static byte[] getUTF8Bytes(String src) {
			return getBytes(src, UTF_8);
		}
		
		public static String buildFromUTF8(byte[] src) {
			return build(src, UTF_8);
		}
	}
}