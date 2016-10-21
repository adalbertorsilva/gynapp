package br.org.silva.gynapp.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.org.silva.gynapp.dao.ExercicioDAO;
import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.exception.InvalidOperationException;
import br.org.silva.gynapp.model.Exercicio;
import br.org.silva.gynapp.test.base.TestBase;

public class TestExercicioDAO extends TestBase {

	@Autowired
	private ExercicioDAO exercicioDAO;

	@Test
	public void should_insert_exercicio() throws Exception {
		Exercicio supino = new Exercicio();
		supino.setNome("Supino");
		exercicioDAO.save(supino);
		assertNotNull(supino.getId());
	}
	
	@Test(expected=PersistenceException.class)
	public void exercicio_should_have_a_name() throws DuplicatedObjectException{
		Exercicio supino = new Exercicio();
		exercicioDAO.save(supino);
	}
	
	@Test(expected = PersistenceException.class)
	public void exercicio_should_not_be_duplicated() throws DuplicatedObjectException{
		
		Exercicio supino = new Exercicio();
		supino.setNome("Supino");
		exercicioDAO.save(supino);
		
		supino = new Exercicio();
		supino.setNome("Supino");
		exercicioDAO.save(supino);
	}

	@Test
	public void should_retrieve_all() throws DuplicatedObjectException {
		
		Exercicio supino = new Exercicio();
		supino.setNome("Supino");
		exercicioDAO.save(supino);
		
		assertFalse(exercicioDAO.getAll().isEmpty());
		assertNotNull(exercicioDAO.getAll().stream().findFirst().get().getNome());
	}

	@Test
	public void should_get_exercicio_by_id() throws DuplicatedObjectException {
		Exercicio legPress = new Exercicio();
		legPress.setNome("Leg Press");
		exercicioDAO.save(legPress);
		Long legPressId = legPress.getId();

		assertEquals(exercicioDAO.getById(legPressId).getNome(), legPress.getNome());
	}

	@SuppressWarnings("deprecation")
	@Test(expected = InvalidOperationException.class)
	public void should_throw_an_exeception_when_tries_to_update_an_exercicio() {
		exercicioDAO.update(new Exercicio());
	}

	@Test
	public void should_delete_exercicio() throws DuplicatedObjectException {
		Exercicio barra = new Exercicio();
		barra.setNome("Barra Fixa");
		exercicioDAO.save(barra);

		Long barraId = barra.getId();
		exercicioDAO.delete(barra);

		assertNull(exercicioDAO.getById(barraId));
	}
}
