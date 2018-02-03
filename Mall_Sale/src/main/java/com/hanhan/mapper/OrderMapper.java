package com.hanhan.mapper;

import java.util.List;
import java.util.Map;

import com.hanhan.bean.T_MALL_FLOW;

public interface OrderMapper {

	void insert_order(Map<String, Object> map);

	void insert_flow(T_MALL_FLOW t_MALL_FLOW);

	void insert_order_info(Map<String, Object> info_map);

	void deleteCart(List<Integer> list_cart_id);

}
