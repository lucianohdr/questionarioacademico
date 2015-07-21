package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Turma;
import br.edu.utfpr.pb.questionarioacademico.repository.TurmaRepository;

@Stateless
public class TurmaBusiness extends RepositoryImpl<Turma, Long> implements TurmaRepository {

}