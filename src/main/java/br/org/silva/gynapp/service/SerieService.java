package br.org.silva.gynapp.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.org.silva.gynapp.dao.SerieDao;
import br.org.silva.gynapp.exception.BusinessException;
import br.org.silva.gynapp.exception.DuplicatedObjectException;
import br.org.silva.gynapp.interfaces.UTF8MediaType;
import br.org.silva.gynapp.model.Serie;

@Path("series")
@Component @Transactional
public class SerieService {

	@Inject
	private SerieDao serieDao;
	
	@GET
	@Produces(UTF8MediaType.JSON)
	public Collection<Serie> getSeries(){
		return serieDao.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(UTF8MediaType.JSON)
	public Serie getSerieById(@PathParam("id") Long id){
		return serieDao.getById(id);
	}
	
	@POST
	@Consumes(UTF8MediaType.JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String saveSerie(Serie serie){
		try {
			serieDao.save(serie);
			return "Operação realizada com sucesso!";
		} catch (BusinessException e) {
			return e.getMessage();
		}
	}
	
	@DELETE
	@Consumes(UTF8MediaType.JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteSerie(Serie serie){
		return "Operação realizada com sucesso!";
	}
}
