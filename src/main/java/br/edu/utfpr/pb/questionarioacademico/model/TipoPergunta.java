package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tipopergunta")
public class TipoPergunta extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="nome", length=60)
	private String nome;
	
	@Column(name="descricao", length=120)
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}