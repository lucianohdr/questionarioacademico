package br.edu.utfpr.pb.questionarioacademico.repository;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.model.Pergunta;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface PerguntaRepository extends Repository<Pergunta, Long> {

	List<Pergunta> perguntasPorQuestionario(long idquestionario);
	
	void setPerguntaInAlternativa(Pergunta pergunta);
}