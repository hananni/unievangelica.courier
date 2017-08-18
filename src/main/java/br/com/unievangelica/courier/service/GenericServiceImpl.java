package br.com.unievangelica.courier.service;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.unievangelica.courier.hibernate.HibernateUtil;
import br.com.unievangelica.courier.hibernate.HibernateUtilImpl;


@Service
public class GenericServiceImpl<T, ID extends Serializable> {
	
	private HibernateUtil<T, ID> hibernateUtil;
	
	public GenericServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GenericServiceImpl(HibernateUtilImpl<T, ID> hibernateUtil) {
		super();
		// TODO Auto-generated constructor stub
		this.hibernateUtil = hibernateUtil;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Override
	public void save(T entity) throws Exception{
		// TODO Auto-generated method stub
		hibernateUtil.save(entity);
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		hibernateUtil.update(entity);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Override
	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		hibernateUtil.saveOrUpdate(entity);
		
	}
	
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Override
//	public void delete(T entity) {
//		// TODO Auto-generated method stub
//		hibernateUtil.delete(entity);
//		
//	}
	
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Override
//	public void delete(Long id) {
//		// TODO Auto-generated method stub
//		hibernateUtil.deleteById(id);
//	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	@Override
	public void deleteByCriteria(Criterion... criterion){
		// TODO Auto-generated method stub
		hibernateUtil.deleteByCriteria(criterion);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
	public T findById(Integer id) {
		// TODO Auto-generated method stub
		return hibernateUtil.findById(id);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
	public T findById(Long id, Projection... projection){
		// TODO Auto-generated method stub
		return hibernateUtil.findById(id, projection);	
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
	public T findBy(Object ... criterion){
		// TODO Auto-generated method stub
		return hibernateUtil.findBy(criterion);			
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
	public List<? extends T> findAll(Object... criterion) {
		// TODO Auto-generated method stub
		return hibernateUtil.findAll(criterion);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
	public List<?> findByQuery(String query, Hashtable<String, Object> params) {
		// TODO Auto-generated method stub
		return hibernateUtil.findByQuery(query, params);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
//	@Override
	public int executeUpdate(String query, Hashtable<String, Object> params) {
		// TODO Auto-generated method stub
		return hibernateUtil.executeUpdate(query, params);
	}
	
	
	

}