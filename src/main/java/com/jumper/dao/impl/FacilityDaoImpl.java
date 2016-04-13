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

import com.jumper.dao.FacilityDao;
import com.jumper.entity.TFacility;
import com.jumper.entity.TUser;

@Repository("faclityDao")
public class FacilityDaoImpl implements FacilityDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public TFacility load(Integer id) {
		return (TFacility) this.getCurrentSession().load(TFacility.class,id);
	}

	public TFacility get(Integer id) {
		return (TFacility) this.getCurrentSession().get(TFacility.class, id);
	}

	public List<TFacility> findAll() {
		List<TFacility> faclities = this.getCurrentSession().createQuery(" from TFacility").list();
		return faclities;
	}

	public void persist(TFacility entity) {
		this.getCurrentSession().persist(entity);
	}

	public Integer save(TFacility entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TFacility entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void update(TFacility entity) {
		this.getCurrentSession().update(entity);
	}
	
	public void delete(Integer id) {
		TFacility facility = this.load(id);
		this.getCurrentSession().delete(facility);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public List<TFacility> findPageByCriteria(int pageNo, int pageSize, Map<String, String> map) {
		List<TFacility> facilities = null;
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(TFacility.class);
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
			facilities = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return facilities;
	}

}
