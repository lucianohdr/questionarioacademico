package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Curso;
import br.edu.utfpr.pb.questionarioacademico.repository.CursoRepository;

@Stateless
public class CursoBusiness extends RepositoryImpl<Curso, Long> implements
		CursoRepository {
}