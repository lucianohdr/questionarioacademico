package br.edu.utfpr.pb.questionarioacademico.repository;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
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
	 * Método responsável por retornar uma lista de questionariodisponivel a serem respondidos por usuario
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
	List<Questionariodisponivel> respondidosPorUsuario(Usuario usuario);
	
	/**
	 * Responsável por trazer as respostas por usuário
	 * Para usuários do tipo admin:</br>
	 *  - Trás respostas de todos os questionarios respondidos;</br>
	 * Para coordenadores:</br>
	 *  - Trás respostas de todos os questionários do curso a que ele é coordenador;</br>
	 * Para chefe departamento:</br>
	 *  - Trás todas as respostas de questionarios dos cursos do departamento a que ele é chefe;</br>
	 * Para professores:</br>
	 * - Trás as respostas de questionarios de 'Avaliação de professor' direcionadas a ele mesmo;
	 * 
	 * @param usuario
	 * @return List<Questionariodisponivel> 
	 */
	List<Questionariodisponivel> porUsuarioEPorPerfil(Perfil perfil);

}