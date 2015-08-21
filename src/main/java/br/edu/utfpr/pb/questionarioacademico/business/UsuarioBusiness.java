package br.edu.utfpr.pb.questionarioacademico.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Usuario;
import br.edu.utfpr.pb.questionarioacademico.repository.UsuarioRepository;

@Stateless
public class UsuarioBusiness extends RepositoryImpl<Usuario, Long> implements UsuarioRepository{

	@Override
	public Usuario getByUsernameAndPassword(String login, String senha) {

		/*
		 * Mais uma vez, esse código não toca no sql nativo, por isso pode ser chamado de Repository pattern
		 */
		StringBuilder queryString = new StringBuilder("from Usuario where login = ? and senha = ?");

		Query query = entityManager.createQuery(queryString.toString());
		
		query.setParameter(1, login);
		query.setParameter(2, senha);
		
		List<Usuario> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean loginDisponivel(String login) {
		
		StringBuilder queryString = new StringBuilder("from Usuario where login = :login");
		Query query = entityManager.createQuery(queryString.toString());
		
		query.setParameter("login", login);
		
		if(query.getResultList().isEmpty()){
			return true;
		} else {
			return false;
		}
	}
}