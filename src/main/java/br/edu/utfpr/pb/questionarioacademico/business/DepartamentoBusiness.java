package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Departamento;
import br.edu.utfpr.pb.questionarioacademico.repository.DepartamentoRepository;

@Stateless
public class DepartamentoBusiness extends RepositoryImpl<Departamento, Long>
		implements DepartamentoRepository {

}
