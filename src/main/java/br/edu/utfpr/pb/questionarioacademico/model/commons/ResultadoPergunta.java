package br.edu.utfpr.pb.questionarioacademico.model.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.pb.questionarioacademico.model.Alternativa;
import br.edu.utfpr.pb.questionarioacademico.model.Pergunta;
import br.edu.utfpr.pb.questionarioacademico.model.Resposta;

public class ResultadoPergunta {
	
	private Pergunta pergunta;
	private List<Resposta> respostas = new ArrayList<Resposta>();
	private Map<Alternativa, Integer> counterAlternativas = new HashMap<Alternativa, Integer>();
	
	public Pergunta getPergunta() {
		return pergunta;
	}
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	public List<Resposta> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
	public Map<Alternativa, Integer> getCounterAlternativas() {
		return counterAlternativas;
	}
	public void setCounterAlternativas(Map<Alternativa, Integer> counterAlternativas) {
		this.counterAlternativas = counterAlternativas;
	}
	
	public void addAlternativa(Alternativa alternativa){
		if(this.counterAlternativas.containsKey(alternativa)){
			Integer counter = this.counterAlternativas.get(alternativa);
			//counter.set
			
			
		}
		this.counterAlternativas.containsKey(alternativa);
		
	}
	
	public void remAlternativa(){
		
	}
}