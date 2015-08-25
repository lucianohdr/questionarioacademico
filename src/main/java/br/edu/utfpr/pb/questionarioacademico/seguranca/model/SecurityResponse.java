package br.edu.utfpr.pb.questionarioacademico.seguranca.model;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.model.Usuario;

/**
 * 
 * Classe que define o DTO para response das requisições de autenticação e autorização da aplicação
 *
 */
public class SecurityResponse {

	private boolean authenticated;
	private String message;
	private Usuario  usuario;
	private List<String> roles;
	
	public SecurityResponse(boolean authenticated, String message, Usuario  usuario, List<String> roles) {
		this.authenticated = authenticated;
		this.message = message;
		this.usuario = usuario;
		this.roles = roles;
	}
	
	public SecurityResponse(boolean authenticated, String message, Usuario  usuario) {
		this.authenticated = authenticated;
		this.message = message;
		this.usuario = usuario;
	}
	
	public SecurityResponse(boolean authenticated, String message) {
		this.authenticated = authenticated;
		this.message = message;
	}
	
	public boolean isAuthenticated() {
		return authenticated;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<String> getRoles(){
		return roles;
	}
}