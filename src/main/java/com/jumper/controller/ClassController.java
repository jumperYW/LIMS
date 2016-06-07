package com.jumper.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jumper.entity.TClass;
import com.jumper.service.ClassService;

@Controller
public class ClassController {
	
	private static Logger logger = Logger.getLogger(ClassController.class);

	@Autowired
	private ClassService classService;
	
	/**
	 * 通过id查询教室信息
	 * @param id 
	 * @return
	 */
	@RequestMapping("/getClassWithId")
	@ResponseBody
	public String getClassWithId(@RequestParam int id){
		TClass cla = classService.get(id);
		System.out.println(JSON.toJSONString(cla));
		return JSON.toJSONString(cla);
	}
	
	/**
	 * 新增教室
	 * @param classjson 实验室json
	 * @return
	 */
	@RequestMapping("/createClass")
	@ResponseBody
	public String createClass(@RequestParam String classjson){
		logger.info("创建新教室：facjson:"+classjson);
		TClass cla = JSON.parseObject(classjson, TClass.class);
		try {
			classService.save(cla);
			logger.info("创建新教室成功！id="+cla.getId());
			return "success";
		} catch (Exception e) {
			logger.error("创建新教室失败！id="+cla.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 修改教室信息
	 * @param classjson
	 * @return
	 */
	@RequestMapping("/updateClass")
	@ResponseBody
	public String updateClass(@RequestParam String classjson){
		logger.info("修改教室信息facjson:"+classjson);
		TClass cla = JSON.parseObject(classjson,TClass.class);
		try{
			classService.update(cla);
			logger.info("更新成功！classid="+cla.getClassid());
			return "success";
		} catch(Exception e){
			logger.error("更新失败！classid="+cla.getClassid(),e);
			return "failed";
		}
	}
	
	/**
	 * 删除教室信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteClass")
	@ResponseBody
	public String deleteClass(@RequestParam int id){
		logger.info("删除教室,id="+id);
		try{
			classService.delete(id);
			logger.info("删除成功：id="+id);
			return "success";
		} catch(Exception e){
			logger.error("删除失败：id="+id,e);
			return "failed";
		}
	}
	
	/**
	 * 查询教室相关
	 * @param pageNo  当前页
	 * @param pageSize  每页数量
	 * @param mapjson	查询条件
	 * @return
	 */
	@RequestMapping("/getTClassList")
	@ResponseBody
	public String getTClassList(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String mapjson){
		Map<String, String> map = null;
		if(mapjson != null){
			map = JSON.parseObject(mapjson,Map.class);
		}
		if("0".equals(map.get("state"))){
			map.remove("state");
		}
		List<TClass> classes = classService.findPageByCriteria(pageNo, pageSize, map);
		logger.info("查询成功！TClasses:"+JSON.toJSONString(classes));
		return JSON.toJSONString(classes);
	}
	
}
