package com.hanhan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanhan.bean.DETAIL_T_MALL_SKU;
import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.OBJECT_T_MALL_ATTR;
import com.hanhan.bean.OBJECT_T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU;
import com.hanhan.bean.T_MALL_SKU_ATTR_VALUE;
import com.hanhan.service.AttrService;
import com.hanhan.service.SkuListService;

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
		
		List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(class_2_id);
		
		List<OBJECT_T_MALL_SKU>  list_sku= skuListService.get_sku_list_by_class2(class_2_id);
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
	public String get_sku_list_by_attr(int class_2_id, MODEL_T_MALL_SKU_ATTR_VALUE list_av, ModelMap map) {
		// 调用属性检索的业务
		// List<OBJECT_T_MALL_SKU> list_sku =
		// skuListServiceInf.get_sku_list_by_attr(class_2_id,
		// list_av.getList_sku_av());

		// map.put("list_sku", list_sku);
		List<OBJECT_T_MALL_SKU> list_sku_attr = skuListService.get_sku_list_by_attr(class_2_id, list_av.getList_sku_attr_value());
		map.put("list_sku", list_sku_attr);
		return "sale_sku_list_inner";
	}

}
