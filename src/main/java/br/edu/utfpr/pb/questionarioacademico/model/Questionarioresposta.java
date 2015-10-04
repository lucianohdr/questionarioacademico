package br.edu.utfpr.pb.questionarioacademico.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="questionarioresposta")
public class Questionarioresposta extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@Column(name="hashid")
	private String hashid;
	
	@ManyToOne
	private Questionariodisponivel questionariodisponivel;
	
	@OneToMany(mappedBy="questionarioresposta", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Resposta> respostas;
	
	public String getHashid() {
		return hashid;
	}

	public void setHashid(String hashid) {
		this.hashid = hashid;
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