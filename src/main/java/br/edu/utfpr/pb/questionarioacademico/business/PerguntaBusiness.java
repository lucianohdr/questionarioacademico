package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Alternativa;
import br.edu.utfpr.pb.questionarioacademico.model.Pergunta;
import br.edu.utfpr.pb.questionarioacademico.repository.PerguntaRepository;

@Stateless
public class PerguntaBusiness extends RepositoryImpl<Pergunta, Long> implements
		PerguntaRepository {

	@Override
	public List<Pergunta> perguntasPorQuestionario(long idquestionario) {
		return null;
	}

	@Override
	public void setPerguntaInAlternativa(Pergunta pergunta) {
		//garantindo que objeto alternativa tenha a referencia de pergunta
		for(Alternativa alternativa: pergunta.getAlternativas()){
			alternativa.setPergunta(pergunta);
		}
	}
	
	@Override
	public Pergunta insertReturn(Pergunta pergunta) {
		
		if(pergunta.getAlternativas()!=null){
			setPerguntaInAlternativa(pergunta);
		}
		
		return super.insertReturn(pergunta);
	}
	
	@Override
	public Pergunta update(Pergunta pergunta) {
		
		if(pergunta.getAlternativas()!=null){
			setPerguntaInAlternativa(pergunta);
		}
		return super.update(pergunta);
	}
}