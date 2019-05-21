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

import base.modelo.Usuario;
import base.service.UsuarioService;
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.EnviarEmail;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("usuarioMB")
public class UsuarioMB {
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> usuarioBusca;
	private List<Usuario> listaUsuario;
	private String senhaConfirmacao;
	
	private EnviarEmail enviarEmail;
		
	@Inject
	private GenericDAO<Usuario> daoUsuario; //faz as buscas
	
	@Inject
	private UsuarioService usuarioService; // inserir no banco
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private EnviarEmail email;
	
	private Usuario envioUsuario;
	
	
	@PostConstruct
	public void inicializar() {
	
		usuario = new Usuario();
	
		listaUsuario = new ArrayList<>();
		listaUsuario = daoUsuario.listaComStatus(Usuario.class);
		usuarioBusca = new ArrayList<>();
		
	}

	public void preencherListaUsuario(Usuario t) {
		this.usuario = t;

	}

	public void inativarUsuario(Usuario t) {
		usuarioService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {
		enviarEmail();
		try {			
				
				if (usuario.getId() == null) {
					usuario.setStatus(true);
					usuarioService.inserirAlterar(usuario);
					

				} else {
					usuario.setStatus(true);
					usuarioService.inserirAlterar(usuario);
					
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
		usuario = new Usuario();
	}

	public void carregarLista() {
		listaUsuario = daoUsuario.listaComStatus(Usuario.class);
	}
	
	public void envioEmail(Usuario u) {
		envioUsuario = u;
	}
	
	public void enviarEmail() {
		
		enviarEmail = new EnviarEmail();
		
		enviarEmail.enviarEmail(usuario.getEmail(), "Confirmação de cadastro", 
				"Email: "+usuario.getEmail()+" Senha: "+usuario.getSenha());	
		
	}
	
	
	public void imprimirRelatorioUsuario() { 
		try {
			List<Usuario> relatorio = daoUsuario.listar(Usuario.class, "status = true");
			if (relatorio.size() > 0) {

				HashMap parametro = new HashMap<>();
				
				ChamarRelatorio ch = new ChamarRelatorio("usuario.jasper", parametro, "relatorio_usuario");
				Session sessions = manager.unwrap(Session.class);
				sessions.doWork(ch);

			} else {
				ExibirMensagem.exibirMensagem(Mensagem.NADA_ENCONTRADO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExibirMensagem.exibirMensagem(Mensagem.ERRO);
		}
	}
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioBusca() {
		return usuarioBusca;
	}

	public void setUsuarioBusca(List<Usuario> usuarioBusca) {
		this.usuarioBusca = usuarioBusca;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public GenericDAO<Usuario> getDaoTipo() {
		return daoUsuario;
	}

	public void setDaoTipo(GenericDAO<Usuario> daoTipo) {
		this.daoUsuario = daoTipo;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EnviarEmail getEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(EnviarEmail enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public GenericDAO<Usuario> getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(GenericDAO<Usuario> daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public EnviarEmail getEmail() {
		return email;
	}

	public void setEmail(EnviarEmail email) {
		this.email = email;
	}

	public Usuario getEnvioUsuario() {
		return envioUsuario;
	}

	public void setEnvioUsuario(Usuario envioUsuario) {
		this.envioUsuario = envioUsuario;
	}

}
