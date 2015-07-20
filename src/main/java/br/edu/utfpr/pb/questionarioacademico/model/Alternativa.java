package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="alternativa")
public class Alternativa extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{
	
	@ManyToOne
	private Pergunta pergunta;
	
	@Column(name="descricao")
	private String descricao;
}