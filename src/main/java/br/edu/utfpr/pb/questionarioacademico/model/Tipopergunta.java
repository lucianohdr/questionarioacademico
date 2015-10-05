package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tipopergunta")
public class Tipopergunta extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="nome", length=60)
	private String nome;
	
	@Column(name="descricao", length=120)
	private String descricao;
	
	public Tipopergunta(String descricao){
		switch (descricao) {
		case "DESCRITIVA":
			super.setId(Long.valueOf(1));
			break;
		case "MULTIPLA":
			super.setId(Long.valueOf(2));
			break;
		}
	}
	
	/*hibernate*/
	public Tipopergunta() {
	
	}
	
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