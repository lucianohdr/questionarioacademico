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
import br.edu.utfpr.pb.questionarioacademico.model.commons.Resultado;
import br.edu.utfpr.pb.questionarioacademico.model.commons.ResultadoPergunta;
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
			List<ResultadoPergunta> resultadoPerguntas = new ArrayList<ResultadoPergunta>();
			
			for(Pergunta pergunta: perguntas){
				ResultadoPergunta resultadoPergunta = new ResultadoPergunta();
				resultadoPergunta.setPergunta(pergunta);
				
				resultadoPerguntas.add(resultadoPergunta);
			}
			
			resultadoPerguntas = addRespostas(questionariorespostas, resultadoPerguntas); 
		}
		
		return retorno;
	}

	private List<ResultadoPergunta> addRespostas(List<Questionarioresposta> questionariorespostas,
												 List<ResultadoPergunta> resultadoPerguntas) {
		
		for(ResultadoPergunta resultadoPergunta: resultadoPerguntas ){
			Pergunta pergunta = resultadoPergunta.getPergunta();
			
			//percorrendo todos os questioanarios resposta, que sao as respostas a todas pergunta separaras por usuario
			for(Questionarioresposta questionarioresposta: questionariorespostas){
				//percorendo as respostas de cada usuario
				for (Resposta resposta : questionarioresposta.getRespostas()) {
					
					//verificando se a pergunta é igual a pergunta do resultado, caso for, adiciona como resposta a pergunta
					//dessa forma é agrupada todas as respostas de determinada pergunta
					if(pergunta.equals(resposta.getPergunta())){
						if(pergunta.getTipopergunta().equals(new Tipopergunta("DESCRITIVA"))){
							resultadoPergunta.getRespostas().add(resposta);
						} else {
							
						}
						
					}
				}
			}
		}
		return null;
	}
}