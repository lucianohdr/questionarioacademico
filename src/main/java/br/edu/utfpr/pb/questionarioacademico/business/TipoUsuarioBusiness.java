package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Tipousuario;
import br.edu.utfpr.pb.questionarioacademico.repository.TipoUsuarioRepository;

@Stateless
public class TipoUsuarioBusiness extends RepositoryImpl<Tipousuario, Long> implements TipoUsuarioRepository{
	
}