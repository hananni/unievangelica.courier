package br.com.unievangelica.courier.service;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

public interface GenericService <T, ID extends Serializable> {
	
	void save(T entity);

	void update(T entity);
	
	void saveOrUpdate(T entity);
	
	void delete(T entity);
	
	void delete(Long id);
	
	void deleteByCriteria(Criterion... criterion);
	
	T findById(Long id);
	
	T findById(Long id, Projection... projection);
	
	T findBy(Object ... criterion);
	
	List<T> findAll();
	
	List<T> findAll(Object ... criterion);
	
	List<?> findByQuery(String query, Hashtable<String, Object> params);
	
	int executeUpdate(String query, Hashtable<String, Object> params);
}