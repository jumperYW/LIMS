package com.jumper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.dao.UserDao;
import com.jumper.entity.TUser;
import com.jumper.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
		
	public TUser load(int id) {
		return userDao.load(id);
	}

	public TUser get(int id) {
		return userDao.get(id);
	}

	public List<TUser> findAll() {
		return userDao.findAll();
	}

	public void persist(TUser entity) {
		userDao.persist(entity);
	}

	public int save(TUser entity) {
		return userDao.save(entity);
	}

	public void saveOrUpdate(TUser entity) {
		userDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public void flush() {
		userDao.flush();
	}

	@Override
	public TUser getToLogin(int id) {
		TUser tuser = userDao.get(id);
		TUser user = new TUser();
		user.setId(tuser.getId());
		user.setPassword(tuser.getPassword());
		user.setAuthority(tuser.getAuthority());
		return user;
	}

	@Override
	public List<TUser> findPageByCriteria(int pageNo, int pageSize, Map<String,String> map) {
		return userDao.findPageByCriteria(pageNo, pageSize, map);
	}
	
	
}
