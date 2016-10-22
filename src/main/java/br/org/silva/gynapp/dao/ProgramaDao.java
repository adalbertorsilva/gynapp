package br.org.silva.gynapp.dao;

import org.springframework.stereotype.Repository;

import br.org.silva.gynapp.dao.base.BaseDao;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.exception.InvalidOperationException;
import br.org.silva.gynapp.model.Programa;

@Repository
public class ProgramaDao extends BaseDao<Programa>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1105752230360353732L;

	@Override
	protected void preEvents(Programa entidade) throws BusinessException {
		
		if(entidade.getExecucoes().isEmpty()){
			throw new BusinessException();
		}
	}

}
