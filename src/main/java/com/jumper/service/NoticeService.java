package com.jumper.service;

import java.util.List;
import java.util.Map;

import com.jumper.entity.TNotice;

public interface NoticeService {
	
	TNotice load(int id);
	
	TNotice get(int id);
	
	List<TNotice> findAll();
	
	void persist(TNotice entity);
	
	int save(TNotice entity);
	
	void saveOrUpdate(TNotice entity);
	
	void update(TNotice entity);
	
	void delete(int id);
	
	void flush();
	
	List<TNotice> findPageByCriteria(int pageNo, int pageSize, Map<String,String> map);
	
}
