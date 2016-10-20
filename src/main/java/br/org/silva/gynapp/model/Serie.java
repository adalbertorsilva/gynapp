package br.org.silva.gynapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.org.silva.gynapp.interfaces.Entidade;

@Entity
public class Serie implements Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542705248213488603L;

	@Id
	@SequenceGenerator(name="serie_id_seq", sequenceName="serie_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="serie_id_seq")
	private Long id;
	
	@Column
	private String descricao;
	
	public Serie() {}

	public Serie(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

}
