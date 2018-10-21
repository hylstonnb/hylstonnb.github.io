package br.com.tresb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.tresb.enums.EnumEstadosBR;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 825841898027594986L;

	private String logradouro;

	private String complemento;

	private String numero;

	private String bairro;

	private String pontoReferencia;

	private String cidade;

	@Column(length = 10)
	private String cep;

	@Enumerated(EnumType.ORDINAL)
	private EnumEstadosBR estado;

	public String getCep() {

		return cep;
	}

	public void setCep(String cep) {

		this.cep = cep;
	}

	public String getLogradouro() {

		return logradouro;
	}

	public void setLogradouro(String logradouro) {

		this.logradouro = logradouro;
	}

	public String getComplemento() {

		return complemento;
	}

	public void setComplemento(String complemento) {

		this.complemento = complemento;
	}

	public String getNumero() {

		return numero;
	}

	public void setNumero(String numero) {

		this.numero = numero;
	}

	public EnumEstadosBR getEstado() {

		return estado;
	}

	public void setEstado(EnumEstadosBR estado) {

		this.estado = estado;
	}

	public String getCidade() {

		return cidade;
	}

	public void setCidade(String cidade) {

		this.cidade = cidade;
	}

	public String getBairro() {

		return bairro;
	}

	public void setBairro(String bairro) {

		this.bairro = bairro;
	}

	public String getPontoReferencia() {

		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {

		this.pontoReferencia = pontoReferencia;
	}

}
