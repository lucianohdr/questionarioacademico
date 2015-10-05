package br.edu.utfpr.pb.questionarioacademico.model.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.pb.questionarioacademico.model.Alternativa;
import br.edu.utfpr.pb.questionarioacademico.model.Pergunta;
import br.edu.utfpr.pb.questionarioacademico.model.Resposta;

public class ResultadoResposta {
	
	private Pergunta pergunta;
	private List<Resposta> respostas = new ArrayList<Resposta>();
	private List<AlternativaCounter> alternativaCounters = new ArrayList<AlternativaCounter>();
	
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
	
	public List<AlternativaCounter> getAlternativaCounters() {
		return alternativaCounters;
	}

	public void setAlternativaCounters(List<AlternativaCounter> alternativaCounters) {
		this.alternativaCounters = alternativaCounters;
	}

	public void addAlternativa(Alternativa alternativa){
		if(containsAlternativa(alternativa)){
			AlternativaCounter alternativaCounter = getAlternativaCounterPorAlt(alternativa);
			Integer counter = alternativaCounter.getCounter();
			alternativaCounter.setCounter(++counter);
			
		} else {
			AlternativaCounter alternativaCounter = new AlternativaCounter();
			alternativaCounter.setAlternativa(alternativa);
			Integer counter = 1;
			alternativaCounter.setCounter(counter);
			alternativaCounters.add(alternativaCounter);
		}
	}
	
	public boolean remAlternativa(Alternativa alternativa){
		boolean retorno = false;
		if(containsAlternativa(alternativa)){
			AlternativaCounter alternativaCounter = getAlternativaCounterPorAlt(alternativa);
			Integer counter = alternativaCounter.getCounter();
			alternativaCounter.setCounter(counter--);
			retorno = true;
		}
		return retorno;
	}
	
	public boolean containsAlternativa(Alternativa alternativa){
		boolean contains = false;
		for (AlternativaCounter alternativaCounter : alternativaCounters) {
			if(alternativaCounter.getAlternativa().equals(alternativa)){
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	public AlternativaCounter getAlternativaCounterPorAlt(Alternativa alternativa){
		AlternativaCounter retorno = null;
		for (AlternativaCounter alternativaCounter : alternativaCounters) {
			if(alternativaCounter.getAlternativa().equals(alternativa)){
				retorno = alternativaCounter;
			}
		}
		return retorno;
	}
}