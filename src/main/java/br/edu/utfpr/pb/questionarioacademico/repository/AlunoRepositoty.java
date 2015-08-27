package br.edu.utfpr.pb.questionarioacademico.repository;

import br.edu.utfpr.pb.questionarioacademico.model.Aluno;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface AlunoRepositoty extends Repository<Aluno, Long>{

	/**
	 * Retorna um objeto Aluno, passando como paramentro um objeto usuario com pelo menos o id carregado
	 * @param usuario
	 * @return
	 */
	Aluno alunoPorUsuario(Usuario usuario);

}
