package br.edu.utfpr.pb.questionarioacademico.repository;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface QuestionarioRepository extends Repository<Questionario, Long> {

	/**
	 * Método responsável por atualizar o status do questionario e colocar a data de liberacao
	 * @param questionario
	 * @return
	 */
	Questionario liberarQuestionario(Questionario questionario);

	/**
	 * Método responsável por retornar uma lista de questionario por usuario
	 * @param usuario
	 * @return
	 */
	List<Questionario> porUsuarioEporStatus(Usuario usuario, Status status);

	/**
	 * Método responsável por retornar o questionário com somentes os dados pertinentes a aquele usuário
	 * @param questionario
	 * @param usuario
	 * @return
	 */
	Questionario responder(Questionario questionario, Usuario usuario);
	
}