package br.org.silva.gynapp.dao;

import org.springframework.stereotype.Repository;

import br.org.silva.gynapp.dao.base.BaseDao;
import br.org.silva.gynapp.exception.InvalidOperationException;
import br.org.silva.gynapp.model.Serie;

@Repository
public class SerieDao extends BaseDao<Serie> {

	@Override
	public void update(Serie entidade) {
		throw new InvalidOperationException();
	}
	
}
