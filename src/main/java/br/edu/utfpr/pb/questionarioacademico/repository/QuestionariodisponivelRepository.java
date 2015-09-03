package br.edu.utfpr.pb.questionarioacademico.repository;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface QuestionariodisponivelRepository extends
		Repository<Questionariodisponivel, Long> {

	/**
	 * Método responsável por retornar lista de questionariodisponivel pelo idquestionario
	 * @param idquestionario
	 * @return
	 */
	List<Questionariodisponivel> porIdquestionario(Long idquestionario);

}
