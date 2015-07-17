package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Permissao;
import br.edu.utfpr.pb.questionarioacademico.repository.PermissaoRepository;

@Stateless
public class PermissaoBusiness extends RepositoryImpl<Permissao, Long>
		implements PermissaoRepository {

}