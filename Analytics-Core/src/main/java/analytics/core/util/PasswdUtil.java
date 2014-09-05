package analytics.core.util;

import java.nio.charset.Charset;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月5日 上午11:28:42
 */
public class PasswdUtil {

	private static final Log log = LogFactory.getLog(TokenUtils.class);

	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final byte[] SECRET = "analytics.core".getBytes(UTF_8);
	private static final String HMAC_256 = "HmacSHA256";
	
	public static String signPwsswd(String passwd) {
		String result = null;
		try {
			Mac sha256_HMAC = Mac.getInstance(HMAC_256);
			SecretKeySpec secret_key = new SecretKeySpec(SECRET, HMAC_256);
			sha256_HMAC.init(secret_key);
			byte sig[] = sha256_HMAC.doFinal(passwd.getBytes(UTF_8));
			result = Base64.encodeBase64URLSafeString(sig);
		} catch (Exception e) {
			log.error("Passwd sign Error.", e);
		}
		return result;
	}
}