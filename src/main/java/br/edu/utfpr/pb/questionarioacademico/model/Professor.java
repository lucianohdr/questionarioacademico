package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="professor")
public class Professor extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="matricula", length=10)
	private String matricula;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Pessoa pessoa;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}