package com.hanhan.mapper;

import java.util.List;

import com.hanhan.bean.T_MALL_ADDRESS;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;

public interface AddressMapper {

	void insert_address(T_MALL_ADDRESS address);

	List<T_MALL_ADDRESS> select_address_by_user_id(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS select_addresses_by_id(int address_id);

	void updata_address(T_MALL_ADDRESS address);

	void delete_address(T_MALL_ADDRESS address);

}
