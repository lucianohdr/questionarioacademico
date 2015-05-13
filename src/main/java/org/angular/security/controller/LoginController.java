/*package org.angular.security.controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.utfpr.pb.questionarioacademico.controller.commons.Controller;
import br.edu.utfpr.pb.questionarioacademico.seguranca.Hasher;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.Login;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.SecurityResponse;

*//**
 * 
 * @author trgp
 *
 * Classe que define recurso público para logar e deslogar da aplicação
 *
 *//*
@br.com.caelum.vraptor.Controller
@Path("authentication")
public class LoginController extends Controller {

	private Login login;
	
	private UsuarioRepository repository;
	
	public LoginController(Result result, UsuarioRepository repository, Login login) {
		super(result);
		this.repository = repository;
		this.login = login;
	}
	
	@Post
	@Path("/login/")
	@Consumes("application/json")
	public void login(Usuario usuario) {
		boolean authenticated = false; String message = "";
		Usuario u = repository.getByUsernameAndPassword(
			usuario.getUsername(), 
			Hasher.get(usuario.getPassword())
		);
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
*/