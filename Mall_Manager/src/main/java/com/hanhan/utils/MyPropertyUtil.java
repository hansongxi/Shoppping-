package com.hanhan.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertyUtil {
	public static String getMyProperty(String property, String key) {

		Properties properties = new Properties();
		//使用类加载器加载文件中的内容
		InputStream resourceAsStream = 
				MyPropertyUtil.class.getClassLoader().getResourceAsStream(property);
		try {
			//因为properties是k-v，所以load作用是根据key去value的值
			properties.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String path = properties.getProperty(key);
		return path;
	}
}
