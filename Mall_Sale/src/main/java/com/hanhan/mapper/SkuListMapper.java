package com.hanhan.mapper;

import java.util.List;
import java.util.Map;

import com.hanhan.bean.DETAIL_T_MALL_SKU;
import com.hanhan.bean.OBJECT_T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU;

public interface SkuListMapper {

	List<OBJECT_T_MALL_SKU> select_sku_list_by_class2(int class_2_id);

	List<OBJECT_T_MALL_SKU> select_sku_list_by_attr(Map<Object,Object> map);

	DETAIL_T_MALL_SKU select_sku_detail(int sku_id);

	List<T_MALL_SKU> select_sku_list_by_spuid(int spu_id);
}
