
package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import org.joda.time.LocalDate;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionarioRepository;

@Stateless
public class QuestionarioBusiness extends RepositoryImpl<Questionario, Long>
		implements QuestionarioRepository {

	@Override
	public Questionario liberarQuestionario(Questionario questionario) {
		//setando data atual
		questionario.setDataliberacao(new LocalDate());
		questionario.setStatus(Status.EMCURSO);
		
		questionario = this.entityManager.merge(questionario);
		
		return questionario;
	}

}