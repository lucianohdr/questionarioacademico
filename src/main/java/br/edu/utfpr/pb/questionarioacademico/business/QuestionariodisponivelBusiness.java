package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariodisponivelRepository;

@Stateless
public class QuestionariodisponivelBusiness extends
		RepositoryImpl<Questionariodisponivel, Long> implements
		QuestionariodisponivelRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Questionariodisponivel> porIdquestionario(Long idquestionario) {
		List<Questionariodisponivel> retorno = null;
		String hql = "from Questionariodisponivel questionariodisponivel"
				   + " where questionariodisponivel.questionario.id = :idquestionario";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idquestionario", idquestionario);
		
		retorno = query.getResultList();
		
		return retorno;
	}
}