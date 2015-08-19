package br.edu.utfpr.pb.questionarioacademico.business.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.edu.utfpr.pb.questionarioacademico.model.commons.Entity;

/**
 * 
 * @author trgp
 *
 * @param <Entity>
 * @param <Id>
 * 
 * Classe que implementa as operações mais básicas com o banco de dados usando hibernate e sem tocar diretamente no sql nativo, 
 * isso se refere ao padrão de projeto Repository que será melhor explicado nas interfaces específicas
 * 
 */
@SuppressWarnings("unchecked")
@Stateless
public class RepositoryImpl<T extends Entity, I extends Serializable> {
	
	@PersistenceContext(unitName = "entityManager")
	protected EntityManager entityManager;
	
	protected final Class<T> clazz = retornaTipo();

	public void insert(T entity) {
		entity.setId(null);
		entityManager.persist(entity);
	}
	
	public T insertReturn(T entity){
		entity.setId(null);
		return (T) entityManager.merge(entity);
	}
	
	public T update(T entity) {
		return entityManager.merge(entity);
	}
	
	public void delete(T entity) {
		entityManager.remove(find((I) entity.getId()));
	}
	
	public T find(I id) {
		return entityManager.find(clazz, id);
	}
	
	public List<T> findAll() {
		Query query = entityManager.createQuery(new StringBuilder("from ").append(clazz.getName()).toString());
		List<T> resultList = query.getResultList();
		return resultList;
	}
	
	public List<T> pagination(int first, int size, Map<String,String> filters) {
		
		StringBuilder queryString = new StringBuilder("select o from ").append(clazz.getSimpleName()).append(" o ");
		
		if (getDefaultOrderField() != null && getDefaultOrderField().length() > 0) {
			queryString.append(" order by ").append(getDefaultOrderField());
		}
		
		Query query = entityManager.createQuery(queryString.toString());
		
		query.setFirstResult(first);
		query.setMaxResults(size);
		
		return query.getResultList();
		
	}
	
	protected String getDefaultOrderField() {
		return null;
	}
	
	private Class<T> retornaTipo() {
		Class<?> clazz = this.getClass();
		
		while (!clazz.getSuperclass().equals(RepositoryImpl.class)) {
			clazz = clazz.getSuperclass();
		}
		
		ParameterizedType tipoGenerico = (ParameterizedType) clazz.getGenericSuperclass();
		return (Class<T>) tipoGenerico.getActualTypeArguments()[0];
	}
}