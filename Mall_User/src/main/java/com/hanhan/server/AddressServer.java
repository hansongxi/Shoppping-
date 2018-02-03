package com.hanhan.server;

import java.util.List;

import javax.jws.WebService;

import com.hanhan.bean.T_MALL_ADDRESS;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface AddressServer {
	
	//添加功能
	void add_address(T_MALL_ADDRESS address);
	//查询功能
	List<T_MALL_ADDRESS> get_addresses_by_user_id(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS get_addresses_by_id(int address_id);
	//修改
	void updata_address(T_MALL_ADDRESS address);
	//删除
	void delete_address(T_MALL_ADDRESS address);
}
