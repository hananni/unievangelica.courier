package br.com.unievangelica.courier.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface GenericController <T, ID extends Serializable>{

	ResponseEntity<Boolean> Conectado();
	
	ResponseEntity<T> save(T entity) throws Exception;
	
	ResponseEntity<T> update(T entity);
	
//	ResponseEntity<T> delete(Long id);

	ResponseEntity<T> findById(Integer id);
	
	List<?> findAll();

	

	

}