package br.edu.utfpr.pb.questionarioacademico.repository;

import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface QuestionarioRepository extends Repository<Questionario, Long> {

	/*
	 * Método responsável por trazer o último Questionario salvo
	 */
	Questionario getLastQuestionario();
}