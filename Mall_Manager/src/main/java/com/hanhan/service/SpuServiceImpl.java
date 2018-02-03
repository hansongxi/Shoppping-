package com.hanhan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.T_MALL_PRODUCT;
import com.hanhan.mapper.SpuMapper;

@Service
public class SpuServiceImpl implements SpuService {
	
	@Autowired
	private SpuMapper spuMapper;

	@Override
	public void save_spu(T_MALL_PRODUCT spu, List<String> list_image) {
		// TODO Auto-generated method stub
		//先插入product
		spu.setShp_tp(list_image.get(0));
		spuMapper.insert_spu(spu);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("spu_id", spu.getId());
		map.put("list_image",list_image);
		spuMapper.insert_spu_img(map);
	}

	@Override
	public List<T_MALL_PRODUCT> get_spu_list(int class_2_id, int pp_id) {
		// TODO Auto-generated method stub
		
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("class_2_id", class_2_id);
		map.put("pp_id", pp_id);
		return spuMapper.select_spu_list(map);
	}

}
