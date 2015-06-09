package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.UsuarioRepository;

@Stateless
public class UsuarioBusiness extends RepositoryImpl<Usuario, Long> implements UsuarioRepository{

}