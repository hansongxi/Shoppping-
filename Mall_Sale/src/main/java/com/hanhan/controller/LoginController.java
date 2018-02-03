package com.hanhan.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanhan.bean.T_MALL_SHOPPINGCAR;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;
import com.hanhan.mapper.LoginMapper;
import com.hanhan.server.UserServer;
import com.hanhan.service.ShoppingCartService;
import com.hanhan.utils.MyJsonUtil;

@Controller
public class LoginController {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private UserServer userServer;
	
	// 登录功能
	@RequestMapping("goto_sale_login")
	public String goto_login() {

		return "sale_login";
	}
	// 注销功能

	@RequestMapping("goto_sale_logout")
	public String goto_sale_logout(HttpSession session) {
		// 注销session
		session.invalidate();
		return "redirect:/goto_sale_login.do";
	}

	// 验证登录功能，并写入session中，
	// 未登录时，要想显示的数据，可以写在cookie中
	@RequestMapping("login")
	public String login(String dataSource_type,@CookieValue(value = "list_shopcart_cookies", required = false) String list_shopcart_cookies,
			HttpServletResponse response, HttpSession session, T_MALL_USER_ACCOUNT user) {
		// 后期可以用webservice来调用user的服务，这里只是模拟下，直接用loginmapper来替代
		//T_MALL_USER_ACCOUNT select_user = loginMapper.select_login(user);
		/*webService的调用
		 * T_MALL_USER_ACCOUNT select_user =null;
		if(dataSource_type.equals("1")) {
			select_user  =  userServer.login_user(user);
		}else {
			
		}select_user  =  userServer.login2(user);*/
		
		T_MALL_USER_ACCOUNT select_user = loginMapper.select_login(user);
		// 验证用户名和密码
		if (select_user == null) {
			return "goto_sale_login";
		} else {
			session.setAttribute("user", select_user);
			// 向cookie写入用户登录信息
			String yh_nch = select_user.getYh_nch();
			String yh_mch = select_user.getYh_mch();

			Cookie cookie = null;
			Cookie cookie2 = null;
			try {
				// URLEncoder：用来编码和解码
				cookie = new Cookie("yh_nch", URLEncoder.encode("周润发", "utf-8"));
				cookie2 = new Cookie("yh_mch", URLEncoder.encode(yh_mch, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cookie.setMaxAge(60 * 60 * 24);
			cookie2.setMaxAge(60 * 60 * 24);
			// cookie.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie2);

		}
		// 合并购物车
		// 需要的数据：list_shopcart_cookies,list_cart_db,select_user,response,session)
		List<T_MALL_SHOPPINGCAR> list_cart_db = shoppingCartService.get_shop_cart_by_userid(select_user);
		merge_cart(list_shopcart_cookies, list_cart_db, select_user, response, session);

		return "redirect:/index.do";
	}

	private void merge_cart(String list_shopcart_cookies, List<T_MALL_SHOPPINGCAR> list_cart_db, T_MALL_USER_ACCOUNT user,
			HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		// 1.判断数据库中是否为空
		if (list_cart_db == null || list_cart_db.size() == 0) {
			// 如果为空，判断cookies是否为空
			if (StringUtils.isBlank(list_shopcart_cookies)) {
				// 结束，直接在最后写入session
			} else {
				list_cart = MyJsonUtil.json_to_list(list_shopcart_cookies, T_MALL_SHOPPINGCAR.class);
				for (int i = 0; i < list_cart.size(); i++) {
					list_cart.get(i).setYh_id(user.getId());
					shoppingCartService.add_shop_cart(list_cart.get(i));
				}
			}
		} else {
			// 如果db数据库不为空
			if (StringUtils.isBlank(list_shopcart_cookies)) {
				// 结束，直接在最后写入session
			} else {
				list_cart = MyJsonUtil.json_to_list(list_shopcart_cookies, T_MALL_SHOPPINGCAR.class);
				for (int i = 0; i < list_cart.size(); i++) {
					boolean b = if_new_cart(list_cart_db,list_cart.get(i));
					if(b) {
						//如果是新的话，就直接插入数据ku
						list_cart.get(i).setYh_id(user.getId());
						shoppingCartService.add_shop_cart(list_cart.get(i));
					}else {
						//如果不是新的话，就覆盖原来的
						list_cart.get(i).setYh_id(user.getId());
						shoppingCartService.update_cart(list_cart.get(i));
					}
				}
			}
		}
		// 清空cookie，同步session
		response.addCookie(new Cookie("list_shopcart_cookies", ""));

		session.setAttribute("list_cart", shoppingCartService.get_shop_cart_by_userid(user));
	}

	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR shopCart) {

		for (T_MALL_SHOPPINGCAR cart : list_cart) {
			if (cart.getSku_id() == shopCart.getSku_id()) {
				return false;
			}
		}
		return true;
	}

}
