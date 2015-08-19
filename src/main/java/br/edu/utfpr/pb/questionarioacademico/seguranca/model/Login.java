package br.edu.utfpr.pb.questionarioacademico.seguranca.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.utfpr.pb.questionarioacademico.model.Usuario;


/**
 * 
 * @author trgp
 *
 * Classe que define o objeto a ser persistido em sessão para auntentição na aplicação
 *
 */
@SuppressWarnings("serial")
@SessionScoped
@Named("login")
public class Login implements Serializable{

	private Usuario usuario;
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}