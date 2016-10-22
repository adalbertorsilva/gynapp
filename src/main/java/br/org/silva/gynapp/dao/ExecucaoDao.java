package br.org.silva.gynapp.dao;

import org.springframework.stereotype.Repository;

import br.org.silva.gynapp.dao.base.BaseDao;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.model.Execucao;
import br.org.silva.gynapp.model.HistoricoExecucao;

@Repository
public class ExecucaoDao extends BaseDao<Execucao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1040827656999571862L;
	
	@Override
	public void save(Execucao entidade) throws BusinessException {
		entidade.getHistorico().add(new HistoricoExecucao(entidade));
		super.save(entidade);
	}
	
	@Override
	public void update(Execucao entidade) throws BusinessException {
		entidade.getHistorico().add(new HistoricoExecucao(entidade));
		super.update(entidade);
	}

}
