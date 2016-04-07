package com.jumper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jumper.dto.FacilityDto;
import com.jumper.dto.UserDto;
import com.jumper.entity.TFacility;
import com.jumper.entity.TUser;
import com.jumper.service.FacilityService;

@Controller
public class FacilityController {
	
	private static Logger logger = Logger.getLogger(FacilityController.class);
	
	@Autowired
	private FacilityService facilityService;
	
	/**
	 * 通过id查询设备信息
	 * @param id 设备id（不是facid）
	 * @return
	 */
	@RequestMapping("/getFacilityWithId")
	@ResponseBody
	public String getFacilityWithId(@RequestParam int id){
		TFacility facility = facilityService.get(id);
		System.out.println(JSON.toJSONString(facility));
		return JSON.toJSONString(facility);
	}
	
	/**
	 * 新增设备
	 * @param facjson 设备json
	 * @return
	 */
	@RequestMapping("/createFacility")
	@ResponseBody
	public String createFacility(@RequestParam String facjson){
		TFacility facility = JSON.parseObject(facjson, TFacility.class);
		try {
			facility.setBuydate(new Date());
			facilityService.save(facility);
			logger.info("创建新设备成功！id="+facility.getId());
			return "success";
		} catch (Exception e) {
			logger.error("创建新设备失败！id="+facility.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 查询用户显示相关
	 * @param pageNo  当前页
	 * @param pageSize  每页数量
	 * @param facjson	查询条件
	 * @return
	 */
	@RequestMapping("/getfacList")
	@ResponseBody
	public String getfacList(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String facjson){
		Map<String, String> map = null;
		if(facjson != null){
			map = (Map<String, String>) JSON.parse(facjson);
		}
		List<TFacility> facilies = facilityService.findPageByCriteria(pageNo, pageSize, map);
		logger.info("查询成功！TFacilities:"+JSON.toJSONString(facilies));
		List<FacilityDto> facDtos = new ArrayList<FacilityDto>();
		FacilityDto facDto;
		for(TFacility facility : facilies){
			facDto = getDtoFromFacility(facility);
			facDtos.add(facDto);
		}
		logger.info("UserDtos:"+JSON.toJSONString(facDtos));
		return JSON.toJSONString(facDtos);
	}
	
	/**
	 * 通过facility得到faclitydto
	 * @param facility
	 * @return
	 */
	public FacilityDto getDtoFromFacility(TFacility facility){
		return new FacilityDto(facility.getId(), facility.getFacid(), facility.getName(), facility.getProductor(), facility.getType(), 
				facility.getDirector(), facility.getBuydate(), facility.getNum(), facility.getLocation(), facility.getState());
	}
	
}
