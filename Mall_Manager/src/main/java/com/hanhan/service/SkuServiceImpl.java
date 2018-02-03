package com.hanhan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU_ATTR_VALUE;
import com.hanhan.mapper.SkuMapper;
@Service
public class SkuServiceImpl implements SkuService {
	
	@Autowired
	private SkuMapper skuMapper;



	@Override
	public void save_sku(T_MALL_SKU sku, List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value) {
		// TODO Auto-generated method stub
		skuMapper.insert_sku(sku);
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("sku_id", sku.getId());
		map.put("spu_id", sku.getShp_id());
		map.put("list_sku_attr_value", list_sku_attr_value);
		skuMapper.insert_sku_attr_value(map);
		
	}

}
