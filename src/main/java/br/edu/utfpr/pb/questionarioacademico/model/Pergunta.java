package br.edu.utfpr.pb.questionarioacademico.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="pergunta")
public class Pergunta extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	@NotNull
	private Tipopergunta tipopergunta;
	
	@Column(name="nome", length=60)
	private String nome;
	
	@Column(name="descricao", length=120)
	private String descricao;
	
	@OneToMany(mappedBy="pergunta")
	private Set<Alternativa> alternativas;
	
	public Tipopergunta getTipoPergunta() {
		return tipopergunta;
	}

	public void setTipoPergunta(Tipopergunta tipopergunta) {
		this.tipopergunta = tipopergunta;
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