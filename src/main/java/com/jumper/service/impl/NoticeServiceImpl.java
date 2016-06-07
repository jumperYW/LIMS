package com.jumper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.dao.NoticeDao;
import com.jumper.entity.TNotice;
import com.jumper.service.NoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public TNotice load(int id) {
		return noticeDao.load(id);
	}

	@Override
	public TNotice get(int id) {
		return noticeDao.get(id);
	}

	@Override
	public List<TNotice> findAll() {
		return noticeDao.findAll();
	}

	@Override
	public void persist(TNotice entity) {
		noticeDao.persist(entity);
	}

	@Override
	public int save(TNotice entity) {
		return noticeDao.save(entity);
	}

	@Override
	public void saveOrUpdate(TNotice entity) {
		noticeDao.saveOrUpdate(entity);
	}

	@Override
	public void update(TNotice entity) {
		noticeDao.update(entity);
	}

	@Override
	public void delete(int id) {
		noticeDao.delete(id);
	}

	@Override
	public void flush() {
		noticeDao.flush();
	}

	@Override
	public List<TNotice> findPageByCriteria(int pageNo, int pageSize, Map<String, String> map) {
		return noticeDao.findPageByCriteria(pageNo, pageSize, map);
	}

}
