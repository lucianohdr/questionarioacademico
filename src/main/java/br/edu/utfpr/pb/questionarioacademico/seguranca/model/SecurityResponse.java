package br.edu.utfpr.pb.questionarioacademico.seguranca.model;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.model.Tela;
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
	private List<Tela> telas;
	private boolean hasAdmin;
	
	public SecurityResponse(boolean authenticated, Usuario  usuario, List<String> roles, List<Tela> telas, boolean hasAdmin) {
		this.authenticated = authenticated;
		this.usuario = usuario;
		this.roles = roles;
		this.telas = telas;
		this.hasAdmin = hasAdmin;
	}
	
	public SecurityResponse(boolean authenticated, Usuario  usuario, List<String> roles, List<Tela> telas) {
		this.authenticated = authenticated;
		this.usuario = usuario;
		this.roles = roles;
		this.telas = telas;
	}
	
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

	public List<Tela> getTelas() {
		return telas;
	}

	public void setTelas(List<Tela> telas) {
		this.telas = telas;
	}

	public boolean isHasAdmin() {
		return hasAdmin;
	}

	public void setHasAdmin(boolean hasAdmin) {
		this.hasAdmin = hasAdmin;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}