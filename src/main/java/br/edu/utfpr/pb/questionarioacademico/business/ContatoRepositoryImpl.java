package br.edu.utfpr.pb.questionarioacademico.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.edu.utfpr.pb.questionarioacademico.business.common.RepositoryImpl;
import br.edu.utfpr.pb.questionarioacademico.model.Contato;
import br.edu.utfpr.pb.questionarioacademico.repository.ContatoRepository;

@Stateless
public class ContatoRepositoryImpl extends RepositoryImpl<Contato, Long> implements ContatoRepository {

	/**
	 * 
	 * @param entityManager
	 * 
	 * Propriedade usando a injeção de dependência do vraptor
	 * 
	 */
	
	/*ContatoRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}*/
}