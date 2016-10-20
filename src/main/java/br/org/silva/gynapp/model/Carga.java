package br.org.silva.gynapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.org.silva.gynapp.interfaces.Entidade;

@Entity
public class Carga implements Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185751773797186913L;

	@Id
	@SequenceGenerator(name="peso_id_seq", sequenceName="peso_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="peso_id_seq")
	private Long id;
	
	@Column
	private Integer peso;
	
	public Carga() {
	}
	
	public Carga(Integer peso) {
		this.peso = peso;
	}
	
	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Long getId() {
		return id;
	}

}
