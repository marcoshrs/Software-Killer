package base.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import base.modelo.Fornecedor;
import base.modelo.Usuario;
import base.service.FornecedorService;
import base.service.UsuarioService;
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.EnviarEmail;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("fornecedorMB")
public class FornecedorMB {
	
	private static final long serialVersionUID = 1L;
	
	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedorBusca;
	private List<Fornecedor> listaFornecedor;
	
	@Inject
	private GenericDAO<Fornecedor> daoFornecedor; //faz as buscas
	
	@Inject
	private FornecedorService fornecedorService; // inserir no banco
	
	@Inject
	private EntityManager manager;
	
	@PostConstruct
	public void inicializar() {
	
		fornecedor = new Fornecedor();
	
		listaFornecedor = new ArrayList<>();
		listaFornecedor = daoFornecedor.listaComStatus(Fornecedor.class);
		fornecedorBusca = new ArrayList<>();
		
	}
	
	public void preencherListaFornecedor(Fornecedor t) {
		this.fornecedor = t;

	}
	
	
	public void salvar() {
		try {			
				
				if (fornecedor.getId() == null) {
					fornecedor.setStatus(true);
					fornecedorService.inserirAlterar(fornecedor);
					

				} else {
					fornecedor.setStatus(true);
					fornecedorService.inserirAlterar(fornecedor);
					
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
		fornecedor = new Fornecedor();
	}

	public void carregarLista() {
		listaFornecedor = daoFornecedor.listaComStatus(Fornecedor.class);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedorBusca() {
		return fornecedorBusca;
	}

	public void setFornecedorBusca(List<Fornecedor> fornecedorBusca) {
		this.fornecedorBusca = fornecedorBusca;
	}

	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public GenericDAO<Fornecedor> getDaoFornecedor() {
		return daoFornecedor;
	}

	public void setDaoFornecedor(GenericDAO<Fornecedor> daoFornecedor) {
		this.daoFornecedor = daoFornecedor;
	}

	public FornecedorService getFornecedorService() {
		return fornecedorService;
	}

	public void setFornecedorService(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
}
