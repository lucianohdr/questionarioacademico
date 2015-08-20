package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Pessoa;
import br.edu.utfpr.pb.questionarioacademico.repository.PessoaRepository;

@Stateless
public class PessoaBusiness extends RepositoryImpl<Pessoa, Long> implements
		PessoaRepository {

}