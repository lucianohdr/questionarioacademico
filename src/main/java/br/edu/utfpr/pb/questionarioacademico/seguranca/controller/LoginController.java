package br.edu.utfpr.pb.questionarioacademico.seguranca.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
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
		String message = "";
		Usuario u = repository.getByUsernameAndPassword(
			usuario.getLogin(), 
			Hasher.get(usuario.getSenha()
		));
		
		if (u != null) {
			login.setUsuario(u);
			authenticated = true;
			message = "AUTHENTICATED";
		} else {
			authenticated = false;
			message = "NOT_AUTHENTICATED";
		}
		serializer(new SecurityResponse(authenticated, message)).serialize();
	}

	@Get
	@Path("/logout/")
	public void logout() {
		login.setUsuario(null);
		serializer(new SecurityResponse(false, "NOT_AUTHENTICATED")).serialize();
	}
}