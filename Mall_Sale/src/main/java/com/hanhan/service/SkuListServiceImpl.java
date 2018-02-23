package com.hanhan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.DETAIL_T_MALL_SKU;
import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.OBJECT_T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU_ATTR_VALUE;
import com.hanhan.mapper.SkuListMapper;
@Service
public class SkuListServiceImpl implements SkuListService {
	
	@Autowired
	private SkuListMapper skuListMapper;

	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_list_by_class2(int class_2_id) {
		// TODO Auto-generated method stub
		return skuListMapper.select_sku_list_by_class2(class_2_id);
	}

	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_list_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av,String order) {
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
		map.put("order", order);

		List<OBJECT_T_MALL_SKU> list_sku = skuListMapper.select_sku_list_by_attr(map);
		return list_sku;
	}

	
	@Override
	public DETAIL_T_MALL_SKU get_sku_detail(int sku_id) {
		DETAIL_T_MALL_SKU sku_detail = skuListMapper.select_sku_detail(sku_id);
		
		return sku_detail;
	}

	@Override
	public List<T_MALL_SKU> get_sku_list_by_spuid(int spu_id) {
		List<T_MALL_SKU> sku_list = skuListMapper.select_sku_list_by_spuid(spu_id);
		return sku_list;
	}

}


	


