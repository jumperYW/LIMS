package com.jumper.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jumper.dao.FeedbackDao;
import com.jumper.entity.TClass;
import com.jumper.entity.TFeedback;

@Repository("feedbackDao")
public class FeedbackDaoImpl implements FeedbackDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public TFeedback load(Integer id) {
		return (TFeedback) this.getCurrentSession().load(TFeedback.class,id);
	}

	@Override
	public TFeedback get(Integer id) {
		return (TFeedback) this.getCurrentSession().get(TFeedback.class, id);
	}

	@Override
	public List<TFeedback> findAll() {
		List<TFeedback> feedbacks = this.getCurrentSession().createQuery(" from TFeedback ").list();
		return feedbacks;
	}

	@Override
	public void persist(TFeedback entity) {
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(TFeedback entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(TFeedback entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void update(TFeedback entity) {
		this.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Integer id) {
		TFeedback feedback = this.load(id);
		this.getCurrentSession().delete(feedback);
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();
	}

	@Override
	public List<TFeedback> findPageByCriteria(int pageNo, int pageSize, Map<String, Object> map) {
		List<TFeedback> TFeedbacks = null;
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(TFeedback.class);
			if(map!=null){
				Iterator it = map.keySet().iterator();
				while(it.hasNext()){
					String key = (String)it.next();
					criteria.add(Restrictions.like(key, "%"+map.get(key)+"%"));
				}
			}
			criteria.setProjection(null);
			criteria.setFirstResult((pageNo-1)*pageSize);
			criteria.setMaxResults(pageSize);
			TFeedbacks = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return TFeedbacks;
	}

}
