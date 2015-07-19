package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@SuppressWarnings("serial")
@Entity
@Table(name="aluno")
public class Aluno extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{
	
	@Column(name="ra", length=15)
	private String ra;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Pessoa pessoa;
	
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}