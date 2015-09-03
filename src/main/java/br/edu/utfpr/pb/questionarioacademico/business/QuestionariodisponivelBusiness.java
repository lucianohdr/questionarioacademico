package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariodisponivelRepository;

@Stateless
@SuppressWarnings("unchecked")
public class QuestionariodisponivelBusiness extends
		RepositoryImpl<Questionariodisponivel, Long> implements
		QuestionariodisponivelRepository {

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

	@Override
	public List<Questionariodisponivel> porUsuarioEporStatus(Usuario usuario,
			Status status) {
		List<Questionariodisponivel> retorno = new ArrayList<Questionariodisponivel>();
		
		//verificando o tipo de usuario, se ele possui o perfil aluno
		if(usuario.getPerfis().contains(new Perfil("ALUNO"))){
			retorno = porAlunoEporStatus(usuario, status);
		} else {
			retorno = porProfessorEporStatus(usuario, status);
		}
		return retorno;
	}
	
private List<Questionariodisponivel> porProfessorEporStatus(Usuario usuario, Status status){
		
		List<Questionariodisponivel> retorno = new ArrayList<Questionariodisponivel>();
		//TODO: implementar
		/*String hql = "select distinct questionario from Questionario questionario"
				+ " inner join questionario.disciplinas disciplina"
				+ " join disciplina.curso curso"
				+ " join disciplina.professor professor"
				+ " where curso.id in (select curso.id from"
											+ " Professor aluno "
											+ " join aluno.pessoa pessoa"
											+ " join pessoa.usuario usuario"
											+ " join aluno.curso curso"
											+ " where usuario.id = :idusuario)"
				+ " and questionario.status = :status";
				
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idusuario", usuario.getId());
		query.setParameter("status", status);
		
		retorno = query.getResultList();
		*/
		return retorno;
	}
	
	private List<Questionariodisponivel> porAlunoEporStatus(Usuario usuario, Status status){
		
		List<Questionariodisponivel> retorno = new ArrayList<Questionariodisponivel>();
		String hql = "select questionariodisponivel from Questionariodisponivel questionariodisponivel"
				+ " join questionariodisponivel.disciplina disciplina"
				+ " join questionariodisponivel.questionario questionario"
				+ " join disciplina.curso curso"
				+ " where disciplina.id in (select disciplina.id from"
											+ " Aluno aluno "
											+ " join aluno.pessoa pessoa"
											+ " join pessoa.usuario usuario"
											+ " join aluno.curso curso"
											+ " where usuario.id = :idusuario)"
				+ " and questionario.status = :status";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idusuario", usuario.getId());
		query.setParameter("status", status);
		
		retorno = query.getResultList();
		
		return retorno;
	}
}