package com.hanhan.service;

import java.util.List;
import java.util.Map;

import com.hanhan.bean.DETAIL_T_MALL_SKU;
import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.OBJECT_T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuListService {

	List<OBJECT_T_MALL_SKU> get_sku_list_by_class2(int class_2_id);

	List<OBJECT_T_MALL_SKU> get_sku_list_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value);

	DETAIL_T_MALL_SKU get_sku_detail(int sku_id);

	List<T_MALL_SKU> get_sku_list_by_spuid(int spu_id);




	

}
