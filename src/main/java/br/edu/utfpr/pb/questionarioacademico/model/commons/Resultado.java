package br.edu.utfpr.pb.questionarioacademico.model.commons;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;

public class Resultado {

	private Questionariodisponivel questionariodisponivel;
	private List<ResultadoPergunta> resultadoRespostas;
	private Integer countUsuariosResp;
	
	public Questionariodisponivel getQuestionariodisponivel() {
		return questionariodisponivel;
	}
	public void setQuestionariodisponivel(
			Questionariodisponivel questionariodisponivel) {
		this.questionariodisponivel = questionariodisponivel;
	}
	public List<ResultadoPergunta> getResultadoRespostas() {
		return resultadoRespostas;
	}
	public void setResultadoRespostas(List<ResultadoPergunta> resultadoRespostas) {
		this.resultadoRespostas = resultadoRespostas;
	}
	public Integer getCountUsuariosResp() {
		return countUsuariosResp;
	}
	public void setCountUsuariosResp(Integer countUsuariosResp) {
		this.countUsuariosResp = countUsuariosResp;
	}
}