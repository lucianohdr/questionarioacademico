package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="turma")
public class Turma extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	@NotNull
	private Curso curso;
	
	@OneToOne
	@NotNull
	private Periodo periodo;
	
	@Column(name="nome", length=60)
	private String nome;
	
	@Column(name="descricao", length=120)
	private String descricao;
	
	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}