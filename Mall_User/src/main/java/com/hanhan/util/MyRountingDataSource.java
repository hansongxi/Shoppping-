package com.hanhan.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRountingDataSource extends AbstractRoutingDataSource {

	//设置公共方法；为了防止线程的共享对象的问题；每条线程用自己的threadLocal
	private static ThreadLocal<String> key = new ThreadLocal<String>();

	public static String getKey() {
		return key.get();
	}

	public static void setKey(String keys) {
		key.set(keys);
	}


	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return getKey();
	}

}
