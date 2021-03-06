package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Pergunta;
import br.edu.utfpr.pb.questionarioacademico.model.Questionariodisponivel;
import br.edu.utfpr.pb.questionarioacademico.model.Questionarioresposta;
import br.edu.utfpr.pb.questionarioacademico.model.Resposta;
import br.edu.utfpr.pb.questionarioacademico.model.Tipopergunta;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.model.commons.Resultado;
import br.edu.utfpr.pb.questionarioacademico.model.commons.ResultadoResposta;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariorespostaRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.Hasher;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.Login;

@Stateless
@SuppressWarnings("unchecked")
public class QuestionariorespostaBusiness extends RepositoryImpl<Questionarioresposta, Long> implements
		QuestionariorespostaRepository {

	private Login login;
	
	@Inject
	public QuestionariorespostaBusiness(Login login) {
		this.login = login;
	}
	
	public QuestionariorespostaBusiness() {
		this(null);
	}
	
	@Override
	public Questionarioresposta insertReturn(Questionarioresposta questionarioresposta) {
		
		if(questionarioresposta.getRespostas()!=null){
			setQuestionariorespostaInResposta(questionarioresposta);
		}
		
		//setando o hash do id de usuario
		questionarioresposta.setHashid(Hasher.get(login.getUsuario().getId().toString()));
		
		questionarioresposta = super.insertReturn(questionarioresposta);
		
		return questionarioresposta;
	}
	
	private void setQuestionariorespostaInResposta(Questionarioresposta questionarioresposta){
		for(Resposta resposta: questionarioresposta.getRespostas()){
			resposta.setQuestionarioresposta(questionarioresposta);
		}
	}

	@Override
	public List<Questionarioresposta> respostasPorIdQuestDisponivel(Long idquestionariodisponivel) {
		List<Questionarioresposta> retorno = null;
		
		String hql = "select questionarioresposta from Questionarioresposta questionarioresposta"
				   + " join Questionarioresposta.questionariodisponivel questionariodisponivel"
				   + " where questionariodisponivel.id = :idquestionariodisponivel";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idquestionariodisponivel", idquestionariodisponivel);
		
		retorno = query.getResultList();
		
		return retorno;
	}

	@Override
	public Resultado carregaResultado(List<Questionarioresposta> questionariorespostas) {
		Resultado retorno = new Resultado();
		
		if(questionariorespostas.size()>0){
			//setando questionariodisponivel no objeto de Resultados
			Questionariodisponivel questionariodisponivel = questionariorespostas.get(0).getQuestionariodisponivel();
			retorno.setQuestionariodisponivel(questionariodisponivel);
			
			//setando numero de respostas
			Integer usuariosrespondidos = questionariorespostas.size();
			retorno.setCountUsuariosResp(usuariosrespondidos);
			
			//percorrendo perguntas e adicionando em Resultado pergunta  
			List<Pergunta> perguntas = questionariodisponivel.getQuestionario().getPerguntas();
			List<ResultadoResposta> resultadoPerguntas = new ArrayList<ResultadoResposta>();
			retorno.setResultadoRespostas(resultadoPerguntas);
			
			for(Pergunta pergunta: perguntas){
				ResultadoResposta resultadoPergunta = new ResultadoResposta();
				resultadoPergunta.setPergunta(pergunta);
				
				resultadoPerguntas.add(resultadoPergunta);
			}
			
			resultadoPerguntas = addRespostas(questionariorespostas, resultadoPerguntas); 
		}
		
		return retorno;
	}

	private List<ResultadoResposta> addRespostas(List<Questionarioresposta> questionariorespostas,
												 List<ResultadoResposta> resultadoPerguntas) {
		
		for(ResultadoResposta resultadoPergunta: resultadoPerguntas ){
			Pergunta pergunta = resultadoPergunta.getPergunta();
			
			//percorrendo todos os questioanarios resposta, que sao as respostas a todas pergunta separaras por usuario
			for(Questionarioresposta questionarioresposta: questionariorespostas){
				//percorendo as respostas de cada usuario
				for (Resposta resposta : questionarioresposta.getRespostas()) {
					
					//verificando se a pergunta é igual a pergunta do resultado, caso for, adiciona como resposta a pergunta
					//dessa forma é agrupada todas as respostas de determinada pergunta
					if(pergunta.equals(resposta.getPergunta())){
						//verificando tipo de pergunta, se for descritiva somentente adiciona a resposta, senão soma o contador de alternativas
						if(pergunta.getTipopergunta().equals(new Tipopergunta("DESCRITIVA"))){
							resultadoPergunta.getRespostas().add(resposta);
						} else {
							resultadoPergunta.getRespostas().add(resposta);
							resultadoPergunta.addAlternativa(resposta.getAlternativa());
						}
						
					}
				}
			}
		}
		return resultadoPerguntas;
	}

	@Override
	public Questionarioresposta repostaPorUsuarioEidquestionariodisponivel(
			Long idquestionariodisponivel,
			Usuario usuario) {
		
		Questionarioresposta retorno = null;
		
		String hashid = Hasher.get(String.valueOf(usuario.getId()));
		
		String hql = "select questionarioresposta from Questionarioresposta questionarioresposta"
				   + " join questionarioresposta.questionariodisponivel questionariodisponivel"
				   + " where questionarioresposta.hashid = :hashid and questionariodisponivel.id = :idquestionariodisponivel";
		
		Query query = this.entityManager.createQuery(hql);
		query.setParameter("hashid", hashid);
		query.setParameter("idquestionariodisponivel", idquestionariodisponivel);
		
		retorno = (Questionarioresposta) query.getSingleResult();
		
		return retorno;
	}
}