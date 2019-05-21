package base.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Estado;
import base.service.EstadoService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("estadoMB")
public class EstadoMB {
	
	private static final long serialVersionUID = 1L;

	private Estado estado;
	private List<Estado> estadoBusca;
	private List<Estado> listaEstado;
	
		
	@Inject
	private GenericDAO<Estado> daoEstado; //faz as buscas
	
	@Inject
	private EstadoService estadoService; // inserir no banco
	
	
	@PostConstruct
	public void inicializar() {
	
		estado = new Estado();
	
		listaEstado = new ArrayList<>();
		listaEstado = daoEstado.listaComStatus(Estado.class);
		estadoBusca = new ArrayList<>();
		
	}
	
	public void preencherListaEstado(Estado t) {
		this.estado = t;

	}

	public void inativarEstado(Estado t) {
		estadoService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {
		try {			
				
				if (estado.getId() == null) {
					estado.setStatus(true);
					estadoService.inserirAlterar(estado);
					

				} else {
					estado.setStatus(true);
					estadoService.inserirAlterar(estado);
					
				}

				criarNovoObjeto();
				
				ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
				FecharDialog.fecharDialogCadastro();
				carregarLista();
			
		} catch (Exception e) {
			ExibirMensagem.exibirMensagem(Mensagem.ERRO);
			e.printStackTrace();
		}

	}
	
	public void criarNovoObjeto() {
		estado = new Estado();
	}

	public void carregarLista() {
		listaEstado = daoEstado.listaComStatus(Estado.class);
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstadoBusca() {
		return estadoBusca;
	}

	public void setEstadoBusca(List<Estado> estadoBusca) {
		this.estadoBusca = estadoBusca;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public GenericDAO<Estado> getDaoEstado() {
		return daoEstado;
	}

	public void setDaoEstado(GenericDAO<Estado> daoEstado) {
		this.daoEstado = daoEstado;
	}

	public EstadoService getEstadoService() {
		return estadoService;
	}

	public void setEstadoService(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
