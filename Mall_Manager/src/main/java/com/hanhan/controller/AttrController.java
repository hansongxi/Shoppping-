package com.hanhan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanhan.bean.MODEL_T_MALL_VALUE;
import com.hanhan.bean.OBJECT_T_MALL_ATTR;
import com.hanhan.service.AttrService;

@Controller
public class AttrController {
	
	@Autowired
	private AttrService attrService;
	
	@RequestMapping("get_attr_list_inner")
	public String get_attr_list(int class_2_id,ModelMap map) {
		List<OBJECT_T_MALL_ATTR> list_attr =  
				attrService.get_attr_list(class_2_id);
		
		map.put("list_attr", list_attr);
		return "manager_attr_list_inner";
	}
	@ResponseBody
	@RequestMapping("get_attr_list_inner_json")
	public List<OBJECT_T_MALL_ATTR> get_attr_list_inner_json(int class_2_id,ModelMap map) {
		List<OBJECT_T_MALL_ATTR> list_attr =  
				attrService.get_attr_list(class_2_id);
		
		map.put("list_attr", list_attr);
		return list_attr;
	}
	
	@RequestMapping("goto_attr_list")
	public String goto_attr_list() {
		
		return "manager_attr_get_list";
	}
	
	
	@Autowired
	private AttrService attrSercice;
	
	@RequestMapping("/goto_attr")
	public String goto_attr() {
		return "manager_attr";
	}
	@RequestMapping("/goto_attr_add")
	public String goto_attr_add() {
		return "manager_attr_add";
	}
	@RequestMapping("/save_attr")
	public String save_attr(int flbh2,MODEL_T_MALL_VALUE list_attr) {
		
		attrSercice.save_attr(flbh2,list_attr.getList_attr());
		
		return "redirect:/goto_attr_add.do";
	}
}
