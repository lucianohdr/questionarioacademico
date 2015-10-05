package br.edu.utfpr.pb.questionarioacademico.model.commons;

import br.edu.utfpr.pb.questionarioacademico.model.Alternativa;

public class AlternativaCounter {
	
	private Alternativa alternativa;
	private Integer counter;
	
	public Alternativa getAlternativa() {
		return alternativa;
	}
	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
}