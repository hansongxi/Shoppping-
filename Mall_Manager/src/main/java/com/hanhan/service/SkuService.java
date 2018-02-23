package com.hanhan.service;

import java.util.List;

import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.OBJECT_T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuService {


	void save_sku(T_MALL_SKU sku, List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value);

	List<OBJECT_T_MALL_SKU> get_sku_list_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_attr_value);

}
