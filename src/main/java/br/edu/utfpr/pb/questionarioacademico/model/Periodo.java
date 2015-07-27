package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="periodo")
public class Periodo extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="nome")
	private String nome;
}