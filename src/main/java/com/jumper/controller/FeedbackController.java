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
import com.jumper.entity.TFeedback;
import com.jumper.service.FeedbackService;

@Controller
public class FeedbackController {

	private static Logger logger = Logger.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackService feedbackService;

	/**
	 * 通过id查询反馈信息
	 * @param id 
	 * @return
	 */
	@RequestMapping("/getFeedbackWithId")
	@ResponseBody
	public String getFeedbackWithId(@RequestParam int id){
		TFeedback feedback = feedbackService.get(id);
		System.out.println(JSON.toJSONString(feedback));
		return JSON.toJSONString(feedback);
	}
	
	/**
	 * 新增反馈信息
	 * @param feedbackjson 反馈信息json
	 * @return
	 */
	@RequestMapping("/createFeedback")
	@ResponseBody
	public String createFeedback(@RequestParam String feedbackjson){
		logger.info("创建新设备：facjson:"+feedbackjson);
		TFeedback feedback = JSON.parseObject(feedbackjson, TFeedback.class);
		try {
			feedbackService.save(feedback);
			logger.info("创建新反馈信息成功！id="+feedback.getId());
			return "success";
		} catch (Exception e) {
			logger.error("创建新反馈信息失败！id="+feedback.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 修改反馈信息状态
	 * @param feedbackjson
	 * @return
	 */
	@RequestMapping("/updateFeedback")
	@ResponseBody
	public String updateFeedback(@RequestParam String feedbackjson){
		logger.info("修改反馈信息feedbackjson:"+feedbackjson);
		TFeedback feedback = JSON.parseObject(feedbackjson,TFeedback.class);
		try{
			feedbackService.update(feedback);
			logger.info("更新成功！id="+feedback.getId());
			return "success";
		} catch(Exception e){
			logger.error("更新失败！id="+feedback.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 查询反馈信息相关
	 * @param pageNo  当前页
	 * @param pageSize  每页数量
	 * @param mapjson	查询条件
	 * @return
	 */
	@RequestMapping("/getFeedbackList")
	@ResponseBody
	public String getFeedbackList(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String mapjson){
		Map<String, String> map = null;
		if(mapjson != null){
			map = JSON.parseObject(mapjson,Map.class);
		}
		List<TFeedback> feedbacks = feedbackService.findPageByCriteria(pageNo, pageSize, map);
		logger.info("查询成功！TFeedbacks:"+JSON.toJSONString(feedbacks));
		return JSON.toJSONString(feedbacks);
	}
}
