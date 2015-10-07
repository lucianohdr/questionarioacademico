package br.edu.utfpr.pb.questionarioacademico.repository;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.model.Questionarioresposta;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.model.commons.Resultado;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface QuestionariorespostaRepository extends
		Repository<Questionarioresposta, Long> {

	/**
	 * Responsável por retornar as respostas passando como parametro o idquestionariodisponivel
	 * @param idquestionariodisponivel
	 * @return List<Questionarioresposta>
	 */
	List<Questionarioresposta> respostasPorIdQuestDisponivel(Long idquestionariodisponivel);

	/**
	 * Carrega dados dos resultados das respostas
	 * @param idquestionariodisponivel
	 * @return
	 */
	Resultado carregaResultado(List<Questionarioresposta> questionariorespostas);

	Questionarioresposta repostaPorUsuarioEidquestionariodisponivel(
			Long idquestionariodisponivel,
			Usuario usuario);

}