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

	@Column
	private Integer quantidadeDeCiclos;

	public Treinamento() {
		quantidadeDeCiclos = 0;
	}
	
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
	
	public Integer getCiclos() {
		return quantidadeDeCiclos;
	}

	public void completarPrograma(Programa programa) {
		programa.indisponibilizar();
		
		if(isTodosProgramasCompletos()){
			quantidadeDeCiclos++;
			disponibilizarTodosProgramas();
		}
	}
	
	public boolean isTodosProgramasCompletos(){
		return ! programas.stream().filter(p -> p.isDisponivel()).findAny().isPresent();
	}
	
	public void disponibilizarTodosProgramas(){
		programas.forEach(p -> p.disponibilizar());
	}
}
