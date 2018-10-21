package br.com.tresb.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.tresb.enums.EnumEstagioLigacao;
import br.com.tresb.enums.EnumPrioridade;
import br.com.tresb.enums.EnumStatus;

@Entity(name = "ligacao")
public class Ligacao extends Entidade {

	private static final long serialVersionUID = 6478987758156275515L;

	private String assunto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@Enumerated(EnumType.ORDINAL)
	private EnumPrioridade prioridade;

	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;
	
	@Enumerated(EnumType.STRING)
	private EnumEstagioLigacao estagio;

	@OneToOne
	private Cliente cliente;

	@Transient
	private String dataHoraFormatada;

	@ElementCollection(targetClass = HistoricoLigacao.class)
	@OrderBy(value = "dataHora DESC")
	List<HistoricoLigacao> historico;

	@Override
	public String getPropriedadeRelevante() {

		return "";
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {

	}

	@Override
	public EnumStatus getStatus() {

		return status;
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

	public String getAssunto() {

		return assunto;
	}

	public void setAssunto(String assunto) {

		this.assunto = assunto;
	}

	public Cliente getCliente() {

		return cliente;
	}

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}

	public EnumPrioridade getPrioridade() {

		return prioridade;
	}

	public void setPrioridade(EnumPrioridade prioridade) {

		this.prioridade = prioridade;
	}

	public String getDataHoraFormatada() {

		if (this.getDataHora() != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			this.dataHoraFormatada = sdf.format(this.getDataHora());
		}

		return dataHoraFormatada;
	}

	public void setDataHoraFormatada(String dataHoraFormatada) {

		this.dataHoraFormatada = dataHoraFormatada;
	}

	public List<HistoricoLigacao> getHistorico() {

		return historico;
	}

	public void setHistorico(List<HistoricoLigacao> historico) {

		this.historico = historico;
	}

}
