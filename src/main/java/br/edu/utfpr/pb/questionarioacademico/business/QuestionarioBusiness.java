package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionarioRepository;

@Stateless
public class QuestionarioBusiness extends RepositoryImpl<Questionario, Long>
		implements QuestionarioRepository {

}