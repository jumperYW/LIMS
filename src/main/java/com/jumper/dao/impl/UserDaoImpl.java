package com.jumper.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jumper.dao.UserDao;
import com.jumper.entity.TUser;


@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	
	public TUser load(Integer id) {
		return (TUser)this.getCurrentSession().load(TUser.class, id);
	}

	public TUser get(Integer id) {
		return (TUser)this.getCurrentSession().get(TUser.class, id);
	}

	public List<TUser> findAll() {
		List<TUser> tusers = this.getCurrentSession().createQuery("from TUser").list();
		return tusers;
	}

	public void persist(TUser entity) {
		this.getCurrentSession().persist(entity);
	}

	public Integer save(TUser entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TUser entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void update(TUser entity) {
		this.getCurrentSession().update(entity);
	}
	
	public void delete(Integer id) {
		TUser entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public List<TUser> findPageByCriteria(int pageNo, int pageSize, Map<String,String> map) {
		List<TUser> users = null;
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(TUser.class);
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
			users = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return users;
	}

}
