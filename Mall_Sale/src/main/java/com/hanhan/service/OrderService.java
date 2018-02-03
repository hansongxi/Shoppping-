package com.hanhan.service;

import com.hanhan.bean.OBJECT_T_MALL_ORDER;
import com.hanhan.bean.T_MALL_ADDRESS;

public interface OrderService {

	void save_order(T_MALL_ADDRESS address, OBJECT_T_MALL_ORDER order);

}
