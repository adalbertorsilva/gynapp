package br.org.silva.gynapp.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.org.silva.gynapp.dao.ProgramaDao;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.model.Carga;
import br.org.silva.gynapp.model.Execucao;
import br.org.silva.gynapp.model.Exercicio;
import br.org.silva.gynapp.model.Programa;
import br.org.silva.gynapp.model.Serie;
import br.org.silva.gynapp.test.base.TestBase;

public class TestProgramaDao extends TestBase{

	@Autowired
	private ProgramaDao programaDao;
	
	@Test(expected=BusinessException.class)
	public void programa_should_have_a_collection_of_exercicio() throws BusinessException{
		Programa programa = new Programa();
		programaDao.save(programa);
	}
	
	@Test
	public void should_insert_programa() throws BusinessException{
		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));
		programaDao.save(programa);
		assertNotNull(programa.getId());
	}
	

	@Test
	public void should_retrieve_all() throws BusinessException {
		
		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));
		programaDao.save(programa);
		
		assertFalse(programaDao.getAll().isEmpty());
	}

	@Test
	public void should_get_exercicio_by_id() throws BusinessException {
		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));
		programaDao.save(programa);
		Long programaID = programa.getId();

		assertEquals(programaDao.getById(programaID).getExecucoes(), programa.getExecucoes());
	}

	@Test
	public void should_update_an_exercicio() throws BusinessException {
		
		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));
		programaDao.save(programa);
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino Inclinado"), new Serie("3 x 10"), new Carga(100)));
		programaDao.update(programa);
		
		Long programaId = programa.getId();
		
		assertTrue(programaDao.getById(programaId).getExecucoes().size() > 1);
	}

	@Test
	public void should_delete_exercicio() throws BusinessException {
		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));
		programaDao.save(programa);
		
		Long programaID = programa.getId();
		programaDao.delete(programa);

		assertNull(programaDao.getById(programaID));
	}
	

}
