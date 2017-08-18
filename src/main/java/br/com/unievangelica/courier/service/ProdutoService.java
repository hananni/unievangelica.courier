package br.com.unievangelica.courier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unievangelica.courier.dao.ProdutoDAO;
import br.com.unievangelica.courier.entity.Produto;

@Service
public class ProdutoService extends GenericServiceImpl<Produto, Integer>{

	ProdutoDAO produtoDao = new ProdutoDAO();
	
	public ProdutoService(ProdutoDAO produtoDao) {
		super(produtoDao);
		// TODO Auto-generated constructor stub
		this.produtoDao = produtoDao; 
	}
	
	public boolean salvarProduto(Produto produto) throws Exception{
		
		produtoDao.save(produto);
		return true;
		
		
	}
	
	public Produto buscarPorId(Integer id) throws Exception{
		
		return produtoDao.findById(id);
		
		
		
	}
	
}
