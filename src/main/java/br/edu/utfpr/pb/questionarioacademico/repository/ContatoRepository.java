package br.edu.utfpr.pb.questionarioacademico.repository;

import br.edu.utfpr.pb.questionarioacademico.model.Contato;
import br.edu.utfpr.pb.questionarioacademico.repository.common.Repository;

/**
 * 
 * @author trgp
 *
 * Essa seria a tradicional camada de DAOs, mas para questões conceituais, como não tocamos 
 * diretamente em código de banco de dados por termos o hibernate para tal, o pattern Repository 
 * é o mais indicado. Em outras palavras, uma camada Repository também pode ser chamada de DAO, 
 * mas o inverso não é verdadeiro.
 * 
 */
public interface ContatoRepository extends Repository<Contato, Long> {
	 
}