package com.doudou.base.common.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.net.*;
import java.util.*;

/**
 * 
 * 生成页面SELECT标签HTML帮助类。<br/>
 * 实现背景：<br>
 * 现公司数据库ID使用32位UUID，大部份由HIBERNATE自动生成，但是如果不是由HIBERNATE自动生成，可以使用此类生成<br>
 */
public class GUIDHelper {
	private static Random myRand;
	private static SecureRandom mySecureRand;
	private static String s_id;

	static {
		mySecureRand = new SecureRandom();
		long secureInitializer = mySecureRand.nextLong();
		myRand = new Random(secureInitializer);
		try {
			s_id = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}


	public static String genRandomGUID() {
		return genRandomGUID(false);
	}

	public static String genRandomGUID(boolean secure) {
		String valueBeforeMD5 = "";
		String valueAfterMD5 = "";

		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e.getMessage());
			return valueBeforeMD5;
		}

		long time = System.currentTimeMillis();
		long rand = 0;
		if (secure) {
			rand = mySecureRand.nextLong();
		} else {
			rand = myRand.nextLong();
		}
		sbValueBeforeMD5.append(s_id);
		sbValueBeforeMD5.append(":");
		sbValueBeforeMD5.append(Long.toString(time));
		sbValueBeforeMD5.append(":");
		sbValueBeforeMD5.append(Long.toString(rand));
		valueBeforeMD5 = sbValueBeforeMD5.toString();
		md5.update(valueBeforeMD5.getBytes());
		byte[] array = md5.digest();
		String strTemp = "";
		for (int i = 0; i < array.length; i++) {
			strTemp = (Integer.toHexString(array[i] & 0XFF));
			if (strTemp.length() == 1) {
				valueAfterMD5 = valueAfterMD5 + "0" + strTemp;
			} else {
				valueAfterMD5 = valueAfterMD5 + strTemp;
			}
		}

		return valueAfterMD5.toUpperCase();
	}

	public static void main(String args[]) {
		for (int i = 1; i < 10; i++) {
			System.out.println(Integer.toString(i) + " : " + genRandomGUID());
		}
	}

}
