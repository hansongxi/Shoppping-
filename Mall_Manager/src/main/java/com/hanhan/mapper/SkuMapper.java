package com.hanhan.mapper;

import java.util.List;
import java.util.Map;

import com.hanhan.bean.OBJECT_T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU;

public interface SkuMapper {

	void insert_sku(T_MALL_SKU sku);

	void insert_sku_attr_value(Map<Object, Object> map);

	List<OBJECT_T_MALL_SKU> select_sku_list_by_attr(Map<Object, Object> map);

}
