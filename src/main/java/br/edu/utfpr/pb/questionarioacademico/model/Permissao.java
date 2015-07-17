package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="permissao")
public class Permissao extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="responderquestionario")
	private Boolean responderQuestionario;
	
	@Column(name="visualizarquestionario")
	private Boolean visualizarQuestionario;

	public Boolean getResponderQuestionario() {
		return responderQuestionario;
	}

	public void setResponderQuestionario(Boolean responderQuestionario) {
		this.responderQuestionario = responderQuestionario;
	}

	public Boolean getVisualizarQuestionario() {
		return visualizarQuestionario;
	}

	public void setVisualizarQuestionario(Boolean visualizarQuestionario) {
		this.visualizarQuestionario = visualizarQuestionario;
	}
}