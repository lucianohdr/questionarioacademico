package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Alternativa;
import br.edu.utfpr.pb.questionarioacademico.repository.AlternativaRepository;

@Stateless
public class AlternativaBusiness extends RepositoryImpl<Alternativa, Long>
		implements AlternativaRepository {

}