package com.hanhan.service;

import com.hanhan.bean.T_MALL_USER_ACCOUNT;

public interface UserService {

	T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);

	T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user);
	
	
	
	void insert_user(T_MALL_USER_ACCOUNT user);

	void update_user(T_MALL_USER_ACCOUNT user);

	void delete_user(T_MALL_USER_ACCOUNT user);

	

	

}
