package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.enums.questionario.Status;
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariodisponivelRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.Login;

@Stateless
@SuppressWarnings("unchecked")
public class QuestionariodisponivelBusiness extends
		RepositoryImpl<Questionariodisponivel, Long> implements
		QuestionariodisponivelRepository {
	
	private Login login;
	
	@Inject
	public QuestionariodisponivelBusiness(Login login) {
		this.login = login;
	}
	
	public QuestionariodisponivelBusiness() {
		this(null);
	}

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
		
		retorno = query.getResultList();*/
		
		return retorno;
	}
	
	private List<Questionariodisponivel> porAlunoEporStatus(Usuario usuario, Status status){
		
		//selecionando curso do aluno
		Query idCurso = idCursoPorUsuarioAluno(usuario);
		
		//selecionando disciplinas com base no curso selecionado anteriormente
		Query idDisciplinas = idDisciplinaPorCurso();
		idDisciplinas.setParameter("idcurso", idCurso.getSingleResult());
		
		//filtrando questionariodisponives através das disciplinas 
		Query idQuestDispDisc = idQuestionariosDisponivelPorIdDisciplina();
		idQuestDispDisc.setParameter("idDisciplinas", idDisciplinas.getResultList());
		
		List<Long> listIdsQuestDispDisc = idQuestDispDisc.getResultList();
		List<Questionariodisponivel> retorno = new ArrayList<Questionariodisponivel>();
		
		if(listIdsQuestDispDisc.size()>0){
			
			//filtrando questionariodisponives por status
			Query idQuestPorStatus = idQuestPorStatus(status);
			idQuestPorStatus.setParameter("idQuestDispDisc", idQuestDispDisc.getResultList());
			
			List<Long> listIdQuestPorStatus = idQuestPorStatus.getResultList();
			if(listIdQuestPorStatus.size() > 0){
				
				//filtrando questionariodisponives por categoria referentes a alunos
				Query idQuestPorCategAluno = idQuestPorCategAluno();
				idQuestPorCategAluno.setParameter("idQuestPorStatus", listIdQuestPorStatus);
		
				//pegando questionariodisponives já respondidos pelo usuario 
				Query idQuestRepondido = idQuestRespondido(usuario);
			
				//filtrando todos os questionarios anteriores que não foram respondidos
				Query idQuestNaoRespondido = idQuestNaoRespondido(idQuestPorCategAluno.getResultList(), idQuestRepondido.getResultList());
				
				retorno = idQuestNaoRespondido.getResultList();
			}
		}
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

	private Query idQuestNaoRespondido(List<Long> idQuestPorCategAluno, List<Long> idQuestRepondido) {
		
		StringBuilder hql = new StringBuilder();
		
		Query query;
		
		hql.append("select questionariodisponivel from Questionariodisponivel questionariodisponivel");
		if(idQuestRepondido.size() > 0){
			
			hql.append(" where questionariodisponivel.id in :idQuestPorCategAluno and questionariodisponivel.id not in :idQuestRepondido");
			
			query = this.entityManager.createQuery(hql.toString());
			query.setParameter("idQuestPorCategAluno", idQuestPorCategAluno);
			query.setParameter("idQuestRepondido", idQuestRepondido);
			
		} else {
			
			hql.append(" where questionariodisponivel.id in :idQuestPorCategAluno");
			query = this.entityManager.createQuery(hql.toString());
			query.setParameter("idQuestPorCategAluno", idQuestPorCategAluno);
		}
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

	@Override
	public List<Questionariodisponivel> respondidosPorUsuario(Usuario usuario) {
		List<Questionariodisponivel> retorno = new ArrayList<Questionariodisponivel>();
		
		//selecionando questionarios respondidos
		Query idsQuestRespondidos = idQuestRespondido(usuario);
		
		List<Long> idQuestRespondido = idsQuestRespondidos.getResultList();
		
		if(idQuestRespondido.size()>0){
			StringBuilder hql = new StringBuilder();
			hql.append("select questionariodisponivel from Questionariodisponivel questionariodisponivel");
			hql.append(" where questionariodisponivel.id in :idsQuestRespondidos");
			
			Query questResp = this.entityManager.createQuery(hql.toString());
			questResp.setParameter("idsQuestRespondidos", idsQuestRespondidos.getResultList());
			
			retorno = questResp.getResultList();
		}
		return retorno;
	}

	@Override
	public List<Questionariodisponivel> porUsuarioEPorPerfil(Perfil perfil) {
		List<Questionariodisponivel> retorno = new ArrayList<Questionariodisponivel>();
		Usuario usuario = login.getUsuario();
		
		//verificando perfil de usuario
		if(perfil.equals(new Perfil("ADMINISTRADOR"))){
			retorno = todosRespondidos();
		}
		
		return retorno;
	}

	private List<Questionariodisponivel> todosRespondidos() {
		
		List<Long> idsQuest = null;
		List<Questionariodisponivel> retorno = null;
		
		String hqlIdsQuest = "select questionariodisponivel.id from Questionariodisponivel questionariodisponivel"
				   + " inner join questionariodisponivel.questionariorespostas questionariorespostas"
				   + " inner join questionariodisponivel.usuariosRespondidos usuariosRespondidos";
		
		Query query = this.entityManager.createQuery(hqlIdsQuest);
		
		idsQuest = query.getResultList();
		
		String hql = "select questionariodisponivel from Questionariodisponivel questionariodisponivel"
				   + " where questionariodisponivel.id in (:idsQuest)";
		
		query = this.entityManager.createQuery(hql);
		query.setParameter("idsQuest", idsQuest);
		
		retorno = query.getResultList();
		return retorno;
	}
}