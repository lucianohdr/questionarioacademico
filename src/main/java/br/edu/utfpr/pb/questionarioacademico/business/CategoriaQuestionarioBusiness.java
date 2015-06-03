package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Categoriaquestionario;
import br.edu.utfpr.pb.questionarioacademico.repository.CategoriaQuestionarioRepository;

@Stateless
public class CategoriaQuestionarioBusiness extends RepositoryImpl<Categoriaquestionario, Long> implements CategoriaQuestionarioRepository {

}
