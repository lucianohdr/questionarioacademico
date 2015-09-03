
package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.joda.time.LocalDate;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
import br.edu.utfpr.pb.questionarioacademico.model.Questionario;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionarioRepository;

@Stateless
@SuppressWarnings("unchecked")
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

	@Override
	public List<Questionario> porUsuarioEporStatus(Usuario usuario, Status status) {
		List<Questionario> retorno = new ArrayList<Questionario>();
		
		//verificando o tipo de usuario, se ele possui o perfil aluno
		if(usuario.getPerfis().contains(new Perfil("ALUNO"))){
			retorno = porAlunoEporStatus(usuario, status);
		} else {
			retorno = porProfessorEporStatus(usuario, status);
		}
		return retorno;
	}
	
	private List<Questionario> porProfessorEporStatus(Usuario usuario, Status status){
		
		List<Questionario> retorno = new ArrayList<Questionario>();
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
	
	private List<Questionario> porAlunoEporStatus(Usuario usuario, Status status){
		
		List<Questionario> retorno = new ArrayList<Questionario>();
		String hql = "select questionario from Questionario questionario"
				+ " left outer join questionario.disciplinas disciplina"
				+ " join disciplina.curso curso"
				+ " join disciplina.professor professor"
				+ " where curso.id in (select curso.id from"
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

	@Override
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
	}
}