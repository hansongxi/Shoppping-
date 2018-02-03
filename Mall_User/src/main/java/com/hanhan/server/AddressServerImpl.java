package com.hanhan.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hanhan.bean.T_MALL_ADDRESS;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;
import com.hanhan.service.AddressService;

public class AddressServerImpl implements AddressServer {
	
	@Autowired
	private AddressService addressService;
	
	@Override
	public void add_address(T_MALL_ADDRESS address) {
		// TODO Auto-generated method stub
		addressService.insert_address(address);
	}

	@Override
	public List<T_MALL_ADDRESS> get_addresses_by_user_id(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		List<T_MALL_ADDRESS> address_user_id = 
				addressService.get_address_by_user_id(user);
		return address_user_id;
	}

	@Override
	public T_MALL_ADDRESS get_addresses_by_id(int address_id) {
		T_MALL_ADDRESS address = 
				addressService.get_addresses_by_id(address_id);
		// TODO Auto-generated method stub
		return address;
	}

	@Override
	public void updata_address(T_MALL_ADDRESS address) {
		// TODO Auto-generated method stub
		addressService.updata_address(address);
	}

	@Override
	public void delete_address(T_MALL_ADDRESS address) {
		// TODO Auto-generated method stub
		addressService.delete_address(address);
	}


}
