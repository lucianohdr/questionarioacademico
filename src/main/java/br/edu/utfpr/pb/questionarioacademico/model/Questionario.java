package br.edu.utfpr.pb.questionarioacademico.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;
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
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="alunorespondido",
			joinColumns=@JoinColumn(name="idaluno"),
			inverseJoinColumns=@JoinColumn(name="idquestionario"))
	private Set<Aluno> alunosRespondidos;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="professorrespondido",
	joinColumns=@JoinColumn(name="idprofessor"),
	inverseJoinColumns=@JoinColumn(name="idquestionario"))
	private Set<Professor> professoresRespondidos;
	
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
	public Set<Aluno> getAlunosRespondidos() {
		return alunosRespondidos;
	}
	public void setAlunosRespondidos(Set<Aluno> alunosRespondidos) {
		this.alunosRespondidos = alunosRespondidos;
	}
	public Set<Professor> getProfessoresRespondidos() {
		return professoresRespondidos;
	}
	public void setProfessoresRespondidos(Set<Professor> professoresRespondidos) {
		this.professoresRespondidos = professoresRespondidos;
	}
}