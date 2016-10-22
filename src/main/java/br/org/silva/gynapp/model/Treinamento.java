package br.org.silva.gynapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.org.silva.gynapp.interfaces.Entidade;

@Entity
public class Treinamento implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2709267799039960902L;

	@Id
	@SequenceGenerator(name="treinamento_id_seq", sequenceName="treinamento_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="treinamento_id_seq")
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="treinamento")
	private Set<Programa> programas;
	
	@Override
	public Long getId() {
		return id;
	}

	public Set<Programa> getProgramas() {
		
		if(programas == null){
			programas = new HashSet<>();
		}
		
		return programas;
	}

}
