package com.hanhan.server;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;
import com.hanhan.service.UserService;

public class UserServerImpl implements UserServer {
	
	@Autowired
	private UserService userService;

	@Override
	public T_MALL_USER_ACCOUNT login_user(T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT users = userService.select_user(user);
		
		return users;
	}
	
	@Override
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		T_MALL_USER_ACCOUNT users = userService.login2(user);
		return users;
	}

	@Override
	@Path("login3")
	@Consumes("application/x-www-form-urlencode")
	@Produces("application/json")
	@GET
	public String login3(@BeanParam T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT users = userService.select_user(user);
		return new Gson().toJson(users);
	}

	
	@Override
	public void register_user(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		userService.insert_user(user);
	}

	@Override
	public void update_user(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		userService.update_user(user);
	}

	@Override
	public void logout_user(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		userService.delete_user(user);
	}

	

	
}
