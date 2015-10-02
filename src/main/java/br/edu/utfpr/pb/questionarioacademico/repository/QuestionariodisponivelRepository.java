package br.edu.utfpr.pb.questionarioacademico.repository;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface QuestionariodisponivelRepository extends
		Repository<Questionariodisponivel, Long> {

	/**
	 * Método responsável por retornar lista de questionariodisponivel pelo idquestionario
	 * @param idquestionario
	 * @return
	 */
	List<Questionariodisponivel> porIdquestionario(Long idquestionario);

	/**
	 * Método responsável por retornar uma lista de questionariodisponivel por usuario
	 * @param usuario
	 * @return
	 */
	List<Questionariodisponivel> porUsuarioEporStatus(Usuario usuario,
			Status status);
	
	/**
	 * Método responsável por retornar os questionarios respondidos por usuário
	 * @param usuario
	 * @return
	 */
	List<Questionariodisponivel> respondidos(Usuario usuario);

}