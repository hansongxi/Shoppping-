package com.hanhan.mapper;

import java.util.List;
import java.util.Map;

import com.hanhan.bean.OBJECT_T_MALL_ATTR;

public interface AttrMapper {

	void insert_attr(Map<String, Object> map);

	void insert_values(Map<String, Object> map2);

	List<OBJECT_T_MALL_ATTR> select_attr_list(int class_2_id);

	

}
