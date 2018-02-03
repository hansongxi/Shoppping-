package com.hanhan.service;

import java.util.List;

import com.hanhan.bean.T_MALL_SHOPPINGCAR;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;

public interface ShoppingCartService {

	List<T_MALL_SHOPPINGCAR> get_shop_cart_by_userid(T_MALL_USER_ACCOUNT select_user);

	void add_shop_cart(T_MALL_SHOPPINGCAR shopCart);

	void update_cart(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR);



	
}
