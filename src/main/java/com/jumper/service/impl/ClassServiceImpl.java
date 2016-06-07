package com.jumper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.dao.ClassDao;
import com.jumper.dao.FacilityDao;
import com.jumper.entity.TClass;
import com.jumper.service.ClassService;

@Service("classService")
public class ClassServiceImpl implements ClassService{

	
	@Autowired
	private ClassDao classDao;
	
	@Override
	public TClass load(int id) {
		return classDao.load(id);
	}

	@Override
	public TClass get(int id) {
		return classDao.get(id);
	}

	@Override
	public List<TClass> findAll() {
		return classDao.findAll();
	}

	@Override
	public void persist(TClass entity) {
		classDao.persist(entity);
	}

	@Override
	public int save(TClass entity) {
		return classDao.save(entity);
	}

	@Override
	public void saveOrUpdate(TClass entity) {
		classDao.saveOrUpdate(entity);
	}

	@Override
	public void update(TClass entity) {
		classDao.update(entity);
	}

	@Override
	public void delete(int id) {
		classDao.delete(id);
	}

	@Override
	public void flush() {
		classDao.flush();
	}

	@Override
	public List<TClass> findPageByCriteria(int pageNo, int pageSize, Map<String, String> map) {
		return classDao.findPageByCriteria(pageNo, pageSize, map);
	}

}
