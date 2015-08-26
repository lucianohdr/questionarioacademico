package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tela")
public class Tela extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{
	
	@Column(name="nome", length=60)
	private String nome;
	
	@Column(name="nomeexibicao")
	private String nomeExibicao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeExibicao() {
		return nomeExibicao;
	}

	public void setNomeExibicao(String nomeExibicao) {
		this.nomeExibicao = nomeExibicao;
	}
}