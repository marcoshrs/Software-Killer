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
import base.modelo.Produto;
import base.modelo.Usuario;
import base.modelo.Venda;
import base.service.FornecedorService;
import base.service.ProdutoService;
import base.service.UsuarioService;
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.EnviarEmail;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("produtoMB")
public class ProdutoMB {
	
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private Venda venda;
	private List<Produto> produtoBusca;
	private List<Produto> listaProduto;
	
	@Inject
	private GenericDAO<Produto> daoProduto; //faz as buscas
	
	@Inject
	private ProdutoService produtoService; // inserir no banco
	
	@Inject
	private EntityManager manager;
	
	@PostConstruct
	public void inicializar() {
	
		produto = new Produto();
	
		listaProduto = new ArrayList<>();
		listaProduto = daoProduto.listaComStatus(Produto.class);
		produtoBusca = new ArrayList<>();
		
	}
	
	
	public void preencherListaProduto(Produto t) {
		this.produto = t;

	}
	
	
	public void salvar() {
		try {			
				
				if (produto.getId() == null) {
					produto.setStatus(true);
					produtoService.inserirAlterar(produto);
					

				} else {
					produto.setStatus(true);
					produtoService.inserirAlterar(produto);
					
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
		produto = new Produto();
	}

	public void carregarLista() {
		listaProduto = daoProduto.listaComStatus(Produto.class);
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<Produto> getProdutoBusca() {
		return produtoBusca;
	}


	public void setProdutoBusca(List<Produto> produtoBusca) {
		this.produtoBusca = produtoBusca;
	}


	public List<Produto> getListaProduto() {
		return listaProduto;
	}


	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}


	public GenericDAO<Produto> getDaoProduto() {
		return daoProduto;
	}


	public void setDaoProduto(GenericDAO<Produto> daoProduto) {
		this.daoProduto = daoProduto;
	}


	public ProdutoService getProdutoService() {
		return produtoService;
	}


	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
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
