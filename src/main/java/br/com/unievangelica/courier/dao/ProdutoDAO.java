package br.com.unievangelica.courier.dao;

import org.springframework.stereotype.Repository;

import br.com.unievangelica.courier.entity.Produto;
import br.com.unievangelica.courier.hibernate.HibernateUtilImpl;

@Repository
public class ProdutoDAO extends HibernateUtilImpl <Produto, Integer>{

	public ProdutoDAO() {
		super(Produto.class);
		// TODO Auto-generated constructor stub
	}

	
	
}
