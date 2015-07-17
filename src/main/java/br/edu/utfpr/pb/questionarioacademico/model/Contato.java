package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="contato")
@SuppressWarnings("serial")
public class Contato extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{
	
	@NotEmpty
	@Column(name="nome")
	private String nome;

	@NotEmpty
	@Column(name="endereco")
	private String endereco;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	@Override
	public String toString() {
		return getNome();
	}

}