package com.hanhan.service;

import java.util.List;

import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuService {


	void save_sku(T_MALL_SKU sku, List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value);

}
