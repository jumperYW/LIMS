package com.jumper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.dao.FacilityDao;
import com.jumper.entity.TFacility;
import com.jumper.service.FacilityService;

@Service("facilityService")
public class FacilityServiceImpl implements FacilityService{

	@Autowired
	private FacilityDao	facDao;
	
	public TFacility load(int id) {
		return facDao.load(id);
	}

	public TFacility get(int id) {
		return facDao.get(id);
	}

	public List<TFacility> findAll() {
		return facDao.findAll();
	}

	public void persist(TFacility entity) {
		facDao.persist(entity);
	}

	public int save(TFacility entity) {
		return facDao.save(entity);
	}

	public void saveOrUpdate(TFacility entity) {
		facDao.saveOrUpdate(entity);
	}

	public void delete(int id) {
		facDao.delete(id);
	}

	public void flush() {
		facDao.flush();
	}

	public List<TFacility> findPageByCriteria(int pageNo, int pageSize, Map<String, String> map) {
		return facDao.findPageByCriteria(pageNo, pageSize, map);
	}

}
