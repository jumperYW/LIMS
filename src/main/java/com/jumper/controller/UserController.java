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
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteFac(@RequestParam int id){
		logger.info("删除用户,id="+id);
		try{
			userService.delete(id);
			logger.info("删除成功：id="+id);
			return "success";
		} catch(Exception e){
			logger.error("删除失败：id="+id,e);
			return "failed";
		}
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
	 * 通过id更改用户权限
	 * @param id 用户id（不是userid）
	 * @return
	 */
	@RequestMapping("/resetAuthority")
	@ResponseBody
	public String resetAuthority(@RequestParam int id,@RequestParam byte authority){
		TUser tuser = userService.get(id);
		if(tuser!=null){
			logger.info(id+"查询成功！"+JSON.toJSONString(tuser));
			tuser.setAuthority(authority);
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
	
	@Autowired private HttpServletRequest request;
	/**
	 * 修改用户信息
	 * @return
	 */
	@RequestMapping("/updateUserPost")
	@ResponseBody
	public String updateUserPost(){
		byte[] userByte = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			InputStream in = request.getInputStream();
			BufferedInputStream bin = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int len = 0;
			while(-1!=(len = bin.read(buffer,0,1024))){
				bos.write(buffer,0,len);
			}
			userByte = bos.toByteArray();
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
		String userjson = null;
		try {
			userjson = new String(userByte,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		logger.info("修改用户信息userjson:"+userjson);
		TUser user = JSON.parseObject(userjson,TUser.class);
		user.setCreatetime(new Date());
		System.out.println(user.toString());
		try{
			//userService.update(user);
			userService.saveOrUpdate(user);
			logger.info("更新成功！facid="+user.getUserid());
			return "success";
		} catch(Exception e){
			logger.error("更新失败！facid="+user.getUserid(),e);
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
		Map<String, Object> map = null;
		if(mapjson != null){
			map = JSON.parseObject(mapjson,Map.class);
		}
		logger.info("map:"+map);
		if(map.get("authority").equals("0")){
			map.remove("authority");
		}
		List<TUser> users = userService.findPageByCriteria(pageNo, pageSize, map);
		logger.info("查询成功！TUsers:"+JSON.toJSONString(users));
		return JSON.toJSONString(users);
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
