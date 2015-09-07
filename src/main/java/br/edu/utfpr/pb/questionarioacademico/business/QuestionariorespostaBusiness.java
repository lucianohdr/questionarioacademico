package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Questionarioresposta;
import br.edu.utfpr.pb.questionarioacademico.model.Resposta;
import br.edu.utfpr.pb.questionarioacademico.repository.QuestionariorespostaRepository;

@Stateless
public class QuestionariorespostaBusiness extends RepositoryImpl<Questionarioresposta, Long> implements
		QuestionariorespostaRepository {

	@Override
	public Questionarioresposta insertReturn(Questionarioresposta questionarioresposta) {
		
		if(questionarioresposta.getRespostas()!=null){
			setQuestionariorespostaInResposta(questionarioresposta);
		}
		questionarioresposta = super.insertReturn(questionarioresposta);
		
		return questionarioresposta;
	}
	
	private void setQuestionariorespostaInResposta(Questionarioresposta questionarioresposta){
		for(Resposta resposta: questionarioresposta.getRespostas()){
			resposta.setQuestionarioresposta(questionarioresposta);
		}
	}
}