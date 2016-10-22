package br.org.silva.gynapp.service;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.silva.gynapp.dao.ExercicioDAO;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.interfaces.UTF8MediaType;
import br.org.silva.gynapp.model.Exercicio;

@Path("exercicios")
@Component
@Transactional
public class ExercicioService {
	
	@Autowired
	private ExercicioDAO exercicioDAO;
	
	@GET
	@Produces(UTF8MediaType.JSON)
	public Collection<Exercicio> getExercicios(){
		return exercicioDAO.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(UTF8MediaType.JSON)
	public Exercicio getExercicio(@PathParam("id") Long id){
		return exercicioDAO.getById(id);
	}
	
	@POST
	@Consumes(UTF8MediaType.JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String saveExercicio(Exercicio exercicio){
		try {
			exercicioDAO.save(exercicio);
			return "Operação realizada com sucesso!";
		} catch (BusinessException e) {
			return e.getMessage();
		}
		
	}
	
	@DELETE
	@Consumes(UTF8MediaType.JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteExercicio(Exercicio exercicio){
		exercicioDAO.delete(exercicio);
		return "Operação realizada com sucesso!";
	}
}
