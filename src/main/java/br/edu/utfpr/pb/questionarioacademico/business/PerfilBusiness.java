package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Perfil;
import br.edu.utfpr.pb.questionarioacademico.repository.PerfilRepository;

@Stateless
public class PerfilBusiness extends RepositoryImpl<Perfil, Long> implements PerfilRepository{
	
}