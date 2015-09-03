
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

	/*@Override 
	public Questionario responder(Questionario questionario, Usuario usuario) {
		Questionario retorno = null;
		Status status = Status.EMCURSO;
		String hql = "select questionario from Questionario questionario"
				+ " left outer join questionario.disciplinas disciplina"
				+ " join disciplina.curso curso"
				+ " join disciplina.professor professor"
				+ " where questionario.id = :idquestionario";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idquestionario", questionario.getId());
		List<Questionario> list = query.getResultList(); 
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}*/
}