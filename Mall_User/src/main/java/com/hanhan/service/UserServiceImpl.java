package com.hanhan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.T_MALL_USER_ACCOUNT;
import com.hanhan.mapper.UserMapper;
import com.hanhan.util.MyRountingDataSource;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		// 使用数据源1
		MyRountingDataSource.setKey("1");
		T_MALL_USER_ACCOUNT users = userMapper.select_user(user);
		return users;
	}
	
	@Override
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		MyRountingDataSource.setKey("2");
		T_MALL_USER_ACCOUNT users = userMapper.select_user(user);
		return users;
	}


	@Override
	public void insert_user(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		userMapper.insert_user(user);
	}

	@Override
	public void update_user(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		userMapper.update_user(user);
	}

	@Override
	public void delete_user(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		userMapper.delete_user(user);
	}

	
}
