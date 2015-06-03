package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@Table(name="categoriaquestionario")
public class Categoriaquestionario extends br.edu.utfpr.pb.questionarioacademico.model.commons. Entity {

	@Column(name = "nome", nullable=false)
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
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