package com.hanhan.server;

import javax.jws.WebService;

import com.hanhan.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface UserServer {

	/**
	 * 登录功能
	 * @param user
	 * @return
	 */
	public T_MALL_USER_ACCOUNT login_user(T_MALL_USER_ACCOUNT user);
	
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user);
	
	public String login3(T_MALL_USER_ACCOUNT user);
	/**
	 * 注册功能
	 * @param user
	 * @return
	 */
	public void register_user(T_MALL_USER_ACCOUNT user);
	
	/**
	 * 更新功能
	 * @param user
	 * @return
	 */
	public void update_user(T_MALL_USER_ACCOUNT user);
	
	/**
	 * 注销功能
	 * @param user
	 * @return
	 */
	public void logout_user(T_MALL_USER_ACCOUNT user);
}
