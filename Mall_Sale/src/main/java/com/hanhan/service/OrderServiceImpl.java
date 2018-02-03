package com.hanhan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanhan.bean.OBJECT_T_MALL_FLOW;
import com.hanhan.bean.OBJECT_T_MALL_ORDER;
import com.hanhan.bean.T_MALL_ADDRESS;
import com.hanhan.bean.T_MALL_ORDER_INFO;
import com.hanhan.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	

	@Override
	public void save_order(T_MALL_ADDRESS address, OBJECT_T_MALL_ORDER order) {
		// TODO Auto-generated method stub
		//保存订单的时候需要保存flow和order_info的信息，
		//1.保存order信息，在保存订单的时候需要修改进度号
		// 保存订单表，生成主键
		// shhr 收件人,
		// zje 总金额,
		// jdh 进度号,
		// yh_id 用户id,
		// dzh_id 地址id,
		// dzh_mch 地址名称
		Map<String,Object> order_map = new HashMap<String,Object>();
		order.setJdh(1);
		order_map.put("address", address);
		order_map.put("order", order);
		orderMapper.insert_order(order_map);
		//2.保存flow的信息，删除的时候需要购物车的信息
		List<Integer> list_cart_id= new ArrayList<Integer>();
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			// 根据订单id保存物流包裹
//			dd_id：订单id
//			mdd：目的地
			Map<String,Object> flow_map = new HashMap<String,Object>();
			list_flow.get(i).setDd_id(order.getId());
			list_flow.get(i).setMdd(address.getYh_dz());
			orderMapper.insert_flow(list_flow.get(i));
			List<T_MALL_ORDER_INFO> list_info = list_flow.get(i).getList_info();
//			dd_id:order的id
//			flow_id
			Map<String,Object> info_map = new HashMap<String,Object>();
			info_map.put("dd_id", order.getId());
			info_map.put("flow_id", list_flow.get(i).getId());
			info_map.put("list_info", list_info);
			orderMapper.insert_order_info(info_map);
		//保存购物车的id
			for (int j = 0; j < list_info.size(); j++) {
				list_cart_id.add(list_info.get(j).getGwch_id());
			}
	}	
		//当保存订单之后，就需要删除购物车中的内容，删除购物车的数据
		orderMapper.deleteCart(list_cart_id);
}

}
