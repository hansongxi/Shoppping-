package com.hanhan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.OBJECT_T_MALL_ATTR;
import com.hanhan.bean.T_MALL_PRODUCT;
import com.hanhan.mapper.AttrMapper;
import com.hanhan.mapper.SpuMapper;

@Service
public class AttrServiceImpl implements AttrService {
	
	@Autowired
	private AttrMapper attrMapper;
	@Override
	public void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		for (int i = 0; i < list_attr.size(); i++) {
			
			OBJECT_T_MALL_ATTR attr = list_attr.get(i);
			if(attr.getShxm_mch()!=null) {
				
				map.put("attr", attr);
				map.put("flbh2", flbh2);
				attrMapper.insert_attr(map);
				Map<String,Object> map2 = new HashMap<String,Object>();
				map2.put("attr_id", attr.getId());
				map2.put("list_value", attr.getList_value());
				attrMapper.insert_values(map2);
			}
		}
	}
	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_list(int class_2_id) {
		
		return attrMapper.select_attr_list(class_2_id);
	}
	

}
