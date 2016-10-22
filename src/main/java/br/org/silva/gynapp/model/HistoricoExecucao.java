package br.org.silva.gynapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import br.org.silva.gynapp.interfaces.Entidade;

@Entity
public class HistoricoExecucao implements Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6169074114527882644L;

	@Id
	@SequenceGenerator(name="historico_execucao_id_seq", sequenceName="historico_execucao_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="historico_execucao_id_seq")
	private Long id;
	
	@Column 
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@ManyToOne(optional=false)
	private Execucao execucao;
	
	public HistoricoExecucao() {}
	
	public HistoricoExecucao(Execucao execucao){
		this.execucao = execucao;
		this.dataCriacao = new Date();
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	public Execucao getExecucao() {
		return execucao;
	}

	public void setExecucao(Execucao execucao) {
		this.execucao = execucao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
}
