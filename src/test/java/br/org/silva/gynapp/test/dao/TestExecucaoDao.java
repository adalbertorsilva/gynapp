package br.org.silva.gynapp.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.org.silva.gynapp.dao.ExecucaoDao;
import br.org.silva.gynapp.dao.ExercicioDAO;
import br.org.silva.gynapp.dao.SerieDao;
import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.model.Carga;
import br.org.silva.gynapp.model.Execucao;
import br.org.silva.gynapp.model.Exercicio;
import br.org.silva.gynapp.model.Serie;
import br.org.silva.gynapp.test.base.TestBase;

public class TestExecucaoDao extends TestBase{
	
	@Autowired
	private ExecucaoDao execucaoDao;

	@Test(expected = PersistenceException.class)
	public void execucao_should_not_have_a_null_exercicio() throws DuplicatedObjectException{
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setSerie(serie);
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
	}
	
	@Test(expected = PersistenceException.class)
	public void execucao_should_not_have_a_null_serie() throws DuplicatedObjectException{
		Exercicio exercicio = new Exercicio("Supino");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
	}
	
	@Test(expected = PersistenceException.class)
	public void execucao_should_not_have_a_null_carga() throws DuplicatedObjectException{
		Exercicio exercicio = new Exercicio("Supino");
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setSerie(serie);
		
		execucaoDao.save(execucao);
	}
	
	@Test
	public void should_insert_execucao() throws DuplicatedObjectException{
		Exercicio exercicio = new Exercicio("Supino");
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setSerie(serie);
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
		
		assertNotNull(execucao.getId());
	}
	
	@Test
	public void should_retrieve_all() throws DuplicatedObjectException{
		Exercicio exercicio = new Exercicio("Supino");
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setSerie(serie);
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
		assertFalse(execucaoDao.getAll().isEmpty());
	}
	
	@Test
	public void should_retireve_execucao_by_id() throws DuplicatedObjectException{
		Exercicio exercicio = new Exercicio("Supino");
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setSerie(serie);
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
		
		Long execucaoId = execucao.getId();
		
		assertEquals(execucaoDao.getById(execucaoId).getExercicio().getNome(), execucao.getExercicio().getNome());
	}
	
	@Test
	public void should_delete_exercicio() throws DuplicatedObjectException {
		Exercicio exercicio = new Exercicio("Supino");
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setSerie(serie);
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
		
		Long execucaoId = execucao.getId();
		execucaoDao.delete(execucao);

		assertNull(execucaoDao.getById(execucaoId));
	}
}
