package com.jumper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.dao.FeedbackDao;
import com.jumper.entity.TFeedback;
import com.jumper.service.FeedbackService;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public TFeedback load(int id) {
		return feedbackDao.load(id);
	}

	@Override
	public TFeedback get(int id) {
		return feedbackDao.get(id);
	}

	@Override
	public List<TFeedback> findAll() {
		return feedbackDao.findAll();
	}

	@Override
	public void persist(TFeedback entity) {
		feedbackDao.persist(entity);
	}

	@Override
	public int save(TFeedback entity) {
		return feedbackDao.save(entity);
	}

	@Override
	public void saveOrUpdate(TFeedback entity) {
		feedbackDao.saveOrUpdate(entity);
	}

	@Override
	public void update(TFeedback entity) {
		feedbackDao.update(entity);
	}

	@Override
	public void delete(int id) {
		feedbackDao.delete(id);
	}

	@Override
	public void flush() {
		feedbackDao.flush();
	}

	@Override
	public List<TFeedback> findPageByCriteria(int pageNo, int pageSize, Map<String, String> map) {
		return feedbackDao.findPageByCriteria(pageNo, pageSize, map);
	}
	
}
