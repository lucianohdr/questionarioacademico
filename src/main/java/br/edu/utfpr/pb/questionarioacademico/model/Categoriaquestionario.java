package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="categoriaquestionario")
public class Categoriaquestionario extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	/*@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_idcategoriaquestionario")
	private Long id;*/
	
	@Column(name = "nome", nullable=false)
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	/*public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
}