package com.hanhan.mapper;

import java.util.Map;

import com.hanhan.bean.T_MALL_SKU;

public interface SkuMapper {

	void insert_sku(T_MALL_SKU sku);

	void insert_sku_attr_value(Map<Object, Object> map);

}
