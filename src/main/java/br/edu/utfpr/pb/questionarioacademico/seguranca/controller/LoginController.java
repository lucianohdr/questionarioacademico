package br.edu.utfpr.pb.questionarioacademico.seguranca.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.utfpr.pb.questionarioacademico.model.Tela;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.UsuarioRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.Hasher;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.Login;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.SecurityResponse;

/**
 * 
 * Classe que define recurso público para logar e deslogar da aplicação
 *
 */
@SuppressWarnings("serial")
@Controller
@Path("authentication")
public class LoginController extends br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller{

	private Login login;
	
	private UsuarioRepository repository;
	
	@Inject
	public LoginController(Result result, UsuarioRepository repository, Login login) {
		super(result);
		this.repository = repository;
		this.login = login;
	}
	
	/*CDI Only*/
	protected LoginController() {
		this(null, null, null);
	}
	
	@Post
	@Path("/login/")
	@Consumes("application/json")
	public void login(Usuario usuario) {
		boolean authenticated = false; 
		boolean hasAdmin = repository.hasAdmin();
		Usuario u = repository.getByUsernameAndPassword(
			usuario.getLogin(), 
			Hasher.get(usuario.getSenha()
		));
		
		List<String> roles = null;
		List<Tela> telas = null;
		if (u != null) {
			login.setUsuario(u);
			authenticated = true;
			roles = repository.getRoles(login.getUsuario());
			telas = repository.getTelas(login.getUsuario());
		} else {
			authenticated = false;
		}
		serializer(new SecurityResponse(authenticated, u, roles, telas, hasAdmin)).exclude("usuario.senha", "usuario.perfis").serialize();
	}

	@Get
	@Path("/logout/")
	public void logout() {
		login.setUsuario(null);
		serializer(new SecurityResponse(false, "NOT_AUTHENTICATED")).serialize();
	}
	
	@Get
	@Path("/identity")
	public void identity(){
		boolean authenticated = false; 
		boolean hasAdmin = repository.hasAdmin();
		Usuario usuario = login.getUsuario();
		
		if(usuario != null){
			authenticated = true;
			
			List<String> roles = repository.getRoles(login.getUsuario());
			List<Tela> telas = repository.getTelas(login.getUsuario());
			
			serializer(new SecurityResponse(authenticated, 
											login.getUsuario(),
											roles,
											telas,
											hasAdmin)).exclude("usuario.senha", "usuario.perfis").serialize();
		} else {
			serializer(new SecurityResponse(authenticated, usuario, null, null, hasAdmin)).exclude("usuario.senha", "usuario.perfis").serialize();
		}
	}
}