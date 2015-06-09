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
	
	@Column(name="identificacao", length=50)
	private String identificacao;
	
	@Column(name="email", length=150, nullable=false)
	private String email;
	
	@Column(name="nome", length=200, nullable=false)
	private String nome;
	
	@Column(name="login", length=50, nullable=false)
	private String login;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	public Tipousuario getTipousuario() {
		return tipousuario;
	}
	public void setTipousuario(Tipousuario tipousuario) {
		this.tipousuario = tipousuario;
	}
	public String getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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