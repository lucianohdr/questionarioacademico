package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Periodo;
import br.edu.utfpr.pb.questionarioacademico.repository.PeriodoRepository;

@Stateless
public class PeriodoBusiness extends RepositoryImpl<Periodo, Long> implements
		PeriodoRepository {

}