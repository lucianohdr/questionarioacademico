package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="perfil")
public class Perfil extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{
	
	@Column(name="nome", nullable=false, length=200)
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="observacao")
	private String observacao;
	
	public Perfil(String descricao){
		switch (descricao) {
		case "ADMINISTRADOR":
			super.setId(Long.valueOf(1));
			break;
		case "COORDENADOR":
			super.setId(Long.valueOf(2));
			break;
		case "CHEFE":
			super.setId(Long.valueOf(3));
			break;
		case "PROFESSOR":
			super.setId(Long.valueOf(4));
			break;
		case "ALUNO":
			super.setId(Long.valueOf(5));
			break;
		}
	}

	/*hibernate only*/
	public Perfil(){
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
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}