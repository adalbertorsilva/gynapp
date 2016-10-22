package br.org.silva.gynapp.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.org.silva.gynapp.interfaces.Entidade;

@Entity
public class Exercicio implements Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1964971620019096509L;

	@Id
	@SequenceGenerator(name="exercicio_id_seq", sequenceName="exercicio_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="exercicio_id_seq")
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	public Exercicio() {}
	
	public Exercicio(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		Exercicio exercicio = (Exercicio) obj;
		return this.getNome().equalsIgnoreCase(exercicio.getNome());
	}
}
