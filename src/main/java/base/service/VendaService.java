package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Venda;
import dao.GenericDAO;
import util.Transacional;

public class VendaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Venda> daoVenda;
	
	@Transacional
	public void inserirAlterar(Venda venda){
		if(venda.getId()==null){
			daoVenda.inserir(venda);
		}else{
			daoVenda.alterar(venda);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoVenda.update(valor);
	}

}
