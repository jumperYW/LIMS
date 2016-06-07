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
import com.jumper.entity.TNotice;
import com.jumper.service.NoticeService;

@Controller
public class NoticeController {

	private static Logger logger = Logger.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 通过id查询教室信息
	 * @param id 
	 * @return
	 */
	@RequestMapping("/getNoticeWithId")
	@ResponseBody
	public String getNoticeWithId(@RequestParam int id){
		TNotice notice = noticeService.get(id);
		System.out.println(JSON.toJSONString(notice));
		return JSON.toJSONString(notice);
	}
	
	/**
	 * 新增公告
	 * @param noticejson 公告json
	 * @return
	 */
	@RequestMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestParam String noticejson){
		logger.info("创建新订单：facjson:"+noticejson);
		TNotice notice = JSON.parseObject(noticejson, TNotice.class);
		try {
			noticeService.save(notice);
			logger.info("创建新公告成功！id="+notice.getId());
			return "success";
		} catch (Exception e) {
			logger.error("创建新公告失败！id="+notice.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 修改公告信息
	 * @param noticejson
	 * @return
	 */
	@RequestMapping("/updateNotice")
	@ResponseBody
	public String updateFeedback(@RequestParam String noticejson){
		logger.info("修改公告信息noticejson:"+noticejson);
		TNotice notice = JSON.parseObject(noticejson,TNotice.class);
		try{
			noticeService.update(notice);
			logger.info("更新成功！id="+notice.getId());
			return "success";
		} catch(Exception e){
			logger.error("更新失败！id="+notice.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 查询公告相关
	 * @param pageNo  当前页
	 * @param pageSize  每页数量
	 * @param mapjson	查询条件
	 * @return
	 */
	@RequestMapping("/getNoticeList")
	@ResponseBody
	public String getNoticeList(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String mapjson){
		Map<String, String> map = null;
		if(mapjson != null){
			map = JSON.parseObject(mapjson,Map.class);
		}
		List<TNotice> notices = noticeService.findPageByCriteria(pageNo, pageSize, map);
		logger.info("查询成功！TNotices:"+JSON.toJSONString(notices));
		return JSON.toJSONString(notices);
	}
	
}
