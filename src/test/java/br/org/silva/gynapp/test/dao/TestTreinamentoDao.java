package br.org.silva.gynapp.test.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.org.silva.gynapp.dao.TreinamentoDao;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.exception.InvalidOperationException;
import br.org.silva.gynapp.model.Carga;
import br.org.silva.gynapp.model.Execucao;
import br.org.silva.gynapp.model.Exercicio;
import br.org.silva.gynapp.model.Programa;
import br.org.silva.gynapp.model.Serie;
import br.org.silva.gynapp.model.Treinamento;
import br.org.silva.gynapp.test.base.TestBase;

public class TestTreinamentoDao extends TestBase {

	@Autowired
	private TreinamentoDao treinamentoDao;

	@Test(expected = BusinessException.class)
	public void should_have_one_programa_to_save() throws BusinessException {
		Treinamento treinamento = new Treinamento();
		treinamentoDao.save(treinamento);
	}

	@Test
	public void should_insert_a_treinamento() throws BusinessException {
		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));

		Treinamento treinamento = new Treinamento();
		treinamento.getProgramas().add(programa);
		treinamentoDao.save(treinamento);

		assertNotNull(treinamento.getId());
	}

	@Test
	public void should_get_treinamento_by_id() throws BusinessException {
		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));

		Treinamento treinamento = new Treinamento();
		treinamento.getProgramas().add(programa);
		treinamentoDao.save(treinamento);

		Long treinamentoId = treinamento.getId();

		assertNotNull(treinamentoDao.getById(treinamentoId).getId());
	}

	@Test
	public void should_get_all_treinamentos() throws BusinessException {
		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));

		Treinamento treinamento = new Treinamento();
		treinamento.getProgramas().add(programa);
		treinamentoDao.save(treinamento);

		assertFalse(treinamentoDao.getAll().isEmpty());
	}

	@Test
	public void should_update_treinamento() throws BusinessException {

		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));

		Treinamento treinamento = new Treinamento();
		treinamento.getProgramas().add(programa);
		treinamentoDao.save(treinamento);

		Long treinamentoId = treinamento.getId();

		programa = new Programa();
		programa.getExecucoes()
				.add(new Execucao(new Exercicio("Supino Inclinado"), new Serie("3 x 10"), new Carga(100)));

		treinamento.getProgramas().add(programa);
		treinamentoDao.update(treinamento);

		assertTrue(treinamentoDao.getById(treinamentoId).getProgramas().size() > 1);
	}

	@Test(expected = InvalidOperationException.class)
	public void should_throw_an_exception_when_delete() throws BusinessException {

		Programa programa = new Programa();
		programa.getExecucoes().add(new Execucao(new Exercicio("Supino"), new Serie("3 x 10"), new Carga(100)));

		Treinamento treinamento = new Treinamento();
		treinamento.getProgramas().add(programa);
		treinamentoDao.save(treinamento);

		treinamentoDao.delete(treinamento);
	}

}
