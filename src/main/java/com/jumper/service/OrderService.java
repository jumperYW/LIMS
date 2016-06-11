package com.jumper.service;

import java.util.List;
import java.util.Map;

import com.jumper.entity.TOrder;

public interface OrderService {
	
	TOrder load(int id);
	
	TOrder get(int id);
	
	List<TOrder> findAll();
	
	void persist(TOrder entity);
	
	int save(TOrder entity);
	
	void saveOrUpdate(TOrder entity);
	
	void update(TOrder entity);
	
	void delete(int id);
	
	void flush();
	
	List<TOrder> findPageByCriteria(int pageNo, int pageSize, Map<String,Object> map);
	
}
