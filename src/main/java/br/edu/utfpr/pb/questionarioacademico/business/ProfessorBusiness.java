package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Professor;
import br.edu.utfpr.pb.questionarioacademico.repository.ProfessorRepository;

@Stateless
public class ProfessorBusiness extends RepositoryImpl<Professor, Long> implements ProfessorRepository {

}