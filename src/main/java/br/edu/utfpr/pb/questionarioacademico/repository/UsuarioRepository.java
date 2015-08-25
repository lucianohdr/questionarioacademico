package br.edu.utfpr.pb.questionarioacademico.repository;

import java.util.List;

import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

public interface UsuarioRepository extends Repository<Usuario, Long>{

	Usuario getByUsernameAndPassword(String login, String senha);
	
	boolean loginDisponivel(String login);
	
	List<String> getRoles(Usuario usuario);
}