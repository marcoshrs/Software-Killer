package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Estado;
import dao.GenericDAO;
import util.Transacional;

public class EstadoService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Estado> daoEstado;
	
	@Transacional
	public void inserirAlterar(Estado estado){
		if(estado.getId()==null){
			daoEstado.inserir(estado);
		}else{
			daoEstado.alterar(estado);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoEstado.update(valor);
	}

}
