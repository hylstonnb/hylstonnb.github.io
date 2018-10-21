package br.com.tresb.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.tresb.enums.EnumAcoes;
import br.com.tresb.enums.EnumStatus;

@Entity(name = "historico")
public class Historico extends Entidade {

	private static final long serialVersionUID = -611753407009410089L;

	private Long idEntidade;

	private String classe;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@Enumerated(EnumType.ORDINAL)
	private EnumAcoes acao;

	@OneToOne
	private Usuario usuario;

	public EnumAcoes getAcao() {

		return acao;
	}

	public void setAcao(EnumAcoes acao) {

		this.acao = acao;
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

	public Date getDataHora() {

		return dataHora;
	}

	public void setDataHora(Date dataHora) {

		this.dataHora = dataHora;
	}

	public Long getIdEntidade() {

		return idEntidade;
	}

	public void setIdEntidade(Long idEntidade) {

		this.idEntidade = idEntidade;
	}

	@Override
	public String getPropriedadeRelevante() {

		return "";
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {

	}

	public String getDataHoraFormatada() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

		return sdf.format(this.getDataHora());
	}

	@Override
	public EnumStatus getStatus() {

		return null;
	}

	public String getClasse() {

		return classe;
	}

	public void setClasse(String classe) {

		this.classe = classe;
	}

	@Override
	public void setStatus(EnumStatus status) {

	}
}
