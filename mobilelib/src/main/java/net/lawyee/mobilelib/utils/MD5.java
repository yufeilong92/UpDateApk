package net.lawyee.mobilelib.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 * @author wuzhu
 * @date 2013-12-17 上午10:01:27
 * @version $id$
 */
public class MD5
{
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };

	/**
	 * 生成md5
	 * 
	 * @param string
	 * @return
	 */
	public static String encode(String string)
	{
		if (StringUtil.isEmpty(string))
			return string;
		try
		{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			return bytesToHexString(digest.digest(string.getBytes()));
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static String bytesToHexString(byte[] bytes)
	{
		if (bytes == null || bytes.length == 0)
			return "";
		final char[] buf = new char[bytes.length * 2];

		byte b;
		int c = 0;
		for (int i = 0, z = bytes.length; i < z; i++)
		{
			b = bytes[i];
			buf[c++] = DIGITS[(b >> 4) & 0xf];
			buf[c++] = DIGITS[b & 0xf];
		}

		return new String(buf);
	}

	/*
	 public static String toMD5(String str) {
                        byte[] source = str.getBytes("ascii");
MessageDigest md = MessageDigest.getInstance("MD5");
md.update(source);
StringBuffer buf = new StringBuffer();
for (byte b : md.digest())
buf.append(String.format("%x", b & 0xff));//%02x
return buf.toString().toUpperCase(Locale.getDefault());
          }


+ (NSString *)md5:(NSString *)str {
    const char *cStr = [str UTF8String];
    unsigned char result[16];
    CC_MD5( cStr, strlen(cStr), result );
    return [NSString stringWithFormat:
            @"%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X",
            result[0], result[1], result[2], result[3],
            result[4], result[5], result[6], result[7],
            result[8], result[9], result[10], result[11],
            result[12], result[13], result[14], result[15]];
}

Android的结果：FFC6F526578D32DB4FE47970FBFEF8
ios的结果            FFC6F5265708D32DB40FE47970FBFEF8
	 */
}
