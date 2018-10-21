package br.com.tresb.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.tresb.enums.EnumStatus;

@Entity(name = "documento")
public class Documento extends Entidade {

	private static final long serialVersionUID = 1L;

	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;

	public String getDescricao() {

		return descricao;
	}

	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	@Override
	public EnumStatus getStatus() {

		return status;
	}

	@Override
	public void setStatus(EnumStatus status) {

		this.status = status;
	}

	@Override
	public String getPropriedadeRelevante() {

		return this.getDescricao();
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {

		this.setDescricao(propriedadeRelevante);
	}

}
