package com.hanhan.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hanhan.bean.OBJECT_T_MALL_FLOW;
import com.hanhan.bean.OBJECT_T_MALL_ORDER;
import com.hanhan.bean.T_MALL_ADDRESS;
import com.hanhan.bean.T_MALL_FLOW;
import com.hanhan.bean.T_MALL_ORDER_INFO;
import com.hanhan.bean.T_MALL_SHOPPINGCAR;
import com.hanhan.bean.T_MALL_USER_ACCOUNT;
import com.hanhan.mapper.AddressMapper;
import com.hanhan.server.AddressServer;
import com.hanhan.service.OrderService;
import com.hanhan.service.ShoppingCartService;
@Controller
@SessionAttributes("order")
//把order放在session中，在本页面都有用
public class OrderController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AddressServer addressServer;
	
	@Autowired
	private AddressMapper addressMapper;

	@RequestMapping("check_order")
	public String goto_check_cart(ModelMap map,HttpSession session) {
		//当结算的时候，用户必须是登录状态
		T_MALL_USER_ACCOUNT user = 
				(T_MALL_USER_ACCOUNT) session.getAttribute("user");
		
		if(user==null) {
			//跳转到登录页面
			return "redirect:/goto_login.do";
		}else {
			//如果是有用户，根据session取出购物车的东西
			List<T_MALL_SHOPPINGCAR> list_cart = 
					(List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
			//订单业务
			OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
			order.setZje(get_sum(list_cart));
			order.setYh_id(user.getId());
			order.setJdh(0);
			//拆单：是根据购物车中的库存地址进行拆单
			//判断有几个库存地址;set是无序不重复的
			Set<String> set_kcdz = new HashSet<String>();
			
			for (int i = 0; i < list_cart.size(); i++) {
				//选中选中的商品
				if(list_cart.get(i).getShfxz().equals("1")) {
					set_kcdz.add(list_cart.get(i).getKcdz());
				}
			}
			//根据库存地址进行商品的order_info的分类，每个分类都会对应一个flow，所以是一个集合list_flow
			List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<OBJECT_T_MALL_FLOW>();
				//set的循环
			Iterator<String> iterator = set_kcdz.iterator();
			while(iterator.hasNext()) {
				String kcdz = iterator.next();
				//每次while的循环都会生成一个flow
				OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
				flow.setPsfsh("硅谷快递");
				flow.setMqdd("商品未出库");
				flow.setYh_id(user.getId());
				//循环购物车，把库存相等的订单放到一个flow中,这个flow是个list_info的类
				List<T_MALL_ORDER_INFO> list_info = 
						new ArrayList<T_MALL_ORDER_INFO>();
				for (int i = 0; i < list_cart.size(); i++) {
					if(list_cart.get(i).getShfxz().equals("1")
							&&list_cart.get(i).getKcdz().equals(kcdz)) {
						//每次循环都会生成一个order_info
						T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();
						info.setGwch_id(list_cart.get(i).getId());
						info.setShp_tp(list_cart.get(i).getShp_tp());
						info.setSku_id(list_cart.get(i).getSku_id());
						info.setSku_jg(list_cart.get(i).getSku_jg());
						info.setSku_kcdz(kcdz);
						info.setSku_mch(list_cart.get(i).getSku_mch());
						info.setSku_shl(list_cart.get(i).getTjshl());
						
						//如果是的话就存入list_info集合中,并在while的一次循环中放入flow中
						list_info.add(info);
					}
				}
				//每次循环的时候会把list_info的集合放入flow中保存
				flow.setList_info(list_info);
				list_flow.add(flow);
			}
			//把list_flow放到order中
			order.setList_flow(list_flow);
			map.put("order", order);
			//调用接口的时候
			//map.put("list_address", addressServer.get_addresses_by_user_id(user));
			map.put("list_address", addressMapper.get_addresses_by_user_id(user));
		}
		return "sale_check_order";
	}
	//总金额的判断
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
	
	//修改订单
	@RequestMapping("save_order")
	public String save_order(int address_id,HttpSession session,
			@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		T_MALL_ADDRESS address = addressMapper.get_addresses_by_id(address_id);
		orderService.save_order(address,order);
		//购物车的任务发生了改变
		T_MALL_USER_ACCOUNT user = 
				(T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = shoppingCartService.get_shop_cart_by_userid(user);
		session.setAttribute("list_cart", list_cart);
		return "redirect:/goto_pay.do";
	}
	@RequestMapping("goto_pay")
	public String goto_pay() {
		return "sale_pay";
	}
}
