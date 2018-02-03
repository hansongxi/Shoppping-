package com.hanhan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.T_MALL_ADDRESS;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;
import com.hanhan.mapper.AddressMapper;
@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public void insert_address(T_MALL_ADDRESS address) {
		// TODO Auto-generated method stub
		addressMapper.insert_address(address);
	}

	@Override
	public List<T_MALL_ADDRESS> get_address_by_user_id(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		 List<T_MALL_ADDRESS> address_user = addressMapper.select_address_by_user_id(user);
		
		return address_user;
	}

	@Override
	public T_MALL_ADDRESS get_addresses_by_id(int address_id) {
		// TODO Auto-generated method stub
		T_MALL_ADDRESS  address = addressMapper.select_addresses_by_id(address_id);
		return address ;
	}

	@Override
	public void updata_address(T_MALL_ADDRESS address) {
		// TODO Auto-generated method stub
		addressMapper.updata_address(address);
	}

	@Override
	public void delete_address(T_MALL_ADDRESS address) {
		// TODO Auto-generated method stub
		addressMapper.delete_address(address);
	}

}
