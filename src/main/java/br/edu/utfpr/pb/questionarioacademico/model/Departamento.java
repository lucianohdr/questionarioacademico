package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="departamento")
public class Departamento extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	private Professor professor;
	
	@Column(name="nome", length=60)
	private String nome;
	
	@Column(name="descricao", length=120)
	private String descricao;
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
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