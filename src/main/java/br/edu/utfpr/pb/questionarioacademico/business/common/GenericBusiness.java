/*package br.edu.utfpr.pb.questionarioacademico.business.common;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.edu.utfpr.pb.questionarioacademico.repository.common.GenericRepository;

*//**
 * No construtor e recuperado o tipo da classe contido no argumento T. Atraves
 * da classe atual, GenericBusiness, o tipo generico e recuperado e convertido
 * para o tipo parametrizado. Com isso o metodo getActualTypeArguments()
 * recupera uma lista de argumentos e devolve apenas o primeiro por meio de seu
 * Indice [0], que e exatamente o tipo generico. Por fim, este tipo generico e
 * convertido em um Class para que possamos saber qual classe a nossa classe
 * generica ira manipular.
 *
 * *//*
@SuppressWarnings("unchecked")
public class GenericBusiness<T> implements GenericRepository<T> {

	@Inject protected EntityManager manager;
	protected final Class<T> clazz;

	
	public GenericBusiness(InjectionPoint injection) {

		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		ParameterizedType type = (ParameterizedType) injection.getType();
		this.clazz = (Class) type.getActualTypeArguments()[0];
		
	}

	@Override
	public List<T> listComboAtrib(String element, String parametros) {
		List<T> list = null;

		String hql = "select new " + clazz.getSimpleName() + "(" + parametros
				+ " ) from " + clazz.getSimpleName() + " o " + " order by o."
				+ element + " asc";

		try {
			Query query = manager.createQuery(hql);
			list = query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar 
		}

		return list;
	}

	@Override
	public List<T> listCombo(String element) {
		List<T> list = null;
		String hql = "from " + clazz.getName() + " order by " + element
				+ " asc";
		try {

			Query query = manager.createQuery(hql);

			list = query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar 
		}

		return list;

	}

	@Override
	public T save(T entity) {
		try {
			manager.getTransaction().begin();
			entity = manager.merge(entity);
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			//TODO: implementar
		}
		return entity;
	}

	@Override
	public T persist(T entity) {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			IndexController.logger.warn(MessageException.persistenceException
					+ e.getMessage() + "), <b>Causa: </b> " + e.getCause());
			throw new PersistenceException(
					MessageException.persistenceException + e.getMessage()
					+ "), <b>Causa: </b> " + e.getCause());
		}
		return entity;
	}

	@Override
	public void delete(Object id) {
		try {
			manager.getTransaction().begin();
			manager.remove(manager.getReference(clazz, id));
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			//TODO: implementar
		}

	}

	@Override
	public T edit(Object id) {
		try {
			return manager.find(clazz, id);
		} catch (PersistenceException e) {
			//TODO: implementar}
			return null;
		}
	}

	@Override
	public long getTotalRecords() {
		long total = 0;
		String hql = "select count(*) from " + clazz.getName();
		try {
			Query query = manager.createQuery(hql);
			total = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			//TODO: implementar
		}
		return total;
	}

	@Override
	public T firstOrLast(String order, String element) {
		List<T> list = null;

		Query query = null;
		String hql = "from " + clazz.getName() + " order by " + element + " "
				+ order;
		try {
			query = manager.createQuery(hql);
			list = query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar
		}
		return list.get(0);

	}

	@Override
	public T next(Object id, String element) {
		List<T> list = null;
		Query query = null;
		String hql = "from " + clazz.getName() + " where " + element
				+ " > (:id) order by " + element + " asc";
		try {
			query = manager.createQuery(hql).setMaxResults(1);
			query.setParameter("id", id);
			list = query.getResultList();

			if (list.isEmpty()) {
				hql = "from " + clazz.getName() + " order by " + element
						+ " desc";
				query = manager.createQuery(hql).setMaxResults(1);
				list = query.getResultList();
			}
		} catch (PersistenceException e) {
			//TODO: implementar
		}

		return list.get(0);
	}

	@Override
	public T previous(Object id, String element) {
		List<T> list = null;
		Query query = null;
		String hql = "from " + clazz.getName() + " where " + element
				+ " < (:id) order by " + element + " desc";
		try {
			query = manager.createQuery(hql).setMaxResults(1);
			query.setParameter("id", id);
			list = query.getResultList();

			if (list.isEmpty()) {
				hql = "from " + clazz.getName() + " order by " + element
						+ " asc";
				query = manager.createQuery(hql).setMaxResults(1);
				list = query.getResultList();
			}
		} catch (PersistenceException e) {
			//TODO: implementar
		}

		return list.get(0);

	}

	@Override
	public void searchString(String busca, String colunas[]) {
		String hql = "";
		Query query = null;
		String consulta = busca.toUpperCase();
		try {
			if (consulta.contains(" ")) {
				hql = "from " + clazz.getName() + " where ";
				String[] subSearch = consulta.split(" ");
				for (int j = 0; j < subSearch.length; j++) {
					if (j != 0) {
						hql += ") and (";
					} else {
						hql += "(";
					}
					for (int i = 0; i < colunas.length; i++) {
						if (i == 0) {
							hql = hql + " coliseusys.sem_acento(upper("
									+ colunas[i]
											+ ")) like (coliseusys.sem_acento((:busca"
											+ j + ")))";
						} else {
							hql = hql + " or coliseusys.sem_acento(upper("
									+ colunas[i]
											+ ")) like (coliseusys.sem_acento((:busca"
											+ j + ")))";
						}
					}

				}
				hql = hql + ")";
				query = manager.createQuery(hql);
				for (int j = 0; j < subSearch.length; j++) {
					query.setParameter("busca" + j,
							"%" + subSearch[j].toUpperCase() + "%");
				}
				query.setMaxResults(50);

			} else {
				hql = "from " + clazz.getName() + " where ";
				for (int i = 0; i < colunas.length; i++) {
					if (i == 0) {
						hql = hql + " coliseusys.sem_acento(upper("
								+ colunas[i]
										+ ")) like (coliseusys.sem_acento((:busca)))";
					} else {
						hql = hql + " or coliseusys.sem_acento(upper("
								+ colunas[i]
										+ ")) like (coliseusys.sem_acento((:busca)))";
					}
				}
				query = manager.createQuery(hql);
				query.setParameter("busca", "%" + consulta + "%");
				query.setMaxResults(50);
				//BuscaRepository.listaConsulta = query.getResultList();

				//BuscaRepository.qtdRetorno = query.getResultList().size();
			}

		} catch (PersistenceException e) {
			//TODO: implementar
		}

	}

	@Override
	public List<T> searchGrid(String colunas[], String searchString,
			String sidx, String sord, int start, int limit) {
		List<T> list = new ArrayList<T>();
		String hql = null;
		Query query = null;
		try {
			if (searchString == null || searchString.equals("")) {
				hql = " from " + clazz.getName() + " order by " + sidx + " "
						+ sord;
				query = manager.createQuery(hql);

				query.setFirstResult(start).setMaxResults(limit);

				list = query.getResultList();
			} else {
				Integer idPesquisa = 0;
				idPesquisa = Integer.parseInt(searchString);
				hql = " from " + clazz.getName() + " where ";
				for (int i = 0; i < colunas.length; i++) {
					if (i == 0) {
						hql = hql
								+ " coliseusys.sem_acento(upper("
								+ colunas[i]
										+ ")) like (coliseusys.sem_acento((:busca))) or "
										+ sidx + "='" + idPesquisa + "'";
					} else {
						hql = hql + " or coliseusys.sem_acento(upper("
								+ colunas[i]
										+ ")) like (coliseusys.sem_acento((:busca))) ";
					}

				}
				hql = hql + "order by " + sidx + " " + sord;
				query = manager.createQuery(hql);
				query.setParameter("busca", "%" + searchString.toUpperCase()
						+ "%");
				query.setFirstResult(start).setMaxResults(limit);
				list = query.getResultList();

			}

		} catch (NumberFormatException e) {
			hql = "from " + clazz.getName() + " where ";
			for (int i = 0; i < colunas.length; i++) {
				if (i == 0) {
					hql = hql + " coliseusys.sem_acento(upper(" + colunas[i]
							+ ")) like (coliseusys.sem_acento((:busca)))";
				} else {
					hql = hql + " or coliseusys.sem_acento(upper(" + colunas[i]
							+ ")) like (coliseusys.sem_acento((:busca))) ";
				}

			}
			hql = hql + "order by " + sidx + " " + sord;
			query = manager.createQuery(hql);
			query.setParameter("busca", "%" + searchString.toUpperCase() + "%");
			query.setFirstResult(start).setMaxResults(limit);

			list = query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar
		}

		return list;

	}

	@Override
	public void search(String busca, String idColuna, String colunas[]) {
		String hql;
		Query query = null;
		String consulta = busca.toUpperCase();
		try {
			Integer idPesquisa = 0;
			idPesquisa = Integer.parseInt(consulta);
			hql = "from " + clazz.getName() + " where ";
			for (int i = 0; i < colunas.length; i++) {
				if (i == 0) {
					hql = hql + " coliseusys.sem_acento(upper(" + colunas[i]
							+ ")) like (coliseusys.sem_acento((:busca))) or "
							+ idColuna + "='" + idPesquisa + "'";
				} else {
					hql = hql + " or coliseusys.sem_acento(upper(" + colunas[i]
							+ ")) like (coliseusys.sem_acento((:busca)))";
				}

			}

			query = manager.createQuery(hql);
			query.setParameter("busca", "%" + consulta + "%");
			query.setMaxResults(50);

		} catch (NumberFormatException e) {
			if (consulta.contains(" ")) {
				hql = "from " + clazz.getName() + " where ";
				String[] subSearch = consulta.split(" ");
				for (int j = 0; j < subSearch.length; j++) {
					if (j != 0) {
						hql += ") and (";
					} else {
						hql += "(";
					}
					for (int i = 0; i < colunas.length; i++) {
						if (i == 0) {
							hql = hql + " coliseusys.sem_acento(upper("
									+ colunas[i]
											+ ")) like (coliseusys.sem_acento((:busca"
											+ j + ")))";
						} else {
							hql = hql + " or coliseusys.sem_acento(upper("
									+ colunas[i]
											+ ")) like (coliseusys.sem_acento((:busca"
											+ j + ")))";
						}
					}

				}
				hql = hql + ")";
				query = manager.createQuery(hql);
				for (int j = 0; j < subSearch.length; j++) {
					query.setParameter("busca" + j,
							"%" + subSearch[j].toUpperCase() + "%");
				}
				query.setMaxResults(50);

			} else {
				hql = "from " + clazz.getName() + " where ";
				for (int i = 0; i < colunas.length; i++) {
					if (i == 0) {
						hql = hql + " coliseusys.sem_acento(upper("
								+ colunas[i]
										+ ")) like (coliseusys.sem_acento((:busca)))";
					} else {
						hql = hql + " or coliseusys.sem_acento(upper("
								+ colunas[i]
										+ ")) like (coliseusys.sem_acento((:busca)))";
					}
				}
				query = manager.createQuery(hql);
				query.setParameter("busca", "%" + consulta + "%");
				query.setMaxResults(50);
			}

		} catch (PersistenceException e) {
			//TODO: implementar
		}

	}

	@Override
	public T getForeignKeyData(Object id, String element) {
		Query query = null;
		String hql = "from " + clazz.getName() + " where " + element + "= :id";
		try {
			query = manager.createQuery(hql);
			query.setParameter("id", id);

			if (!query.getResultList().isEmpty()) {
				return (T) query.getSingleResult();
			} else {
				return null;
			}

		} catch (PersistenceException e) {
			////TODO: implementar IndexController.logger.warn(e.getMessage());
			return null;
		}

	}

	@Override
	public List<T> saveList(List<T> entity) {
		List<T> retorno = new ArrayList<>();
		try {
			manager.getTransaction().begin();

			if (entity != null) {
				Iterator<T> i = entity.iterator();
				while (i.hasNext()) {
					T savedEntity = i.next();
					savedEntity = manager.merge(savedEntity);
					retorno.add(savedEntity);
				}
			}
			manager.getTransaction().commit();
		} catch (PersistenceException e) {//TODO: implementar
			
		}
		return retorno;
	}

	@Override
	public Set<T> saveListSet(Set<T> entity) {
		Set<T> retorno = new HashSet<>();
		try {
			manager.getTransaction().begin();

			if (entity != null) {
				Iterator<T> i = entity.iterator();
				while (i.hasNext()) {
					T savedEntity = i.next();
					savedEntity = manager.merge(savedEntity);
					retorno.add(savedEntity);
				}
				manager.getTransaction().commit();
			}
		} catch (PersistenceException e) {//TODO: implementar
		}
		return retorno;
	}

	@Override
	public List<T> slqSearchGrid(String sql, int start, int limit) {
		List<T> list = null;
		Query query = null;
		String hql = sql;
		try {
			query = manager.createQuery(hql);
			query.setFirstResult(start).setMaxResults(limit);
			list = query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar
		}
		return list;
	}

	@Override
	public List<T> sqlList(String sql) {
		List<T> retorno = null;
		Query query = null;
		String hql = sql;
		try {
			query = manager.createQuery(hql);
			retorno = query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar
			retorno = null;
		}
		return retorno;
	}

	@Override
	public Collection<Object[]> sqlListObj(String sql) {
		Query query = null;
		try {
			query = manager.createQuery(sql);
			return query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar
			return null;
		}
	}

	@Override
	public Collection<Object[]> sqlListObj(String sql, int start, int limit) {
		Query query = null;
		try {
			query = manager.createQuery(sql);
			query.setFirstResult(start).setMaxResults(limit);
			return query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar
			return null;
		}
	}

	@Override
	public List<Object[]> searchGridObject(String[] colunas,
			String searchString, String sidx, String sord, int start,
			int limit, String joins) {
		String hql = null;
		Query query = null;
		List<Object[]> lista = null;
		int contColCons = 0;
		String[] colunasCons = null;
		boolean status = false;
		try {
			hql = "select ";

			for (int i = 0; i < colunas.length; i++) {
				if (i != 0) {
					hql += ", ";
				}
				if (colunas[i].contains("&") || colunas[i].contains("0")) {
					hql += colunas[i].substring(1);
				} else {
					hql += colunas[i];
					contColCons++;
				}
			}
			colunasCons = new String[contColCons];
			int contCons = 0;
			for (int j = 0; j < colunas.length; j++) {
				if (!colunas[j].contains("&") && !colunas[j].contains("0")) {
					colunasCons[contCons] = colunas[j];
					contCons++;
				}
			}
			if (searchString == null || searchString.equals("")
					|| searchString.matches("\\s+")) {

				hql += " from " + clazz.getName() + " " + joins + " order by "
						+ sidx + " " + sord;

				query = manager.createQuery(hql);

				query.setFirstResult(start).setMaxResults(limit);

				lista = query.getResultList();

			} else {
				Integer idPesquisa = 0;
				idPesquisa = Integer.parseInt(searchString);
				hql += " from " + clazz.getName() + " " + joins + " where (";
				for (int i = 0; i < colunasCons.length; i++) {
					if (i == 0) {
						if (colunasCons[i].contains("0")) {
							hql = hql + colunasCons[i].substring(1) + " = "
									+ idPesquisa + " or " + sidx + "='"
									+ idPesquisa + "'";
							status = true;
						} else if (!colunasCons[i].contains("/")) {
							hql = hql
									+ " coliseusys.sem_acento(upper("
									+ colunasCons[i]
											+ ")) like (coliseusys.sem_acento((:busca))) or "
											+ sidx + "='" + idPesquisa + "'";
							status = true;
						}
					} else {
						if (status && i != colunasCons.length) {
							if (colunasCons[i].contains("0")) {
								hql = hql + " or "
										+ colunasCons[i].substring(1) + " = "
										+ idPesquisa;
								status = true;
							} else if (!colunasCons[i].contains("/")) {
								hql = hql
										+ " or coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca))) ";
								status = true;
							}
						} else {
							if (colunasCons[i].contains("0")) {
								hql = hql + " or "
										+ colunasCons[i].substring(1) + " = "
										+ idPesquisa;
								status = true;
							} else if (!colunasCons[i].contains("/")) {
								hql = hql
										+ "or coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca))) ";
								status = true;
							}
						}
					}
				}
				hql = hql + ") order by " + sidx + " " + sord;
				query = manager.createQuery(hql);
				query.setParameter("busca", "%" + searchString.toUpperCase()
						+ "%");
				
				query.setFirstResult(start).setMaxResults(limit);
				lista = query.getResultList();
			}

		} catch (NumberFormatException e) {
			if (searchString.contains(" ")) {
				hql += " from " + clazz.getName() + " " + joins + " where (";
				String[] subSearch = searchString.split(" ");
				for (int j = 0; j < subSearch.length; j++) {
					status = false;
					if (j != 0) {
						hql += ") and (";
					} else {
						hql += "(";
					}
					for (int i = 0; i < colunasCons.length; i++) {
						if (i == 0) {
							if (colunasCons[i].contains("/")
									&& (searchString.length() == 8 || searchString
									.length() == 10)) {
								hql = hql + "'" + colunasCons[i].substring(1)
										+ "' = " + searchString;
								status = true;
							} else if (!(colunasCons[i].contains("/") || colunasCons[i]
									.contains("0"))) {

								hql = hql
										+ " coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca"
												+ j + "))) ";
								status = true;
							}
						} else {
							if (status && i != colunasCons.length) {
								if (colunasCons[i].contains("/")
										&& (searchString.length() == 8 || searchString
										.length() == 10)) {
									hql = hql + " or "
											+ colunasCons[i].substring(1)
											+ " = '" + searchString + "'";
									status = true;
								} else if (!(colunasCons[i].contains("/") || colunasCons[i]
										.contains("0"))) {
									hql = hql
											+ " or coliseusys.sem_acento(upper("
											+ colunasCons[i]
													+ ")) like (coliseusys.sem_acento((:busca"
													+ j + "))) ";
									status = true;
								}
							} else {
								if (colunasCons[i].contains("/")
										&& (searchString.length() == 8 || searchString
										.length() == 10)) {
									hql = hql + colunasCons[i].substring(1)
											+ " = " + searchString;
									status = true;
								} else if (!(colunasCons[i].contains("/") || colunasCons[i]
										.contains("0"))) {
									hql = hql
											+ " coliseusys.sem_acento(upper("
											+ colunasCons[i]
													+ ")) like (coliseusys.sem_acento((:busca"
													+ j + "))) ";
									status = true;
								}
							}
						}
					}
				}
				hql = hql + ")) order by " + sidx + " " + sord;
				query = manager.createQuery(hql);
				for (int j = 0; j < subSearch.length; j++) {
					query.setParameter("busca" + j,
							"%" + subSearch[j].toUpperCase() + "%");
				}
				query.setFirstResult(start).setMaxResults(limit);

				lista = query.getResultList();

			} else {
				hql += " from " + clazz.getName() + " " + joins + " where (";

				for (int i = 0; i < colunasCons.length; i++) {
					if (i == 0) {
						if (colunasCons[i].contains("/")
								&& (searchString.length() == 8 || searchString
								.length() == 10)) {
							hql = hql + "'" + colunasCons[i].substring(1)
									+ "' = " + searchString;
							status = true;
						} else if (!(colunasCons[i].contains("/") || colunasCons[i]
								.contains("0"))) {

							hql = hql
									+ " coliseusys.sem_acento(upper("
									+ colunasCons[i]
											+ ")) like (coliseusys.sem_acento((:busca))) ";
							status = true;
						}
					} else {
						if (status && i != colunasCons.length) {
							if (colunasCons[i].contains("/")
									&& (searchString.length() == 8 || searchString
									.length() == 10)) {
								hql = hql + " or "
										+ colunasCons[i].substring(1) + " = '"
										+ searchString + "'";
								status = true;
							} else if (!(colunasCons[i].contains("/") || colunasCons[i]
									.contains("0"))) {
								hql = hql
										+ " or coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca))) ";
								status = true;
							}
						} else {
							if (colunasCons[i].contains("/")
									&& (searchString.length() == 8 || searchString
									.length() == 10)) {
								hql = hql + colunasCons[i].substring(1) + " = "
										+ searchString;
								status = true;
							} else if (!(colunasCons[i].contains("/") || colunasCons[i]
									.contains("0"))) {
								hql = hql
										+ " coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca))) ";
								status = true;
							}
						}
					}
				}

				hql = hql + ") order by " + sidx + " " + sord;
				query = manager.createQuery(hql);
				query.setParameter("busca", "%" + searchString.toUpperCase()
						+ "%");

				query.setFirstResult(start).setMaxResults(limit);

				lista = query.getResultList();
			}

		} catch (PersistenceException e) {
			//TODO: implementar
		}
		return lista;
	}

	@Override
	public List<Object[]> searchGridObjectList(String[] colunas,
			String searchString, String sidx, String sord, int start,
			int limit, long idtabpai, String tabpaidesc, String joins) {
		String hql = null;
		Query query = null;
		List<Object[]> lista = null;
		int contColCons = 0;
		String[] colunasCons = null;
		boolean status = false;
		try {
			hql = "select ";

			for (int i = 0; i < colunas.length; i++) {
				if (i != 0) {
					hql += ", ";
				}
				if (colunas[i].contains("0") || colunas[i].contains("/")) {

					hql += colunas[i].substring(1);
					contColCons++;
				} else if (colunas[i].contains("&")) {
					hql += colunas[i].substring(1);
				} else {
					hql += colunas[i];
					contColCons++;
				}
			}

			colunasCons = new String[contColCons];
			int contCons = 0;
			for (int j = 0; j < colunas.length; j++) {
				if (!colunas[j].contains("&")) {
					colunasCons[contCons] = colunas[j];
					contCons++;
				}
			}
			if (searchString == null || searchString.equals("")) {

				hql += " from " + clazz.getName() + " " + joins + " where "
						+ tabpaidesc + " =(:idtabpai)  order by " + sidx + " "
						+ sord;

				query = manager.createQuery(hql);
				query.setParameter("idtabpai", idtabpai);

				query.setFirstResult(start).setMaxResults(limit);

				lista = query.getResultList();
			} else {
				Integer idPesquisa = 0;
				idPesquisa = Integer.parseInt(searchString);
				hql += " from " + clazz.getName() + " " + joins + " where "
						+ tabpaidesc + " =(:idtabpai) and (";
				for (int i = 0; i < colunasCons.length; i++) {
					if (i == 0) {
						if (colunasCons[i].contains("0")) {
							hql = hql + colunasCons[i].substring(1) + " = "
									+ idPesquisa + " or " + sidx + "='"
									+ idPesquisa + "'";
							status = true;
						} else if (!colunasCons[i].contains("/")) {
							hql = hql
									+ " coliseusys.sem_acento(upper("
									+ colunasCons[i]
											+ ")) like (coliseusys.sem_acento((:busca))) or "
											+ sidx + "='" + idPesquisa + "'";
							status = true;
						}
					} else {
						if (status && i != colunasCons.length) {
							if (colunasCons[i].contains("0")) {
								hql = hql + " or "
										+ colunasCons[i].substring(1) + " = "
										+ idPesquisa;
								status = true;
							} else if (!colunasCons[i].contains("/")) {
								hql = hql
										+ "or coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca))) ";
								status = true;
							}
						} else {
							if (colunasCons[i].contains("0")) {
								hql = hql + " or "
										+ colunasCons[i].substring(1) + " = "
										+ idPesquisa;
								status = true;
							} else if (!colunasCons[i].contains("/")) {
								hql = hql
										+ "or coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca))) ";
								status = true;
							}
						}
					}
				}

				hql = hql + ") order by " + sidx + " " + sord;
				System.out.println("hql" + hql);
				query = manager.createQuery(hql);
				query.setParameter("busca", "%" + searchString.toUpperCase()
						+ "%");
				query.setParameter("idtabpai", idtabpai);
				query.setFirstResult(start).setMaxResults(limit);
				lista = query.getResultList();
			}
		} catch (NumberFormatException e) {

			if (searchString.contains(" ")) {
				hql += " from " + clazz.getName() + " " + joins + " where "
						+ tabpaidesc + " =(:idtabpai) and(";
				String[] subSearch = searchString.split(" ");
				for (int j = 0; j < subSearch.length; j++) {
					status = false;
					if (j != 0) {
						hql += ") and (";
					} else {
						hql += "(";
					}
					for (int i = 0; i < colunasCons.length; i++) {
						if (i == 0) {
							if (colunasCons[i].contains("/")
									&& (searchString.length() == 8 || searchString
									.length() == 10)) {
								hql = hql + "'" + colunasCons[i].substring(1)
										+ "' = " + searchString;
								status = true;
							} else if (!(colunasCons[i].contains("/") || colunasCons[i]
									.contains("0"))) {

								hql = hql
										+ " coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca"
												+ j + "))) ";
								status = true;
							}
						} else {
							if (status && i != colunasCons.length) {
								if (colunasCons[i].contains("/")
										&& (searchString.length() == 8 || searchString
										.length() == 10)) {
									hql = hql + " or "
											+ colunasCons[i].substring(1)
											+ " = '" + searchString + "'";
									status = true;
								} else if (!(colunasCons[i].contains("/") || colunasCons[i]
										.contains("0"))) {
									hql = hql
											+ " or coliseusys.sem_acento(upper("
											+ colunasCons[i]
													+ ")) like (coliseusys.sem_acento((:busca"
													+ j + "))) ";
									status = true;
								}
							} else {
								if (colunasCons[i].contains("/")
										&& (searchString.length() == 8 || searchString
										.length() == 10)) {
									hql = hql + colunasCons[i].substring(1)
											+ " = " + searchString;
									status = true;
								} else if (!(colunasCons[i].contains("/") || colunasCons[i]
										.contains("0"))) {
									hql = hql
											+ " coliseusys.sem_acento(upper("
											+ colunasCons[i]
													+ ")) like (coliseusys.sem_acento((:busca"
													+ j + "))) ";
									status = true;
								}
							}
						}
					}
				}
				hql = hql + ")) order by " + sidx + " " + sord;
				query = manager.createQuery(hql);
				for (int j = 0; j < subSearch.length; j++) {
					query.setParameter("busca" + j,
							"%" + subSearch[j].toUpperCase() + "%");
				}
				query.setParameter("idtabpai", idtabpai);
				query.setFirstResult(start).setMaxResults(limit);

				lista = query.getResultList();

			} else {
				hql += " from " + clazz.getName() + " " + joins + " where "
						+ tabpaidesc + " =(:idtabpai) and(";

				for (int i = 0; i < colunasCons.length; i++) {
					if (i == 0) {
						if (colunasCons[i].contains("/")
								&& (searchString.length() == 8 || searchString
								.length() == 10)) {
							hql = hql + "'" + colunasCons[i].substring(1)
									+ "' = " + searchString;
							status = true;
						} else if (!(colunasCons[i].contains("/") || colunasCons[i]
								.contains("0"))) {

							hql = hql
									+ " coliseusys.sem_acento(upper("
									+ colunasCons[i]
											+ ")) like (coliseusys.sem_acento((:busca))) ";
							status = true;
						}
					} else {
						if (status && i != colunasCons.length) {
							if (colunasCons[i].contains("/")
									&& (searchString.length() == 8 || searchString
									.length() == 10)) {
								hql = hql + " or "
										+ colunasCons[i].substring(1) + " = '"
										+ searchString + "'";
								status = true;
							} else if (!(colunasCons[i].contains("/") || colunasCons[i]
									.contains("0"))) {
								hql = hql
										+ " or coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca))) ";
								status = true;
							}
						} else {
							if (colunasCons[i].contains("/")
									&& (searchString.length() == 8 || searchString
									.length() == 10)) {
								hql = hql + colunasCons[i].substring(1) + " = "
										+ searchString;
								status = true;
							} else if (!(colunasCons[i].contains("/") || colunasCons[i]
									.contains("0"))) {
								hql = hql
										+ " coliseusys.sem_acento(upper("
										+ colunasCons[i]
												+ ")) like (coliseusys.sem_acento((:busca))) ";
								status = true;
							}
						}
					}
				}

				hql = hql + ") order by " + sidx + " " + sord;
				query = manager.createQuery(hql);
				query.setParameter("busca", "%" + searchString.toUpperCase()
						+ "%");
				query.setParameter("idtabpai", idtabpai);
				query.setFirstResult(start).setMaxResults(limit);

				lista = query.getResultList();
			}
		} catch (PersistenceException e) {
			//TODO: implementar
		}

		return lista;
	}

	@Override
	public T edit2(Object id, String element) {
		String hql = "from " + clazz.getName() + " where " + element + " = :id";
		try {
			Query query = manager.createQuery(hql);
			query.setParameter("id", id);
			if (query.getResultList().isEmpty()) {
				return null;
			} else {
				return (T) query.getSingleResult();
			}

		} catch (PersistenceException e) {
			//TODO: implementar
			return null;
		}
	}

	@Override
	public T getObject(String sql) {
		T retorno = null;
		Query query = null;
		String hql = sql;
		try {
			query = manager.createQuery(hql);
			retorno = (T) query.getSingleResult();

		} catch (NoResultException e) {
			//TODO: implementar

		} catch (PersistenceException e) {
			//TODO: implementar
			retorno = null;
		}
		return retorno;
	}

	@Override
	public BigDecimal sumValue(String sql) {
		BigDecimal retorno = null;
		Query query = null;
		String hql = sql;
		try {
			query = manager.createQuery(hql);
			retorno = (BigDecimal) query.getSingleResult();

		} catch (PersistenceException e) {
			//TODO: implementar
			retorno = null;
		}
		return retorno;

	}

	@Override
	public long getTotalRecords(String campo, Object parametro) {
		long total = 0;
		String hql = "select count(*) from " + clazz.getName()
				+ " where "+campo +"= :parametro";
		try {
			Query query = manager.createQuery(hql);
			query.setParameter("parametro", parametro);
			total = (Long) query.getSingleResult();
		} catch (PersistenceException e) {
			//TODO: implementar
			total = 0;
		}
		return total;
	}

	@Override
	public List<T> getForeignKeyDataList(String campo, Object parametro) {
		List<T> retorno = new ArrayList<>();
		Query query = null;
		String hql = "from " + clazz.getName() + " where " + campo
				+ " =:parametro";
		try {
			query = manager.createQuery(hql);
			query.setParameter("parametro", parametro);
			retorno = query.getResultList();

		} catch (PersistenceException e) {
			//TODO: implementar
			retorno = null;
		}
		return retorno;
	}
}*/