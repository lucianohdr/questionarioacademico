package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Disciplina;
import br.edu.utfpr.pb.questionarioacademico.repository.DisciplinaRepository;

@Stateless
public class DisciplinaBusiness extends RepositoryImpl<Disciplina, Long>
		implements DisciplinaRepository {

	@Override
	public List<Disciplina> disciplinaPorCurso(Long idcurso){
		List<Disciplina> returno = new ArrayList<Disciplina>();
		
		String hql = "from Disciplina where curso.id = :idcurso";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idcurso", idcurso);
		
		if(!query.getResultList().isEmpty()){
			returno = query.getResultList();
		} 
		return returno;
	}
}