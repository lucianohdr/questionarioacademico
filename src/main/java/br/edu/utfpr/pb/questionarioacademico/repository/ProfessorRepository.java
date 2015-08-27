package br.edu.utfpr.pb.questionarioacademico.repository;

import br.edu.utfpr.pb.questionarioacademico.model.Professor;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface ProfessorRepository extends Repository<Professor, Long>{

	/**
	 * Retorna um professor passando como parametro um usuario cm pelo menos o id carregado
	 * @param usuario
	 * @return
	 */
	Professor professorPorUsuario(Usuario usuario);

}
