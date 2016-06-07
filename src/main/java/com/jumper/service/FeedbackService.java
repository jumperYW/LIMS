package com.jumper.service;

import java.util.List;
import java.util.Map;

import com.jumper.entity.TFeedback;

public interface FeedbackService {

	TFeedback load(int id);
	
	TFeedback get(int id);
	
	List<TFeedback> findAll();
	
	void persist(TFeedback entity);
	
	int save(TFeedback entity);
	
	void saveOrUpdate(TFeedback entity);
	
	void update(TFeedback entity);
	
	void delete(int id);
	
	void flush();
	
	List<TFeedback> findPageByCriteria(int pageNo, int pageSize, Map<String,String> map);
	
}
