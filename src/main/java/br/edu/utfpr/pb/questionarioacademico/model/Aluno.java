package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="aluno")
public class Aluno extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{
	
	@Column(name="ra", length=15)
	private String ra;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Pessoa pessoa;

	@OneToOne
	private Curso curso;
	
	@OneToOne
	private Turma turma;
	
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
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}