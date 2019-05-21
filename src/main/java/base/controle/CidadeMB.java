package base.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Cidade;
import base.service.CidadeService;
import dao.GenericDAO;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("cidadeMB")
public class CidadeMB {
	
	private static final long serialVersionUID = 1L;

	private Cidade cidade;
	private List<Cidade> cidadeBusca;
	private List<Cidade> listaCidade;
	
		
	@Inject
	private GenericDAO<Cidade> daoCidade; //faz as buscas
	
	@Inject
	private CidadeService cidadeService; // inserir no banco
	
	
	@PostConstruct
	public void inicializar() {
	
		cidade = new Cidade();
	
		listaCidade = new ArrayList<>();
		listaCidade = daoCidade.listaComStatus(Cidade.class);
		cidadeBusca = new ArrayList<>();
		
	}
	
	public void preencherListaCidade(Cidade t) {
		this.cidade = t;

	}

	public void inativarCidade(Cidade t) {
		cidadeService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {
		try {			
				
				if (cidade.getId() == null) {
					cidade.setStatus(true);
					cidadeService.inserirAlterar(cidade);
					

				} else {
					cidade.setStatus(true);
					cidadeService.inserirAlterar(cidade);
					
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
		cidade = new Cidade();
	}

	public void carregarLista() {
		listaCidade = daoCidade.listaComStatus(Cidade.class);
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidadeBusca() {
		return cidadeBusca;
	}

	public void setCidadeBusca(List<Cidade> cidadeBusca) {
		this.cidadeBusca = cidadeBusca;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public GenericDAO<Cidade> getDaoCidade() {
		return daoCidade;
	}

	public void setDaoCidade(GenericDAO<Cidade> daoCidade) {
		this.daoCidade = daoCidade;
	}

	public CidadeService getCidadeService() {
		return cidadeService;
	}

	public void setCidadeService(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
