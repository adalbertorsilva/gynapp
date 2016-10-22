package br.org.silva.gynapp.dao;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.org.silva.gynapp.dao.base.BaseDao;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.exception.InvalidOperationException;
import br.org.silva.gynapp.model.Serie;

@Repository
public class SerieDao extends BaseDao<Serie> {

	@Override
	public void update(Serie entidade) {
		throw new InvalidOperationException();
	}
	
	@Override
	protected void preEvents(Serie entidade) throws BusinessException {
		if(!getSeriesByName(entidade).isEmpty()){
			throw new DuplicatedObjectException();
		}
	}
	
	public Collection<Serie> getSeriesByName(Serie serie){
		StringBuffer queryString = new StringBuffer("select * from serie s where s.descricao = ");
		queryString.append("\'");
		queryString.append(serie.getDescricao());
		queryString.append("\'");
		return getEntityManager().createNativeQuery(queryString.toString()).getResultList();
	}
	
}
