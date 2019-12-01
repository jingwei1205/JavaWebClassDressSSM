package com.lifeng.util;

public class Unicode {

	// ת��Ϊunicode����
	public String toUnicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			unicode.append("\\u" +Integer.toHexString(c));
		}
		return unicode.toString();
	}
	// ת��Ϊstring
	public String toString(String unicode) {
		StringBuffer string = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			int data = Integer.parseInt(hex[i], 16);// ת����ÿһ�������
			string.append((char) data);// ׷�ӳ�string
		}
		return string.toString();
	}
	
	public String toCookieUnicode(String string) {
		char []ss = string.toCharArray();
		String cu = new String();
		for(int i = 0; i < ss.length; i++) {
			if(i == ss.length-1) {
				cu +=  Integer.toHexString(ss[i]);
			}else {
				cu +=  Integer.toHexString(ss[i]) + "&";
			}
			
		}
		return cu;
	}
	
	public String toCookieString(String unicode) {
		String[] cs = unicode.split("&");
		String string = new String();
		for(int i = 0; i < cs.length; i++) {
			try {
				string += (char) Integer.parseInt(cs[i], 16);
			}catch (Exception e) {
			}
		}
		return string;
	}
	
	// ����url�ַ���
	public String toUrlString(String unicode) {
		String[] sa = unicode.split("u");
		String string  = new String();
		for(int i = 0; i < sa.length; i ++) {
			try {
				string += (char) Integer.parseInt(sa[i],16);
			}catch (Exception e) {
			}
		}
		return string;
	}
}
