package com.jumper.service;

import java.util.List;
import java.util.Map;

import com.jumper.entity.TClass;

public interface ClassService {
	
	TClass load(int id);
	
	TClass get(int id);
	
	List<TClass> findAll();
	
	void persist(TClass entity);
	
	int save(TClass entity);
	
	void saveOrUpdate(TClass entity);
	
	void update(TClass entity);
	
	void delete(int id);
	
	void flush();
	
	List<TClass> findPageByCriteria(int pageNo, int pageSize, Map<String,String> map);
	
}
