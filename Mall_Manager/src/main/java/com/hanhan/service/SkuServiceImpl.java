package com.hanhan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.OBJECT_T_MALL_SKU;
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
	
	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_list_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av) {
		// 根据属性集合动态拼接属性过滤的sql语句
		StringBuffer sql = new StringBuffer();

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("class_2_id", class_2_id);
		if (list_av == null || list_av.size() == 0) {
			map.put("sql", "");
		} else {
			// 动态拼接sql的方法
			
			sql.append(" and sku.id in (select skuid0 from ");
			

			for (int i = 0; i < list_av.size(); i++) {
				//循环子查询
				sql.append(" (select sku_id skuid"+i+" FROM t_mall_sku_attr_value "
						+ "WHERE shxm_id = "+list_av.get(i).getShxm_id()+" "
								+ " AND shxzh_id ="+list_av.get(i).getShxzh_id()+") sku"+i+" ");
				if(list_av.size()>1&&i<(list_av.size()-1)){
					sql.append(" , ");
					// 判断什么时候加入,
				}
			}

			// where
			if (list_av.size() > 1) {
				sql.append(" where ");
			}
			
			for (int j = 0; j < list_av.size(); j++) {
				// 循环连接条件，sku0=sku1 
				if(j<list_av.size()-1) {
					sql.append(" sku"+j+".skuid"+j+"=sku"+(j+1)+".skuid"+(j+1)+" ");
				}
				if(list_av.size()>2 && j<(list_av.size()-2)){
					// 判断什么时候加入and
					sql.append(" and ");
				}
			}

			sql.append(" ) ");
			map.put("sql", sql.toString());
		}

		List<OBJECT_T_MALL_SKU> list_sku = skuMapper.select_sku_list_by_attr(map);
		return list_sku;
	}

}
