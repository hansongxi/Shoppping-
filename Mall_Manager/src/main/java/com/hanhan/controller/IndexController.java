package com.hanhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index(String url,String title,ModelMap map) {
		map.put("url", url);
		map.put("title", title);
		return "manager_index";
	}
}
