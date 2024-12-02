package com.chldbwls.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
		// 암호화 환경
public class MD5HashingEncoder {
	
	// 필요한 것을 class에 저장하는 형태
	
	// md5를 통한 암호화 기능
	public static String encode(String message) {
		// static = 객체 생성 없이도 사용할 수 있음
		String result = "";
		try {
			MessageDigest messageDigest =  MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			// bytes가 된 메시지를 암호화하여 전달
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			for(int i = 0; i < digest.length; i++) {
//				여기에 누적해서 더한다			byte 값을 16진수로 변환하기 위한 값(하나하나를 얻어내는것)
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			// 인자로 받은 알고리즘이 없다면
			e.printStackTrace();
			return null;
		}
		
		return result;
		
	}

}
