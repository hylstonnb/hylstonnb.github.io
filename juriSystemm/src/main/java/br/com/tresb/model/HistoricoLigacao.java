package br.com.tresb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class HistoricoLigacao {

	@Column(length = 1024)
	private String resumo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@ManyToOne
	private Usuario usuario;

	public String getResumo() {

		return resumo;
	}

	public void setResumo(String resumo) {

		this.resumo = resumo;
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

}
