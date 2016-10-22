package br.org.silva.gynapp.test.model;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

import br.org.silva.gynapp.model.Programa;
import br.org.silva.gynapp.model.Treinamento;
import br.org.silva.gynapp.test.base.TestBase;

public class TestTreinamento extends TestBase{

	@Test
	public void treinamento_deve_completar_um_programa(){

		Treinamento treinamento = new Treinamento();
		treinamento.getProgramas().add(new Programa());
		treinamento.getProgramas().add(new Programa());
		treinamento.getProgramas().add(new Programa());
		
		treinamento.completarPrograma(treinamento.getProgramas().iterator().next());
		
		assertFalse(treinamento.getProgramas().iterator().next().isDisponivel());
	}
	
	@Test
	public void treinamento_deve_incrementar_um_ciclo_ao_completar_todos_os_programas(){

		Treinamento treinamento = new Treinamento();
		treinamento.getProgramas().add(new Programa());
		treinamento.getProgramas().add(new Programa());
		treinamento.getProgramas().add(new Programa());
		
		assertEquals(treinamento.getCiclos(), new Integer(0));
		
		treinamento.getProgramas().forEach(p -> treinamento.completarPrograma(p));
		
		assertTrue(treinamento.getCiclos() > 0);
	}
	
	@Test
	public void treinamento_deve_disponibilizar_todos_programas_quando_completar_um_ciclo(){

		Treinamento treinamento = new Treinamento();
		treinamento.getProgramas().add(new Programa());
		treinamento.getProgramas().add(new Programa());
		treinamento.getProgramas().add(new Programa());
		
		treinamento.getProgramas().forEach(p -> treinamento.completarPrograma(p));
		
		assertEquals(treinamento.getProgramas().stream()
								.filter(p -> p.isDisponivel())
								.collect(Collectors.toList()).size(), 
								treinamento.getProgramas().size());
	}
}
