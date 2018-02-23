package com.hanhan.mapper;

import java.util.List;
import java.util.Map;

import com.hanhan.bean.OBJECT_T_MALL_FLOW;
import com.hanhan.bean.OBJECT_T_MALL_ORDER;
import com.hanhan.bean.T_MALL_FLOW;
import com.hanhan.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	void insert_order(Map<String, Object> map);

	void insert_flow(T_MALL_FLOW t_MALL_FLOW);

	void insert_order_info(Map<String, Object> info_map);

	void deleteCart(List<Integer> list_cart_id);

	int select_count(int sku_id);

	long select_kc(int sku_id);

	long select_kc_for_update(int sku_id);
	
	void update_kc(T_MALL_ORDER_INFO t_MALL_ORDER_INFO);

	void update_flow(OBJECT_T_MALL_FLOW flow);

	void update_order(OBJECT_T_MALL_ORDER order);

}
