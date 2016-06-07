package com.jumper.dao;

import java.util.List;
import java.util.Map;

public interface Generic <T,P> {

	T load(P id);
	
	T get(P id);
	
	List<T> findAll();
	
	void persist(T entity);
	
	P save(T entity);
	
	void saveOrUpdate(T entity);
	
	void update(T entity);
	
	void delete(P id);
	
	void flush();
	
	List<T> findPageByCriteria(int pageNo, int pageSize, Map<String,String> map);
	
}
