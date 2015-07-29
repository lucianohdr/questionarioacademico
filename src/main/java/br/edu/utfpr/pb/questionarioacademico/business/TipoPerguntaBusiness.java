package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.TipoPergunta;
import br.edu.utfpr.pb.questionarioacademico.repository.TipoPerguntaRepository;

@Stateless
public class TipoPerguntaBusiness extends RepositoryImpl<TipoPergunta, Long>
		implements TipoPerguntaRepository {

}
