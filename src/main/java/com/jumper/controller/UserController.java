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
import com.jumper.dto.UserDto;
import com.jumper.entity.TUser;
import com.jumper.service.UserService;

@Controller
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 通过id查询用户信息
	 * @param id 用户id（不是userid）
	 * @return
	 */
	@RequestMapping("/getUserWithId")
	@ResponseBody
	public String getUserWithId(@RequestParam int id){
		TUser tuser = userService.get(id);
		System.out.println(JSON.toJSONString(tuser));
		return JSON.toJSONString(tuser);
	}
	
	/**
	 * 通过id重置用户密码
	 * @param id 用户id（不是userid）
	 * @return
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	public String resetPassword(@RequestParam int id){
		TUser tuser = userService.get(id);
		if(tuser!=null){
			logger.info(id+"查询成功！"+JSON.toJSONString(tuser));
			tuser.setPassword(""+tuser.getUserid());
			userService.saveOrUpdate(tuser);
			return "success";
		}else{
			logger.warn(id+"查询无结果！");
			return "failed";
		}
	}
	
	/**
	 * 新增用户
	 * @param userjson 用户json
	 * @return
	 */
	@RequestMapping("/createUser")
	@ResponseBody
	public String createUser(@RequestParam String userjson){
		TUser tuser = JSON.parseObject(userjson, TUser.class);
		try {
			tuser.setCreatetime(new Date());
			userService.save(tuser);
			logger.info("创建新用户成功！id="+tuser.getId());
			return "success";
		} catch (Exception e) {
			logger.error("创建新用户失败！id="+tuser.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 更新用户信息
	 * @param userjson
	 * @return
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(@RequestParam String userjson){
		TUser tuser = JSON.parseObject(userjson, TUser.class);
		try {
			userService.update(tuser);
			logger.info("更新用户成功！id="+tuser.getId());
			return "success";
		} catch (Exception e) {
			logger.error("更新用户失败！id="+tuser.getId(),e);
			return "failed";
		}
	}
	
	/**
	 * 查询用户显示相关
	 * @param pageNo  当前页
	 * @param pageSize  每页数量
	 * @param mapjson	查询条件
	 * @return
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	public String getuserList(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String mapjson){
		Map<String, String> map = null;
		if(mapjson != null){
			map = JSON.parseObject(mapjson,Map.class);
		}
		int authority = Integer.parseInt(map.get("authority"));
		map.remove("authority");
		List<TUser> users = userService.findPageByCriteria(pageNo, pageSize, map);
		logger.info("查询成功！TUsers:"+JSON.toJSONString(users));
//		List<UserDto> userDtos = new ArrayList<UserDto>();
//		UserDto userDto;
		List<TUser> userlist = new ArrayList<TUser>();
		for(TUser user : users){
			if(authority==0||user.getAuthority()==authority){
//				userDto = getDtoFromUser(user);
//				userDtos.add(userDto);
				userlist.add(user);
			}
		}
		logger.info("UserDtos:"+JSON.toJSONString(userlist));
		return JSON.toJSONString(userlist);
	}
	
	/**
	 * 通过user得到userdto
	 * @param user
	 * @return
	 */
	public UserDto getDtoFromUser(TUser user){
		return new UserDto(user.getId(), user.getUserid(), user.getName(), user.getPassword(), 
				user.getAuthority(), user.getTel(), user.getSex(), user.getDepartment());
	}
	
}
