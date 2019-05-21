package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Fornecedor;
import dao.GenericDAO;
import util.Transacional;

public class FornecedorService implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Fornecedor> daoFornecedor;
	
	@Transacional
	public void inserirAlterar(Fornecedor fornecedor){
		if(fornecedor.getId()==null){
			daoFornecedor.inserir(fornecedor);
		}else{
			daoFornecedor.alterar(fornecedor);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoFornecedor.update(valor);
	}
}
