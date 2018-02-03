package com.hanhan.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanhan.bean.T_MALL_SHOPPINGCAR;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;
import com.hanhan.service.ShoppingCartService;
import com.hanhan.utils.MyJsonUtil;

@Controller
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	//添加到迷你购物车
	@RequestMapping("get_miniCart")
	public String get_miniCart(ModelMap map, HttpSession session,
			@CookieValue(value = "list_shopcart_cookies", required = false) String list_shopcart_cookies) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();

		if (user == null) {
			list_cart = MyJsonUtil.json_to_list(list_shopcart_cookies, T_MALL_SHOPPINGCAR.class);

		} else {
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");

		}
		map.put("list_cart", list_cart);
		map.put("sum", get_sum(list_cart));
		return "sale_miniCart_list_inner";
	}
	
	//改变状态
	@RequestMapping("change_cart_status")
	public String change_cart_status(ModelMap map,int sku_id,String shfxz,HttpServletResponse response,HttpSession session,
			@CookieValue(value="list_shopcart_cookies",required=false)String list_shopcart_cookies) {
		List<T_MALL_SHOPPINGCAR> list_cart  = new ArrayList<T_MALL_SHOPPINGCAR>();
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if(user==null) {
			//用户不存在，在cookie中取值
			list_cart = MyJsonUtil.json_to_list(list_shopcart_cookies, T_MALL_SHOPPINGCAR.class);
		}else {
			//用户存在，在session中取值
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
		}
		for (int i = 0; i < list_cart.size(); i++) {
			//看是否sku_id
			if(list_cart.get(i).getSku_id()==sku_id) {
				list_cart.get(i).setShfxz(shfxz);
				if (user == null) {
					// 添加cookie
					Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
					cookie.setMaxAge(60 * 60 * 24 * 7);
					response.addCookie(cookie);
				} else {
					// // 更新db
					shoppingCartService.update_cart(list_cart.get(i));
				}
			}
		}
		
		map.put("list_cart", list_cart);
		map.put("sum", get_sum(list_cart));
		return "sale_cart_list_inner";
	}

	//给购物车增加商品
	@RequestMapping("goto_cart_list")
	public String goto_cart_list(ModelMap map,HttpSession session,
			@CookieValue(value = "list_shopcart_cookies", required = false) String list_shopcart_cookies  ) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if(user==null) {
			//如果用户名为空，则购物车的商品应该是cookie中的商品
			list_cart = MyJsonUtil.json_to_list(list_shopcart_cookies, T_MALL_SHOPPINGCAR.class);
			
		}else {
			//如果用户名不为空，则从session中去数据
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
		}
		map.put("list_cart", list_cart);
		map.put("sum", get_sum(list_cart));
		return "sale_cart_list";
	}

	private BigDecimal get_sum(List<T_MALL_SHOPPINGCAR> list_cart) {
		BigDecimal sum = new BigDecimal("0");
		if(list_cart!=null) {
			for (int i = 0; i < list_cart.size(); i++) {
				if (list_cart.get(i).getShfxz().equals("1")) {
					sum = sum.add(new BigDecimal(list_cart.get(i).getHj() + ""));
				}
				
			}
			
		}

		return sum;
	}
	//添加购物车
	@RequestMapping("add_cart")
	public String add_cart(HttpSession session,T_MALL_SHOPPINGCAR shopCart,HttpServletResponse response,
				@CookieValue(value="list_shopcart_cookies",required=false)String list_shopcart_cookies) {
		//创建集合，把cart的值放到集合中
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		//1.判断用户是否登录
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
			if(user==null) {
				//2.判断cookie是否为空
				if(list_shopcart_cookies==null||list_shopcart_cookies.equals("")) {
					//3.如果为空的话，就直接添加到集合中
					list_cart.add(shopCart);
				}else {
					//4.判断是否有相同的值
					list_cart= MyJsonUtil.json_to_list(list_shopcart_cookies, T_MALL_SHOPPINGCAR.class);
					boolean b = if_new_cart(list_cart, shopCart);
					if (b) {
						// 未添加过
						list_cart.add(shopCart);

					} else {
						// 添加过，更新
						for (int i = 0; i < list_cart.size(); i++) {
							if (list_cart.get(i).getSku_id() == shopCart.getSku_id()) {
								list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + 1);
								list_cart.get(i).setHj(list_cart.get(i).getSku_jg() * list_cart.get(i).getTjshl());
							}
						}
					}
				}
				// 覆盖客户端cookie，更新浏览器本地cookie数据
				Cookie cookie = new Cookie("list_shopcart_cookies", MyJsonUtil.list_to_json(list_cart));
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);
			
		}else {
			//如果用户存在
			list_cart= (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
			if(list_cart==null) {
				//如果购物车中没有值
				shoppingCartService.add_shop_cart(shopCart);
				list_cart= new ArrayList<T_MALL_SHOPPINGCAR>();
				list_cart.add(shopCart);
				session.setAttribute("list_cart", list_cart);
			}else {
				//如果购物车中有值
				boolean b = if_new_cart(list_cart, shopCart);
				if (b) {
					// 未添加过
					shoppingCartService.add_shop_cart(shopCart);
					//session中的购物车要有主键，所以先添加，添加的时候返回主键
					list_cart.add(shopCart);
				} else {
					// 添加过，更新
					for (int i = 0; i < list_cart.size(); i++) {
						if (list_cart.get(i).getSku_id() == shopCart.getSku_id()) {
							list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + 1);
							list_cart.get(i).setHj(list_cart.get(i).getSku_jg() * list_cart.get(i).getTjshl());
							shoppingCartService.update_cart(list_cart.get(i));
						}
					}
				}
				
			}
		}
			return "sale_cart_success";
	}
	
	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR shopCart) {
		
		for (T_MALL_SHOPPINGCAR cart : list_cart) {
			if(cart.getSku_id()==shopCart.getSku_id()) {
				return false;
			}
		}
		return true;
	}
}
