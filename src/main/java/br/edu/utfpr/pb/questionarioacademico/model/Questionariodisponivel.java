package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="questionariodisponivels")
public class Questionariodisponivel extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	private Disciplina disciplina;
	
	@ManyToOne
	private Questionario questionario;

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
}