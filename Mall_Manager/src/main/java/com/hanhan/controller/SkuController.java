package com.hanhan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanhan.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.hanhan.bean.OBJECT_T_MALL_ATTR;
import com.hanhan.bean.T_MALL_PRODUCT;
import com.hanhan.bean.T_MALL_SKU;
import com.hanhan.service.AttrService;
import com.hanhan.service.SkuService;
import com.hanhan.service.SpuService;

@Controller
public class SkuController {
	
	@Autowired
	private AttrService attrService;
	
	@Autowired
	private SpuService spuService;
	@Autowired
	private SkuService skuService;
	
	@RequestMapping("/goto_sku")
	public String goto_sku() {
		return "manager_sku";
	}
	@RequestMapping("/goto_sku_add")
	public String goto_sku_add() {
		return "manager_sku_add";
	}
	
	@RequestMapping("get_attr_list")
	public String get_attr_list(int class_2_id,ModelMap map) {
		List<OBJECT_T_MALL_ATTR> list_attr =  attrService.get_attr_list(class_2_id);
		
		map.put("list_attr", list_attr);
		return "manager_sku_attr_list_inner_html";
	}
	
	@ResponseBody
	@RequestMapping("get_spu_list")
	public List<T_MALL_PRODUCT> get_spu_list(int class_2_id,int pp_id) {
		List<T_MALL_PRODUCT> spu_list = 
				spuService.get_spu_list(class_2_id,pp_id);
		return spu_list;
	}
	
	
	@RequestMapping("save_sku")
	public String save_sku(T_MALL_SKU sku,MODEL_T_MALL_SKU_ATTR_VALUE list_sku_attr_value) {
		skuService.save_sku(sku,list_sku_attr_value.getList_sku_attr_value());
		return "redirect:/goto_sku_add.do";
	}	
}
