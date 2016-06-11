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

import com.jumper.dao.ClassDao;
import com.jumper.entity.TClass;
import com.jumper.entity.TFacility;

@Repository("classDao")
public class ClassDaoImpl implements ClassDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public TClass load(Integer id) {
		return (TClass) this.getCurrentSession().load(TClass.class,id);
	}

	@Override
	public TClass get(Integer id) {
		return (TClass) this.getCurrentSession().get(TClass.class, id);
	}

	@Override
	public List<TClass> findAll() {
		List<TClass> classes = this.getCurrentSession().createQuery(" from TClass ").list();
		return classes;
	}

	@Override
	public void persist(TClass entity) {
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(TClass entity) {
		return (Integer) this.getCurrentSession().save(entity);	
	}

	@Override
	public void saveOrUpdate(TClass entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void update(TClass entity) {
		this.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Integer id) {
		TClass tclass = this.load(id);
		this.getCurrentSession().delete(tclass);
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();
	}

	@Override
	public List<TClass> findPageByCriteria(int pageNo, int pageSize, Map<String, Object> map) {
		List<TClass> classes = null;
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(TClass.class);
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
			classes = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return classes;
	}

}
