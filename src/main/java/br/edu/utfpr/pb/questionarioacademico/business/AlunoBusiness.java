package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Aluno;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.AlunoRepositoty;

@Stateless
public class AlunoBusiness extends RepositoryImpl<Aluno, Long> implements AlunoRepositoty{

	@Override
	public Aluno alunoPorUsuario(Usuario usuario) {
		Aluno aluno = null;
		String hql = "from Aluno"
				+ " where pessoa.usuario.id = :id";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("id", usuario.getId());
		
		List<Aluno> alunos = query.getResultList(); 
		if(!alunos.isEmpty()){
			aluno = (Aluno) alunos.get(0);
		}
		return aluno;
	}

}