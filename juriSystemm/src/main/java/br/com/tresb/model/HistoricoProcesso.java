package br.com.tresb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.tresb.enums.EnumStatus;

@Entity(name = "historicoprocesso")
public class HistoricoProcesso extends Entidade {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EnumStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;
	
	private Usuario usuario;
	
	private String estagio;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="processo_id")
	private Processo processo;

	@Override
	public String getPropriedadeRelevante() {
		return getId().toString();
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {
			
	}

	@Override
	public EnumStatus getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEstagio() {
		return estagio;
	}

	public void setEstagio(String estagio) {
		this.estagio = estagio;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
