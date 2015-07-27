package br.edu.utfpr.pb.questionarioacademico.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="usuario")
public class Usuario extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="login", length=60, nullable=false)
	private String login;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="usuario_perfil",
			joinColumns=@JoinColumn(name="id_usuario"),
			inverseJoinColumns=@JoinColumn(name="id_perfil"))
	private Set<Perfil> perfis;

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
	
	public Set<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}
}