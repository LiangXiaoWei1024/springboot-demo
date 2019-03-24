package com.imooc.utils;

public class StringUtils {

	public static boolean isNotNull(String str) {
		return !fund(str);
	}
	public static boolean isNull(String str) {
		return fund(str);
	}
	
	
	public static boolean fund(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
}
