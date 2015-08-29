package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Resposta;
import br.edu.utfpr.pb.questionarioacademico.repository.RespostaRepositoty;

@Stateless
public class RespostaBusiness extends RepositoryImpl<Resposta, Long> implements
		RespostaRepositoty {

}