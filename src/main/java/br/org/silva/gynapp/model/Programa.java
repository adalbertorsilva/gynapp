package br.org.silva.gynapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.org.silva.gynapp.interfaces.Entidade;

@Entity
public class Programa implements Entidade {
	
	@Id
	@SequenceGenerator(name="programa_id_seq", sequenceName="programa_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="programa_id_seq")
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="programa")
	private Set<Execucao> execucoes;
	
	@Column
	private boolean disponivel;
	
	@ManyToOne
	private Treinamento treinamento;
	
	@Override
	public Long getId() {
		return id;
	}
	
	public Programa() {
		this.disponivel = true;
	}

	public Set<Execucao> getExecucoes() {
		
		if(execucoes == null){
			this.execucoes = new HashSet<>();
		}
		
		return execucoes;
	}

	public void disponibilizar(){
		this.disponivel = true;
	}
	
	public void indisponibilizar(){
		this.disponivel = false;
	}
	
	public boolean isDisponivel() {
		return disponivel;
	}
	
	@Override
	public boolean equals(Object obj) {
		Programa programa = (Programa) obj;
		return this.getExecucoes().equals(programa.getExecucoes());
	}
}
