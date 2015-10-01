package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Categoriaquestionario;
import br.edu.utfpr.pb.questionarioacademico.model.Disciplina;
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
		
		Query idCurso = idCursoPorUsuarioAluno(usuario);
		
		Query idDisciplinas = idDisciplinaPorCurso();
		idDisciplinas.setParameter("idcurso", idCurso.getSingleResult());
		
		Query idQuestDispDisc = idQuestionariosDisponivelPorIdDisciplina();
		idQuestDispDisc.setParameter("idDisciplinas", idDisciplinas.getResultList());
		
		Query idQuestPorStatus = idQuestPorStatus(status);
		idQuestPorStatus.setParameter("idQuestDispDisc", idQuestDispDisc.getResultList());

		Query idQuestPorCategAluno = idQuestPorCategAluno();
		idQuestPorCategAluno.setParameter("idQuestPorStatus", idQuestPorStatus.getResultList());
		
		Query idQuestRepondido = idQuestRespondido(usuario);
		
		List<Long> lista = idQuestRepondido.getResultList();
		
		
		Query idQuestNaoRespondido = idQuestNaoRespondido();
		idQuestNaoRespondido.setParameter("idQuestRepondido", idQuestRepondido.getResultList());
		idQuestNaoRespondido.setParameter("idQuestPorCategAluno", idQuestPorCategAluno.getResultList());
		
		List<Questionariodisponivel> retorno = idQuestNaoRespondido.getResultList();
		
		return retorno;
	}
	
	private Query idQuestPorCategAluno() {
		String hql = "select questionariodisponivel.id from Questionariodisponivel questionariodisponivel"
				   + " join questionariodisponivel.questionario questionario"
				   + " join questionario.categoriaquestionario categoria"
				   + " where (categoria.id = 1 or categoria.id = 4) and questionariodisponivel.id in :idQuestPorStatus";
				   
		Query query = this.entityManager.createQuery(hql);
		
		return query;
	}

	private Query idQuestNaoRespondido() {
		String hql = "select questionariodisponivel from Questionariodisponivel questionariodisponivel"
				   + " where questionariodisponivel.id in :idQuestPorCategAluno and questionariodisponivel.id not in :idQuestRepondido";
		
		Query query = this.entityManager.createQuery(hql);
		
		return query;
	}

	private Query idQuestPorStatus(Status status) {
		String hql = "select questionariodisponivel.id from Questionariodisponivel questionariodisponivel"
				   + " join questionariodisponivel.questionario questionario"
				   + " where questionario.status = :status and questionariodisponivel.id in :idQuestDispDisc";
				   
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("status", status);
		
		return query;
	}

	private Query idQuestRespondido(Usuario usuario) {
		String hql = "select questionariodisponivel.id from Questionariodisponivel questionariodisponivel"
				   + " inner join questionariodisponivel.usuariosRespondidos usuariosRespondidos"
				   + " where usuariosRespondidos.id = :idusuario";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idusuario", usuario.getId());
		
		return query;
	}

	private Query idQuestionariosDisponivelPorIdDisciplina() {
		Query retorno = null;
		String hql = "select questionariodisponivel.id from Questionariodisponivel questionariodisponivel"
				+ " join questionariodisponivel.disciplina disciplina"
				+ " where disciplina.id in (:idDisciplinas)";
		
		retorno = this.entityManager.createQuery(hql);
		
		return retorno;
	}

	private Query idDisciplinaPorCurso() {
		Query retorno = null;
		String hql = "select disciplina.id from Disciplina disciplina"
				+ " join disciplina.curso curso"
				+ " where curso.id = :idcurso";
		
		retorno = this.entityManager.createQuery(hql);
		
		return retorno;
	}

	private Query idCursoPorUsuarioAluno(Usuario usuario) {
		String hql = "select curso.id from Aluno aluno"
				+ " join aluno.curso curso"
				+ " join aluno.pessoa pessoa"
				+ " join pessoa.usuario usuario"
				+ " where usuario.id = :idusuario";
		
		Query retorno = this.entityManager.createQuery(hql);
		
		retorno.setParameter("idusuario", usuario.getId());
		
		return retorno;
	}

	/*private Query idsNaoRespondidosPorUsuario(Usuario usuario){
		String hql = "select questionariodisponivel.id from Questionariodisponivel questionariodisponivel"
				   + " join questionariodisponivel.questionario questionario"
				   + " inner join questionariodisponivel.usuariosRespondidos usuariosRespondidos"
				   + " where usuariosRespondidos.id <> :idusuario";
		
		Query query = this.entityManager.createQuery(hql);
		
		query.setParameter("idusuario", usuario.getId());
		return query;
	}
	
	private Query idsPorStatusEUsuario(Status status){
		
		String hql = "select questionariodisponivel.id from Questionariodisponivel questionariodisponivel"
				   + " join questionariodisponivel.questionario questionario"
				   + " inner join questionariodisponivel.usuariosRespondidos usuariosRespondidos"
				   + " where questionario.status = :status and questionariodisponivel.id in (:questionariodisponivelIdList)";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("status", status);
		return query;
	}
	
	private Query idsPorCategoriaEUsuarioAluno(){
		 
		String hql = "select questionariodisponivel.id from Questionariodisponivel questionariodisponivel"
				   + " join questionariodisponivel.questionario questionario"
				   + " join questionario.categoriaquestionario categoria"
				   + " inner join questionariodisponivel.usuariosRespondidos usuariosRespondidos"
				   + " where (categoria.id = 1 or categoria = 4) and questionariodisponivel.id in (:questionariodisponivelIdList)";
		
		Query query = this.entityManager.createQuery(hql);
		return query;
	}
	
	private Query idsDeDisciplinasPorAluno(Usuario usuario){
		 
		String hql = "select questionariodisponivel from Questionariodisponivel questionariodisponivel"
				   + " join questionariodisponivel.questionario questionario"
				   + " join questionariodisponivel.disciplina disciplina"
				   + " inner join questionariodisponivel.usuariosRespondidos usuariosRespondidos"
				   + " where disciplina.id in ("
				   			+ " select disciplinaAluno.id from Aluno aluno"
				   			+ " join aluno.pessoa pessoa"
				   			+ " join pessoa.usuario usuario"
				   			+ " join aluno.turma turma"
				   			+ " join turma.periodo periodo"
				   			+ " join turma.curso curso"
				   			+ " join curso.disciplinas disciplinaAluno"
				   			+ " where usuario.id = :idusuario "
				   + ") and questionariodisponivel.id in (:questionariodisponivelIdList)";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idusuario", usuario.getId());
		return query;
	}*/
}