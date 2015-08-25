package br.edu.utfpr.pb.questionarioacademico.seguranca.regras;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.Login;

@RequestScoped
public class LoggedAccessRule implements CustomBrutauthRule{

	private Login login;
	
	/**
     * @deprecated CDI eyes only
     */
	protected LoggedAccessRule() {
		this(null);
	}
	
	@Inject
	public LoggedAccessRule(Login login){
		this.login = login;
	}
	
	public boolean isAllowed(){
		return login.getUsuario() != null;
	}
}