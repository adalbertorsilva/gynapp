package br.org.silva.gynapp.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.org.silva.gynapp.dao.ExercicioDAO;
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

	@Test
	public void should_retrieve_all() {
		assertFalse(exercicioDAO.getAll().isEmpty());
		assertNotNull(exercicioDAO.getAll().stream().findFirst().get().getNome());
	}

	@Test
	public void should_get_exercicio_by_id() {
		Exercicio legPress = new Exercicio();
		legPress.setNome("Leg Press");
		exercicioDAO.save(legPress);
		Long legPressId = legPress.getId();

		assertEquals(exercicioDAO.getById(legPressId).getNome(), legPress.getNome());
	}

	@Test(expected = InvalidOperationException.class)
	public void should_throw_an_exeception_when_tries_to_update_an_exercicio() {
		exercicioDAO.update(new Exercicio());
	}

	@Test
	public void should_delete_exercicio() {
		Exercicio barra = new Exercicio();
		barra.setNome("Barra Fixa");
		exercicioDAO.save(barra);

		Long barraId = barra.getId();
		exercicioDAO.delete(barra);

		assertNull(exercicioDAO.getById(barraId));
	}
}
