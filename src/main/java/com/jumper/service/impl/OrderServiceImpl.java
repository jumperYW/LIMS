package com.jumper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.dao.OrderDao;
import com.jumper.entity.TOrder;
import com.jumper.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public TOrder load(int id) {
		return orderDao.load(id);
	}

	@Override
	public TOrder get(int id) {
		return orderDao.get(id);
	}

	@Override
	public List<TOrder> findAll() {
		return orderDao.findAll();
	}

	@Override
	public void persist(TOrder entity) {
		orderDao.persist(entity);
	}

	@Override
	public int save(TOrder entity) {
		return orderDao.save(entity);
	}

	@Override
	public void saveOrUpdate(TOrder entity) {
		orderDao.saveOrUpdate(entity);
	}

	@Override
	public void update(TOrder entity) {
		orderDao.update(entity);
	}

	@Override
	public void delete(int id) {
		orderDao.delete(id);
	}

	@Override
	public void flush() {
		orderDao.flush();
	}

	@Override
	public List<TOrder> findPageByCriteria(int pageNo, int pageSize, Map<String, String> map) {
		return orderDao.findPageByCriteria(pageNo, pageSize, map);
	}

}
