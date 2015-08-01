package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Pergunta;
import br.edu.utfpr.pb.questionarioacademico.repository.PerguntaRepository;

@Stateless
public class PerguntaBusiness extends RepositoryImpl<Pergunta, Long> implements
		PerguntaRepository {

	@Override
	public List<Pergunta> perguntasPorQuestionario(long idquestionario) {
		String hql = "from Pergunta pergunta "
				+ "inner join pergunta";
		return null;
	}
}