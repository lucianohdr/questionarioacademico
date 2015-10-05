package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Questionarioresposta;
import br.edu.utfpr.pb.questionarioacademico.model.Resposta;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariorespostaRepository;
import br.edu.utfpr.pb.questionarioacademico.seguranca.Hasher;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.Login;

@Stateless
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

}