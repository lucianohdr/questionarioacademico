package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Tipopergunta;
import br.edu.utfpr.pb.questionarioacademico.repository.TipoPerguntaRepository;

@Stateless
public class TipoPerguntaBusiness extends RepositoryImpl<Tipopergunta, Long>
		implements TipoPerguntaRepository {

}
