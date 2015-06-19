package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Aluno;
import br.edu.utfpr.pb.questionarioacademico.repository.AlunoRepositoty;

@Stateless
public class AlunoBusiness extends RepositoryImpl<Aluno, Long> implements AlunoRepositoty{

}