package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Questionarioresposta;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariorespostaRepository;

@Stateless
public class QuestionariorespostaBusiness extends
		RepositoryImpl<Questionarioresposta, Long> implements
		QuestionariorespostaRepository {
}