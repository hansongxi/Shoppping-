package com.hanhan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanhan.bean.DETAIL_T_MALL_SKU;
import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.OBJECT_T_MALL_ATTR;
import com.hanhan.bean.OBJECT_T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU_ATTR_VALUE;
import com.hanhan.service.AttrService;
import com.hanhan.service.SkuListService;
import com.hanhan.utils.MyCacheUtil;

@Controller
public class SkuListController {
	
	@Autowired
	private AttrService attrService;
	
	@Autowired
	private SkuListService skuListService;
	
	@RequestMapping("goto_sku_detail")
	public String goto_sku_detail(int sku_id,int spu_id,ModelMap map) {
		//商品的详情属性的业务；首先确定要查询的数据，shxm_mch,shxzh_mch,spu,img
		//所以需要建立一个详细的DETAIL_T_MALL_SKU里面需要有值
		DETAIL_T_MALL_SKU obj_sku = skuListService.get_sku_detail(sku_id);
		List<T_MALL_SKU> list_sku = skuListService.get_sku_list_by_spuid(spu_id);
		map.put("obj_sku", obj_sku);
		map.put("list_sku", list_sku);
		return "sale_search_detail";
	}

	@RequestMapping("goto_sale_sku_list")
	public String goto_sku_list(int class_2_id,ModelMap map) {
		List<OBJECT_T_MALL_SKU>  list_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		//这里可以用redis中的缓存数据来替代
		String keys="class_2_"+class_2_id;
		MyCacheUtil.getMyListByKey(OBJECT_T_MALL_SKU.class, keys);
		//如果缓存中没有的话，就从数据库中查询
		if(list_sku==null||list_sku.size()==0) {
			list_sku= skuListService.get_sku_list_by_class2(class_2_id);
			//在数据库中查询出来之后会同步到redis中，之后还会用队列来排序
			MyCacheUtil.setMyListByKey(list_sku, keys);
		}
		List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(class_2_id);
		map.put("list_attr", list_attr);
		map.put("list_sku", list_sku);
		map.put("class_2_id", class_2_id);
		return "sale_sku_list";
	}
	
	
	/*@RequestMapping("get_sku_list_by_attr")
	public String get_sku_list_by_attr(int class_2_id,
					@RequestParam("attr_param[]") String [] attr_param,ModelMap map) {
//		Map<Object,Object> map = new HashMap<Object,Object>();
//		List<OBJECT_T_MALL_SKU> list_sku_attr = skuListService.get_sku_list_by_attr(map);
		
		return "sale_sku_list_inner";
	}*/
	@RequestMapping("get_sku_list_by_attr")
	public String get_sku_list_by_attr(int class_2_id, MODEL_T_MALL_SKU_ATTR_VALUE list_av, String order,ModelMap map) {
		// 调用属性检索的业务
		// List<OBJECT_T_MALL_SKU> list_sku =
		// skuListServiceInf.get_sku_list_by_attr(class_2_id,
		// list_av.getList_sku_av());
		List<OBJECT_T_MALL_SKU> list_sku= new ArrayList<OBJECT_T_MALL_SKU>();
		// map.put("list_sku", list_sku);
		
		List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value = list_av.getList_sku_attr_value();
		
		String new_key=" ";
		if(list_sku_attr_value!=null&&list_sku_attr_value.size()>0) {
			
			String[] keys = new String[list_sku_attr_value.size()];
			for (int i = 0; i < list_sku_attr_value.size(); i++) {
				String key = "attr_" + class_2_id + "_" + list_sku_attr_value.get(i).getShxm_id() + "_"
						+ list_sku_attr_value.get(i).getShxzh_id();
				keys[i] = key;
			}
			//数据库中查询
			// 调用属性检索的业务
			 new_key = MyCacheUtil.interKey(keys);
			list_sku = MyCacheUtil.getMyListByKey(OBJECT_T_MALL_SKU.class,new_key);
		}

		// mysql
		if (list_sku == null || list_sku.size() == 0) {
			list_sku = skuListService.get_sku_list_by_attr(class_2_id, list_av.getList_sku_attr_value(),order);

			// 同步缓存，消息队列的异步同步
			MyCacheUtil.setMyListByKey(list_sku, new_key);
		}
		map.put("list_sku", list_sku);
		return "sale_sku_list_inner";
	}

}
