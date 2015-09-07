package br.edu.utfpr.pb.questionarioacademico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Entity
@Table(name="resposta")
public class Resposta extends br.edu.utfpr.pb.questionarioacademico.model.commons.Entity{

	@OneToOne
	private Pergunta pergunta;
	
	@OneToOne
	private Alternativa alternativa;
	
	@ManyToOne
	private Questionarioresposta questionarioresposta;
	
	@Type(type="encryptedString")
	@Column(name="respostadescritiva")
	private String respostadescritiva;


	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public Questionarioresposta getQuestionarioresposta() {
		return questionarioresposta;
	}

	public void setQuestionarioresposta(Questionarioresposta questionarioresposta) {
		this.questionarioresposta = questionarioresposta;
	}

	public String getRespostadescritiva() {
		return respostadescritiva;
	}

	public void setRespostadescritiva(String respostadescritiva) {
		this.respostadescritiva = respostadescritiva;
	}
}