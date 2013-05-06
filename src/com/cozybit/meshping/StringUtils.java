package com.cozybit.meshping;

import java.io.UnsupportedEncodingException;

class StringUtils {
	
	public static byte[] toUtf8(String str)
	{
		byte[] bytes = null;
		
		try {
			bytes = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		
		return bytes;
	}
	
	public static String fromUtf8(byte[] bytes)
	{
		String str = null;
		
		try {
			str = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return str;
	}

}
