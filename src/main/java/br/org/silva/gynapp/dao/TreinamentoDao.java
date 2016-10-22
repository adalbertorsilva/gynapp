package br.org.silva.gynapp.dao;

import org.springframework.stereotype.Repository;

import br.org.silva.gynapp.dao.base.BaseDao;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.exception.InvalidOperationException;
import br.org.silva.gynapp.model.Treinamento;

@Repository
public class TreinamentoDao extends BaseDao<Treinamento> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8723591085844731860L;

	@Override
	@Deprecated
	public void delete(Treinamento entidade) {
		throw new InvalidOperationException();
	}
	
	@Override
	protected void preEvents(Treinamento entidade) throws BusinessException {
		if(entidade.getProgramas().isEmpty()){
			throw new BusinessException();
		}
	}
	
	
}
