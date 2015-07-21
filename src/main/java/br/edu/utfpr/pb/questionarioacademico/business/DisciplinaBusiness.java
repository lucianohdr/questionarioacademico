package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Disciplina;
import br.edu.utfpr.pb.questionarioacademico.repository.DisciplinaRepository;

@Stateless
public class DisciplinaBusiness extends RepositoryImpl<Disciplina, Long>
		implements DisciplinaRepository {

}
