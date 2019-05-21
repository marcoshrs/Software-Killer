package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Produto;
import dao.GenericDAO;
import util.Transacional;

public class ProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Produto> daoProduto;
	
	@Transacional
	public void inserirAlterar(Produto produto){
		if(produto.getId()==null){
			daoProduto.inserir(produto);
		}else{
			daoProduto.alterar(produto);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoProduto.update(valor);
	}
}
