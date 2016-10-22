package br.org.silva.gynapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import br.org.silva.gynapp.interfaces.Entidade;

@Entity
public class Execucao implements Entidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6050760114492263383L;
	
	@Id
	@SequenceGenerator(name="execucao_id_seq", sequenceName="execucao_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="execucao_id_seq")
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	private Exercicio exercicio; 
	
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	private Serie serie;
	
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	private Carga carga;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, 
				 fetch=FetchType.LAZY, mappedBy= "execucao")
	@OrderBy("dataCriacao ASC")
	private List<HistoricoExecucao> historico;
	
	@ManyToOne
	private Programa programa;
	
	public Execucao() {}
	
	public Execucao(Exercicio exercicio, Serie serie, Carga carga){
		this.exercicio = exercicio;
		this.serie = serie;
		this.carga = carga;
	}
	
	public Exercicio getExercicio() {
		return exercicio;
	}
	
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Serie getSerie() {
		return serie;
	}
	
	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Carga getCarga() {
		return carga;
	}
	
	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public Long getId() {
		return id;
	}

	public List<HistoricoExecucao> getHistorico() {
		
		if(historico == null){
			historico = new ArrayList<>();
		}
		
		return historico;
	}
	
	@Override
	public boolean equals(Object obj) {
		Execucao execucao = (Execucao) obj;
		return this.getExercicio().equals(execucao.getExercicio()) && 
			   this.getSerie().equals(execucao.getSerie()) &&
			   this.getCarga().equals(execucao.getCarga());
	}
}
