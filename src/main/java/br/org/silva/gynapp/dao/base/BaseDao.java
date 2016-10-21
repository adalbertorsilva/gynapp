package br.org.silva.gynapp.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;

import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.interfaces.Entidade;

public abstract class BaseDao<E extends Entidade> implements Serializable{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	protected void preEvents(E entidade) throws DuplicatedObjectException{}
	protected void postEvents(E entidade){}
	
	public void save(E entidade) throws DuplicatedObjectException{
		
		preEvents(entidade);
		getEntityManager().persist(entidade);
		postEvents(entidade);
	}

	public void update(E entidade) throws DuplicatedObjectException {
		preEvents(entidade);
		getEntityManager().merge(entidade);
		postEvents(entidade);
	}

	public void delete(E entidade) {
		getEntityManager().remove(getEntityManager().contains(entidade) ? entidade : getEntityManager().merge(entidade));
	}

	public E getById(Long id) {
		return getEntityManager().find(getClazz(), id);
	}
	
	public Session getSession(){
		return getEntityManager().unwrap(Session.class);
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<E> getAll() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from ")
			  .append(getClazz().getSimpleName().toLowerCase());
		
		Query query = getEntityManager().createNativeQuery(buffer.toString(),getClazz());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Class<E> getClazz(){
		return (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
