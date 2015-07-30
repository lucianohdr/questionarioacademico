package br.edu.utfpr.pb.questionarioacademico.repository.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.pb.questionarioacademico.model.commons.Entity;

/**
 * 
 * @author trgp
 *
 * Interface genérica para o padrão de projeto Repository, na interface mais específica esse padrão será melhor explicado
 * 
 */
public interface Repository<T extends Entity, I extends Serializable> {
	 
	void insert(T entity);
	
	T insertReturn(T entity);
	
	T update(T entity);
	
	void delete(T entity);
	
	T find(I id);
	
	List<T> findAll();
	
	List<T> pagination(int first, int size, Map<String,String> filters);
	
}
