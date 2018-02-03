package com.hanhan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.T_MALL_SHOPPINGCAR;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;
import com.hanhan.mapper.ShoppingMapper;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	private ShoppingMapper shoppingMapper;


	@Override
	public List<T_MALL_SHOPPINGCAR> get_shop_cart_by_userid(T_MALL_USER_ACCOUNT select_user) {
		
		List<T_MALL_SHOPPINGCAR> list_shop_cart = 
				shoppingMapper.select_shop_cart_by_userid(select_user);
		return list_shop_cart;
	}


	


	@Override
	public void add_shop_cart(T_MALL_SHOPPINGCAR shopCart) {
		// TODO Auto-generated method stub
		shoppingMapper.inset_shop_cart(shopCart);
	}





	@Override
	public void update_cart(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR) {
		// TODO Auto-generated method stub
		shoppingMapper.update_cart(t_MALL_SHOPPINGCAR);
	}
}
