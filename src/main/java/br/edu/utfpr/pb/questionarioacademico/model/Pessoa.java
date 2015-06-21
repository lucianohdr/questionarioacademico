package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="pessoa")
public class Pessoa extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="nome", length=60, nullable=false)
	private String nome;
	
	@Column(name="email", length=60, nullable=false)
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Usuario usuario;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}