package com.hanhan.service;

import java.util.List;

import com.hanhan.bean.OBJECT_T_MALL_ATTR;
import com.hanhan.bean.OBJECT_T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU_ATTR_VALUE;

public interface AttrService {

	void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr);

	List<OBJECT_T_MALL_ATTR> get_attr_list(int class_2_id);


	List<Integer> get_list_value_id_by_attr_id(int attr_id);

	

}
