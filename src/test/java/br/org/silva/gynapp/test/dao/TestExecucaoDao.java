package br.org.silva.gynapp.test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.org.silva.gynapp.dao.ExecucaoDao;
import br.org.silva.gynapp.dao.ExercicioDAO;
import br.org.silva.gynapp.dao.SerieDao;
import br.org.silva.gynapp.model.Carga;
import br.org.silva.gynapp.model.Execucao;
import br.org.silva.gynapp.test.base.TestBase;

public class TestExecucaoDao extends TestBase{
	
	@Autowired
	private ExecucaoDao execucaoDao;
	
	@Autowired
	private ExercicioDAO exercicioDAO;
	
	@Autowired
	private SerieDao serieDao;
	
	@Test
	public void should_insert_execucao(){
		
		Execucao execucao =  new Execucao();
		
		execucao.setExercicio(exercicioDAO.getById(700L));
		execucao.setSerie(serieDao.getById(50L));
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
		
		assertNotNull(execucao.getId());
		
	}

	@Test
	public void should_retrieve_all(){
		assertFalse(execucaoDao.getAll().isEmpty());
	}
}
