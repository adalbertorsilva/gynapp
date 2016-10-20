package br.org.silva.gynapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
	
	@OneToOne(cascade=CascadeType.ALL)
	private Exercicio exercicio; 
	
	@OneToOne(cascade=CascadeType.ALL)
	private Serie serie;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Carga carga;
	
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
}
