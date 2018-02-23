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
import com.hanhan.exception.OverSaleException;
import com.hanhan.mapper.OrderMapper;
import com.hanhan.utils.MyDateUtil;

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


	//修改订单之后的状态
	@Override
	public void pay_after(OBJECT_T_MALL_ORDER order) throws OverSaleException {
		// TODO Auto-generated method stub
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			OBJECT_T_MALL_FLOW flow = list_flow.get(i);
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			for (int j = 0; j < list_info.size(); j++) {
				int sku_id = list_info.get(j).getSku_id();
				// 查询剩余sku数量
				long kc = get_kc(sku_id);

				if (kc > list_info.get(j).getSku_shl()) {
					// 更新sku库存，增加销量
					orderMapper.update_kc(list_info.get(j));
				} else {
					// sku数量不足，回滚订单操作，给出提示
					throw new OverSaleException("over sale");
				}
			}
			// 生成物流包裹的其他信息，配送时间，配送描述，业务员，联系方式
			flow.setLxfsh("12312312313");
			flow.setPsshj(MyDateUtil.getMyDate(1));// 物流接口,可以知道什么时候发货
			flow.setPsmsh("商品正在出库");
			flow.setYwy("老佟");
			
			orderMapper.update_flow(flow);
		}
		// 修改订单状态，预计送达时间
		order.setJdh(3);
		order.setYjsdshj(MyDateUtil.getMyDate(3));// 物流接口
		orderMapper.update_order(order);
	}


	private long get_kc(int sku_id) {
		long kc = 0l;

		// 库存查询的业务，加入查询锁，保证不会发生不可重复读的事务
		int select_count = orderMapper.select_count(sku_id);

		if (select_count == 1) {
			kc = orderMapper.select_kc(sku_id);
		} else {
			kc = orderMapper.select_kc_for_update(sku_id);
		}

		return kc;
	}
}


