package com.jumper.service;

import java.util.List;
import java.util.Map;

import com.jumper.entity.TUser;

public interface UserService {
	
	TUser load(int id);
	
	TUser get(int id);
	
	List<TUser> findAll();
	
	void persist(TUser entity);
	
	int save(TUser entity);
	
	void saveOrUpdate(TUser entity);
	
	void update(TUser entity);
	
	void delete(int id);
	
	void flush();
	
	List<TUser> findPageByCriteria(int pageNo, int pageSize, Map<String,String> map);
	

}
