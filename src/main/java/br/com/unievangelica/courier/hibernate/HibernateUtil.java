package br.com.unievangelica.courier.hibernate;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

public interface HibernateUtil<T, ID extends Serializable> {
	
	public EntityManager getEntityManager();
	
	public T findById(Integer id);
	
	public T findById(Long id, Projection... projection);
	
	public T findBy(Object ... criterion);
	
	public List<T> findAll(Object ... criterion);
	
	public List<? extends T> findByQuery(String query, Hashtable<String, Object> params);
	
	public void save(T entity);
	
	public void update(T entity);
	
	public int executeUpdate(String query, Hashtable<String, Object> params);
	
	public void saveOrUpdate(T entity);
	
	public void deleteByCriteria(Criterion... criterion);
}