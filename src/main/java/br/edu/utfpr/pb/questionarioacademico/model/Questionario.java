package br.edu.utfpr.pb.questionarioacademico.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;

import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;

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
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	//inicializando status como salvo
	public Questionario(){
		this.status = Status.SALVO;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="composicaoquestionario",
	joinColumns=@JoinColumn(name="idquestionario"),
	inverseJoinColumns=@JoinColumn(name="idpergunta"))
	private List<Pergunta> perguntas;
	
	public void addPergunta(Pergunta pergunta){
		getPerguntas().add(pergunta);
	}
	
	public void rmPergunta(Pergunta pergunta){
		getPerguntas().remove(pergunta);
	}
	
	@OneToMany(mappedBy="questionario", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Questionariodisponivel> questionariodisponivels;
	
	public List<Questionariodisponivel> getQuestionariodisponivels() {
		return questionariodisponivels;
	}

	public void setQuestionariodisponivels(
			List<Questionariodisponivel> questionariodisponivels) {
		this.questionariodisponivels = questionariodisponivels;
	}

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
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDatavalidade(LocalDate datavalidade) {
		this.datavalidade = datavalidade;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
}