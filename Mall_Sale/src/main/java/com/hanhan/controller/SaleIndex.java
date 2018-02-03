package com.hanhan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SaleIndex {

	@RequestMapping("index")
	public String index(@CookieValue(value = "yh_nch", required = false) String yh_nch, HttpServletRequest request,
			ModelMap map){
		
		// Cookie[] cookies = request.getCookies();
		// for (int i = 0; i < cookies.length; i++) {
		// if (cookies[i].getName().equals("yh_nch")) {
		// yh_nch = cookies[i].getValue();
		// }
		// }
		//
		// try {
		// map.put("yh_nch", URLDecoder.decode(yh_nch, "utf-8"));
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return "sale_index";
	}

	
}
