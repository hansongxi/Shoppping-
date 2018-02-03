package com.hanhan.mapper;

import java.util.List;

import com.hanhan.bean.T_MALL_ADDRESS;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;

public interface AddressMapper {
	
	List<T_MALL_ADDRESS> get_addresses_by_user_id(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS get_addresses_by_id(int address_id);

}
