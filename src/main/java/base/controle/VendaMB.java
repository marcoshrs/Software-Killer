package base.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import base.modelo.Produto;
import base.modelo.Venda;
import base.service.VendaService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("vendaMB")
public class VendaMB {

	private static final long serialVersionUID = 1L;
	
	private Venda venda;
	private Produto produto;
	private List<Venda> vendaBusca;
	private List<Venda> listaVenda;
	
	@Inject
	private GenericDAO<Venda> daoVenda; //faz as buscas
	
	@Inject
	private VendaService vendaService; // inserir no banco
	
	@Inject
	private EntityManager manager;
	
	@PostConstruct
	public void inicializar() {
	
		venda = new Venda();
	
		listaVenda = new ArrayList<>();
		listaVenda = daoVenda.listaComStatus(Venda.class);
		vendaBusca = new ArrayList<>();
		
	}
	
	public void preencherListaVenda(Venda t) {
		this.venda = t;

	}
	
	
	public void salvar() {
		
		calcular();
		
		try {			
				
				if (venda.getId() == null) {
					venda.setStatus(true);
					vendaService.inserirAlterar(venda);
					

				} else {
					venda.setStatus(true);
					vendaService.inserirAlterar(venda);
					
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
	
	public void calcular() {
		
		venda.setValorTotal(venda.getQuantidade() * venda.getValorVenda());
		
	}
	
	public void diminuirEstoque() {
		
		produto.setQuantidade(produto.getQuantidade() - venda.getQuantidade());
		
	}
	
	public void criarNovoObjeto() {
		venda = new Venda();
	}

	public void carregarLista() {
		listaVenda = daoVenda.listaComStatus(Venda.class);
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getVendaBusca() {
		return vendaBusca;
	}

	public void setVendaBusca(List<Venda> vendaBusca) {
		this.vendaBusca = vendaBusca;
	}

	public List<Venda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}

	public GenericDAO<Venda> getDaoVenda() {
		return daoVenda;
	}

	public void setDaoVenda(GenericDAO<Venda> daoVenda) {
		this.daoVenda = daoVenda;
	}

	public VendaService getVendaService() {
		return vendaService;
	}

	public void setVendaService(VendaService vendaService) {
		this.vendaService = vendaService;
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
