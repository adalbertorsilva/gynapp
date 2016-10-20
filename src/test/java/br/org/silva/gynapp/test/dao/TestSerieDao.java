package br.org.silva.gynapp.test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.org.silva.gynapp.dao.SerieDao;
import br.org.silva.gynapp.exception.InvalidOperationException;
import br.org.silva.gynapp.model.Serie;
import br.org.silva.gynapp.test.base.TestBase;

public class TestSerieDao extends TestBase{
	
	@Autowired
	private SerieDao serieDao;
	
	@Test
	public void should_save_serie(){
		
		Serie serie = new Serie();
		serie.setDescricao("3 x 10");
		serieDao.save(serie);
		assertNotNull(serie.getId());
	}
	
	@Test
	public void should_get_all_series(){
		Serie serie = new Serie();
		serie.setDescricao("3 x 10");
		serieDao.save(serie);
		assertFalse(serieDao.getAll().isEmpty());
	}
	
	@Test
	public void should_get_serie_by_id(){
		Serie serie = new Serie();
		serie.setDescricao("2 x 3 - 5");
		serieDao.save(serie);
		Long serieId = serie.getId();
		assertNotNull(serieDao.getById(serieId));
	}
	
	@Test(expected = InvalidOperationException.class)
	public void should_throw_an_exeception_when_tries_to_update_a_serie(){
		serieDao.update(new Serie());
	}
	
	@Test
	public void should_delete_a_serie(){
		Serie serie = new Serie();
		serie.setDescricao("3 x 6 - 8");
		serieDao.save(serie);
		Long serieId = serie.getId();
		
		serieDao.delete(serie);
		assertNull(serieDao.getById(serieId));
		
	}
}