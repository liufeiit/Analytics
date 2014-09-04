package analytics.core.util;

import java.nio.charset.Charset;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 生成App注册所需的访问Token.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年9月4日 下午8:28:40
 */
public class TokenUtils {

	private static final Log log = LogFactory.getLog(TokenUtils.class);

	private static final String SECRET = "analytics.core";
	private static final String TOKEN_SEP = ".";
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final String HMAC_256 = "HmacSHA256";
	
	public static String generate() {
		JSONObject claims = new JSONObject();
		claims.put("uuid", UUID.randomUUID().toString());
		claims.put("millis", System.currentTimeMillis());
		return sign(SECRET, encode(claims, SECRET));
	}

	static String encode(JSONObject claims, String secret) {
		String encodedHeader = commonHeader();
		String encodedClaims = encodeJSON(claims);
		String secureBits = new StringBuffer(encodedHeader).append(TOKEN_SEP).append(encodedClaims).toString();
		String sig = sign(secret, secureBits);
		return new StringBuffer(secureBits).append(TOKEN_SEP).append(sig).toString();
	}

	static String sign(String secret, String secureBits) {
		String result = null;
		try {
			Mac sha256_HMAC = Mac.getInstance(HMAC_256);
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(UTF_8), HMAC_256);
			sha256_HMAC.init(secret_key);
			byte sig[] = sha256_HMAC.doFinal(secureBits.getBytes(UTF_8));
			result = Base64.encodeBase64URLSafeString(sig);
		} catch (Exception e) {
			log.error("token sign Error.", e);
		}
		return result;
	}

	static String commonHeader() {
		JSONObject headerJSON = new JSONObject();
		headerJSON.put("typ", "JWT");
		headerJSON.put("alg", "HS256");
		return encodeJSON(headerJSON);
	}

	static String encodeJSON(JSONObject jsonData) {
		return Base64.encodeBase64URLSafeString(jsonData.toString().getBytes(UTF_8));
	}
}