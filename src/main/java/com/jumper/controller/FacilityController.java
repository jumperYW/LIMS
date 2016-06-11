package com.jumper.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jumper.dto.FacilityDto;
import com.jumper.entity.TFacility;
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
		logger.info("创建新设备：facjson:"+facjson);
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
	 * 修改设备状态
	 * @param facjson
	 * @return
	 */
	@RequestMapping("/changeFacState")
	@ResponseBody
	public String updateFac(@RequestParam String facjson){
		logger.info("修改设备状态facjson:"+facjson);
		TFacility fac = JSON.parseObject(facjson,TFacility.class);
		TFacility facility = facilityService.get(fac.getId());
		facility.setState(fac.getState());
//		facility.setNum(fac.getNum());
		try{
			facilityService.update(facility);
			logger.info("更新成功！facid="+facility.getFacid());
			return "success";
		} catch(Exception e){
			logger.error("更新失败！facid="+facility.getFacid(),e);
			return "failed";
		}
	}	
	
	@Autowired private HttpServletRequest request;
	
	/**
	 * 修改设备信息
	 * @return
	 */
	@RequestMapping("/updateFacPost")
	@ResponseBody
	public String updateFacPost(){
		byte[] facByte = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			InputStream in = request.getInputStream();
			BufferedInputStream bin = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int len = 0;
			while(-1!=(len = bin.read(buffer,0,1024))){
				bos.write(buffer,0,len);
			}
			facByte = bos.toByteArray();
			bin.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally{
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String facjson = null;
		try {
			facjson = new String(facByte,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		logger.info("修改设备信息facjson:"+facjson);
		TFacility fac = JSON.parseObject(facjson,TFacility.class);
		System.out.println(fac.toString());
		try{
			facilityService.update(fac);
			logger.info("更新成功！facid="+fac.getFacid());
			return "success";
		} catch(Exception e){
			logger.error("更新失败！facid="+fac.getFacid(),e);
			return "failed";
		}
	}
	
	/**
	 * 删除设备信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteFac")
	@ResponseBody
	public String deleteFac(@RequestParam int id){
		logger.info("删除设备,id="+id);
		try{
			facilityService.delete(id);
			logger.info("删除成功：id="+id);
			return "success";
		} catch(Exception e){
			logger.error("删除失败：id="+id,e);
			return "failed";
		}
	}
	
	/**
	 * 查询设备显示相关
	 * @param pageNo  当前页
	 * @param pageSize  每页数量
	 * @param mapjson	查询条件
	 * @return
	 */
	@RequestMapping("/getFacList")
	@ResponseBody
	public String getFacList(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String mapjson){
		Map<String, Object> map = null;
		if(mapjson != null){
			map = JSON.parseObject(mapjson,Map.class);
		}
		if("0".equals(map.get("state"))){
			map.remove("state");
		}
		List<TFacility> facilies = facilityService.findPageByCriteria(pageNo, pageSize, map);
		for(int i=0;i<facilies.size();i++){
			facilies.get(i).setImage(null);
		}
		logger.info("查询成功！TFacilities:"+JSON.toJSONString(facilies));
		return JSON.toJSONString(facilies);
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
