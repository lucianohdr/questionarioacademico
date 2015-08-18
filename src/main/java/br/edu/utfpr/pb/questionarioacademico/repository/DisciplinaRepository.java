package br.edu.utfpr.pb.questionarioacademico.repository;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.model.Disciplina;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface DisciplinaRepository extends Repository<Disciplina, Long> {

	/**
	 * Método responsavel por retornar as disciplinas filtradas por curso
	 * @param idcurso
	 * @return
	 */
	List<Disciplina> disciplinaPorCurso(Long idcurso);

}
