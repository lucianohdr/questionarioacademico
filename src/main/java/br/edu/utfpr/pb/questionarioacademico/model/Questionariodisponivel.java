package br.edu.utfpr.pb.questionarioacademico.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="questionariodisponivel")
public class Questionariodisponivel extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	private Disciplina disciplina;
	
	@ManyToOne
	private Questionario questionario;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="alunorespondido",
			joinColumns=@JoinColumn(name="idquestionariodisponivel"),
			inverseJoinColumns=@JoinColumn(name="idaluno"))
	private Set<Aluno> alunosRespondidos;
	
	public void addAluno(Aluno aluno){
		getAlunosRespondidos().add(aluno);
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="professorrespondido",
	joinColumns=@JoinColumn(name="idquestionariodisponivel"),
	inverseJoinColumns=@JoinColumn(name="idprofessor"))	
	private Set<Professor> professoresRespondidos;
	
	public void addProfessor(Professor professor){
		getProfessoresRespondidos().add(professor);
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
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