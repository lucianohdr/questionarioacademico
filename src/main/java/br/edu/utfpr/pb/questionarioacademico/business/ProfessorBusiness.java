package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Professor;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.ProfessorRepository;

@Stateless
public class ProfessorBusiness extends RepositoryImpl<Professor, Long> implements ProfessorRepository {

	@Override
	public Professor professorPorUsuario(Usuario usuario) {
		Professor professor = null;
		String hql = "from Professor"
				+ " where pessoa.usuario.id = :id";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("id", usuario.getId());
		
		List<Professor> professors = query.getResultList(); 
		if(!professors.isEmpty()){
			professor = (Professor) professors.get(0);
		}
		return professor;
	}
}