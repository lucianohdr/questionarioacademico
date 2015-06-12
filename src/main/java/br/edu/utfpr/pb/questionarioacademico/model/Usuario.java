package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="usuario")
public class Usuario extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	private Tipousuario tipousuario;
	
	@Column(name="login", length=60, nullable=false)
	private String login;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	public Tipousuario getTipousuario() {
		return tipousuario;
	}
	public void setTipousuario(Tipousuario tipousuario) {
		this.tipousuario = tipousuario;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}