package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name="questionario")
public class Questionario extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	@NotNull
	private Categoriaquestionario categoriaquestionario;
	
	@Column(name="descricao", length=120)
	private String descricao;
	
	@Column(name="observacao", length=255)
	private String observacao;
	
	@Column(name="dataliberacao")
	
	private LocalDate dataliberacao;
	
	@Column(name="datavalidade")
	private LocalDate datavalidade;
	
	public Categoriaquestionario getCategoriaquestionario() {
		return categoriaquestionario;
	}
	public void setCategoriaquestionario(Categoriaquestionario categoriaquestionario) {
		this.categoriaquestionario = categoriaquestionario;
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
	public LocalDate getDataliberacao() {
		return dataliberacao;
	}
	public void setDataliberacao(LocalDate dataliberacao) {
		this.dataliberacao = dataliberacao;
	}
	public LocalDate getDatavalidade() {
		return datavalidade;
	}
	public void setDatavalidade(LocalDate datavalidade) {
		this.datavalidade = datavalidade;
	}
}