package br.org.silva.gynapp.test.model;

import org.junit.Test;

import br.org.silva.gynapp.model.Programa;
import br.org.silva.gynapp.test.base.TestBase;
import static org.junit.Assert.*;

public class TestPrograma extends TestBase {
	
	@Test
	public void programa_should_be_disponivel_when_created(){
		Programa programa = new Programa();
		assertTrue(programa.isDisponivel());
	}
	
	@Test
	public void programa_should_be_indisponivel(){
		Programa programa = new Programa();
		programa.indisponibilizar();
		assertFalse(programa.isDisponivel());
	}
	
	@Test
	public void programa_should_be_disponivel(){
		Programa programa = new Programa();
		programa.indisponibilizar();
		assertFalse(programa.isDisponivel());
		
		programa.disponibilizar();
		assertTrue(programa.isDisponivel());
	}
}
