package base.service;

import javax.inject.Inject;

import base.modelo.Cidade;
import dao.GenericDAO;
import util.Transacional;

public class CidadeService {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Cidade> daoCidade;
	
	@Transacional
	public void inserirAlterar(Cidade cidade){
		if(cidade.getId()==null){
			daoCidade.inserir(cidade);
		}else{
			daoCidade.alterar(cidade);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoCidade.update(valor);
	}

}
