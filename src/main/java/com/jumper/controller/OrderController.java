package com.jumper.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jumper.entity.TOrder;
import com.jumper.service.OrderService;

@Controller
public class OrderController {

	private static Logger logger = Logger.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	
	/**
	 * 通过id查询教室信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/getOrderWithId")
	@ResponseBody
	public String getOrderWithId(@RequestParam int id){
		TOrder order = orderService.get(id);
		System.out.println(JSON.toJSONString(order));
		return JSON.toJSONString(order);
	}

	/**
	 * 新增订单
	 * @param orderjson 订单json
	 * @return
	 */
	@RequestMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestParam String orderjson){
		logger.info("创建新订单：orderjson:"+orderjson);
		TOrder order = JSON.parseObject(orderjson, TOrder.class);
		try {
			orderService.save(order);
			logger.info("创建新订单成功！id="+order.getId());
			return "success";
		} catch (Exception e) {
			logger.error("创建新订单失败！id="+order.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 修改订单信息
	 * @param orderjson
	 * @return
	 */
	@RequestMapping("/updateOrder")
	@ResponseBody
	public String updateOrder(@RequestParam String orderjson){
		logger.info("修改订单信息orderjson:"+orderjson);
		TOrder order = JSON.parseObject(orderjson,TOrder.class);
		try{
			orderService.update(order);
			logger.info("更新成功！orderid="+order.getId());
			return "success";
		} catch(Exception e){
			logger.error("更新失败！orderid="+order.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 查询订单相关
	 * @param pageNo  当前页
	 * @param pageSize  每页数量
	 * @param mapjson	查询条件
	 * @return
	 */
	@RequestMapping("/getOrderList")
	@ResponseBody
	public String getOrderList(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String mapjson){
		Map<String, Object> map = null;
		if(mapjson != null){
			map = JSON.parseObject(mapjson,Map.class);
		}
		if("5".equals(map.get("state"))){
			map.remove("state");
		}
		logger.info("mapjson:"+mapjson);
		logger.info("map:"+map);
		List<TOrder> orders = orderService.findPageByCriteria(pageNo, pageSize, map);
		logger.info("查询成功！TOrders:"+JSON.toJSONString(orders));
		return JSON.toJSONString(orders);
	}
	
}
