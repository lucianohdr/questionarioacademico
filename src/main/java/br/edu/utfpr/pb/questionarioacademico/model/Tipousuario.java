package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tipousuario")
public class Tipousuario extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity {
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtipousuario", nullable=false)
	private long idtipousuario;*/
	
	@Column(name="nome", nullable=false, length=200)
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="observacao")
	private String observacao;
	
	/*public long getIdtipousuario() {
		return idtipousuario;
	}
	public void setIdtipousuario(long idtipousuario) {
		this.idtipousuario = idtipousuario;
	}*/
	
	
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