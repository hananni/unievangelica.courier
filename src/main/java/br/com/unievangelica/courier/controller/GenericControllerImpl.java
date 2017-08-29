package br.com.unievangelica.courier.controller;
//package br.com.unievangelica.courier.controllers;
import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unievangelica.courier.service.GenericServiceImpl;

@Controller("controller")
public class GenericControllerImpl<T, ID extends Serializable> implements GenericController<T, Serializable>{

	private GenericServiceImpl<T, ID> service;

	public GenericControllerImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenericControllerImpl(GenericServiceImpl<T, ID> service) {
		super();
		this.service = service;
	}

	@Override
	@RequestMapping(method=RequestMethod.GET , value="/conectado",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> Conectado() {
		// TODO Auto-generated method stub
		return new ResponseEntity<Boolean>(HttpStatus.OK);
		
	}
	
	@Override
	@RequestMapping(method=RequestMethod.POST , value="/salvar", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> save(@RequestBody @Valid T entity) throws Exception{
		service.save(entity);
		return new ResponseEntity<>(entity,HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(method=RequestMethod.POST , value="/atualizar", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> update(@RequestBody @Valid T entity){
		service.update(entity);
		return new ResponseEntity<>(entity,HttpStatus.OK);
	}
	
//	@Override
//	@RequestMapping(method=RequestMethod.DELETE, value="/deletar/{id}")
//	public ResponseEntity<T> delete(@PathVariable Long id){
//		service.delete(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	@Override
	@RequestMapping(method=RequestMethod.GET, value="/buscar/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> findById(@PathVariable Integer id){
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(method=RequestMethod.GET, value="/buscarTodos",produces=MediaType.APPLICATION_JSON_VALUE)
	public  List<?> findAll(){
		
		return service.findAll();
	}
	
	

	



}
