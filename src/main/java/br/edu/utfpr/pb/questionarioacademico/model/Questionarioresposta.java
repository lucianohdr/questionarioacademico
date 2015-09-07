package br.edu.utfpr.pb.questionarioacademico.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="questionarioresposta")
public class Questionarioresposta extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="cryptid")
	private String cryptid;
	
	@OneToOne
	private Questionariodisponivel questionariodisponivel;
	
	@OneToMany(mappedBy="questionarioresposta", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Resposta> respostas;
	
	public String getCryptid() {
		return cryptid;
	}

	public void setCryptid(String cryptid) {
		this.cryptid = cryptid;
	}

	public Questionariodisponivel getQuestionariodisponivel() {
		return questionariodisponivel;
	}

	public void setQuestionariodisponivel(Questionariodisponivel questionariodisponivel) {
		this.questionariodisponivel = questionariodisponivel;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
}