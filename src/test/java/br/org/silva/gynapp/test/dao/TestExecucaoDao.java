package br.org.silva.gynapp.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import br.org.silva.gynapp.dao.ExecucaoDao;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.model.Carga;
import br.org.silva.gynapp.model.Execucao;
import br.org.silva.gynapp.model.Exercicio;
import br.org.silva.gynapp.model.HistoricoExecucao;
import br.org.silva.gynapp.model.Serie;
import br.org.silva.gynapp.test.base.TestBase;
import mockit.Expectations;
import mockit.Mocked;

public class TestExecucaoDao extends TestBase{
	
	@Autowired
	private ExecucaoDao execucaoDao;

	@Test(expected = PersistenceException.class)
	public void execucao_should_not_have_a_null_exercicio() throws BusinessException{
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setSerie(serie);
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
	}
	
	@Test(expected = PersistenceException.class)
	public void execucao_should_not_have_a_null_serie() throws BusinessException{
		Exercicio exercicio = new Exercicio("Supino");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setCarga(new Carga(100));
		
		execucaoDao.save(execucao);
	}
	
	@Test(expected = PersistenceException.class)
	public void execucao_should_not_have_a_null_carga() throws BusinessException{
		Exercicio exercicio = new Exercicio("Supino");
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setSerie(serie);
		
		execucaoDao.save(execucao);
	}
	
	@Test
	public void should_insert_execucao() throws BusinessException{
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
	public void should_retrieve_all() throws BusinessException{
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
	public void should_retireve_execucao_by_id() throws BusinessException{
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
	public void should_delete_exercicio() throws BusinessException {
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
	
	@Test
	public void should_create_an_historico_execucao_when_persisted() throws BusinessException{
		
		Exercicio exercicio = new Exercicio("Supino");
		Serie serie = new Serie("3 x 10");
		
		Execucao execucao =  new Execucao();
		execucao.setExercicio(exercicio);
		execucao.setSerie(serie);
		execucao.setCarga(new Carga(100));
		
		assertTrue(execucao.getHistorico().isEmpty());
		execucaoDao.save(execucao);
		assertFalse(execucao.getHistorico().isEmpty());
		
		execucao.setCarga(new Carga(110));
		
		execucaoDao.update(execucao);
		assertTrue(execucao.getHistorico().size() > 1);
	}
}
