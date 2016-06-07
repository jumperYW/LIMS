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

import com.jumper.dao.NoticeDao;
import com.jumper.entity.TClass;
import com.jumper.entity.TNotice;

@Repository("noticeDao")
public class NoticeDaoImpl implements NoticeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public TNotice load(Integer id) {
		return (TNotice) this.getCurrentSession().load(TNotice.class, id);
	}

	@Override
	public TNotice get(Integer id) {
		return (TNotice) this.getCurrentSession().get(TNotice.class, id);
	}

	@Override
	public List<TNotice> findAll() {
		List<TNotice> notices = this.getCurrentSession().createQuery(" from TNotice ").list();
		return notices;
	}

	@Override
	public void persist(TNotice entity) {
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(TNotice entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(TNotice entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void update(TNotice entity) {
		this.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Integer id) {
		TNotice notice = this.load(id);
		this.getCurrentSession().delete(notice);
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();
	}

	@Override
	public List<TNotice> findPageByCriteria(int pageNo, int pageSize, Map<String, String> map) {
		List<TNotice> notices = null;
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(TNotice.class);
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
			notices = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return notices;
	}

}
