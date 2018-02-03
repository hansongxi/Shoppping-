package com.hanhan.mapper;

import java.util.List;
import java.util.Map;

import com.hanhan.bean.T_MALL_SHOPPINGCAR;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;

public interface ShoppingMapper {

	List<T_MALL_SHOPPINGCAR> select_shop_cart_by_userid(T_MALL_USER_ACCOUNT select_user);

	void inset_shop_cart(T_MALL_SHOPPINGCAR shopCart);

	void update_cart(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR);


}
