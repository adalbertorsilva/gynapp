package br.org.silva.gynapp.dao;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.org.silva.gynapp.dao.base.BaseDao;
import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.exception.InvalidOperationException;
import br.org.silva.gynapp.model.Exercicio;

@Repository
public class ExercicioDAO extends BaseDao<Exercicio>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1566821258988631589L;

	@Deprecated
	@Override
	public void update(Exercicio entidade){
		throw new InvalidOperationException();
	}

	@Override
	protected void preEvents(Exercicio entidade) throws DuplicatedObjectException {
		if (!getExerciciosByName(entidade).isEmpty()) {
			throw new DuplicatedObjectException();
		}
		
	}
	
	public Collection<Exercicio> getExerciciosByName(Exercicio exercicio){
		StringBuffer queryString = new StringBuffer("select * from exercicio e where e.nome = ");
		queryString.append("\'");
		queryString.append(exercicio.getNome());
		queryString.append("\'");
		return getEntityManager().createNativeQuery(queryString.toString()).getResultList();
	}
	
}
