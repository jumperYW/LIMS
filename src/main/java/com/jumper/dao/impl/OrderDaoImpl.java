package com.jumper.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.jumper.dao.OrderDao;
import com.jumper.entity.TOrder;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public TOrder load(Integer id) {
		return (TOrder) this.getCurrentSession().load(TOrder.class, id);
	}

	@Override
	public TOrder get(Integer id) {
		return (TOrder) this.getCurrentSession().get(TOrder.class, id);
	}

	@Override
	public List<TOrder> findAll() {
		List<TOrder> orders = this.getCurrentSession().createQuery(" from TOrder ").list();
		return orders;
	}

	@Override
	public void persist(TOrder entity) {
		this.getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(TOrder entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(TOrder entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void update(TOrder entity) {
		this.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Integer id) {
		TOrder order = this.load(id);
		this.getCurrentSession().delete(order);
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();
	}
	
	@Override
	public List<TOrder> findPageByCriteria(int pageNo, int pageSize, Map<String, Object> map) {
		List<TOrder> orders = null;
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(TOrder.class);
			if(map!=null){
				Iterator it = map.keySet().iterator();
				while(it.hasNext()){
					String key = (String)it.next();
					if(key.equals("date")){
						criteria.add(Restrictions.eq(key, new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get(key))));
						//criteria.add(Restrictions.sqlRestriction(key+" = '"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' "));
					}else if(key.equals("id")||key.equals("state")){
						criteria.add(Restrictions.eq(key, Integer.parseInt((String) map.get(key))));
					}else if(key.equals("states")&&map.get(key).equals("now")){
						List<Integer> sta = new ArrayList<>();
						sta.add(0);
						sta.add(1);
						criteria.add(Restrictions.in("state", sta));
					}
					else{
						criteria.add(Restrictions.like(key, "%"+map.get(key)+"%"));
					}
				}
			}
			criteria.setProjection(null);
			criteria.setFirstResult((pageNo-1)*pageSize);
			criteria.setMaxResults(pageSize);
			orders = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

}
