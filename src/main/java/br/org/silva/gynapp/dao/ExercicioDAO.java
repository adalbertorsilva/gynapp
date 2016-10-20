package br.org.silva.gynapp.dao;

import org.springframework.stereotype.Repository;

import br.org.silva.gynapp.dao.base.BaseDao;
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
	
}
