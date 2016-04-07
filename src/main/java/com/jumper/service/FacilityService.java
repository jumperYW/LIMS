package com.jumper.service;

import java.util.List;
import java.util.Map;

import com.jumper.entity.TFacility;

public interface FacilityService {
	
	TFacility load(int id);
	
	TFacility get(int id);
	
	List<TFacility> findAll();
	
	void persist(TFacility entity);
	
	int save(TFacility entity);
	
	void saveOrUpdate(TFacility entity);
	
	void delete(int id);
	
	void flush();
	
	List<TFacility> findPageByCriteria(int pageNo, int pageSize, Map<String,String> map);
}
