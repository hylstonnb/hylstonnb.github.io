package br.com.tresb.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.com.tresb.enums.EnumStatus;

@Entity(name = "localAudiencia")
public class LocalAudiencia extends Entidade {

	private static final long serialVersionUID = 8285173577925382666L;

	private String nome;

	private EnumStatus status;
	
	@Embedded
	private Endereco endereco;

	@Override
	public String getPropriedadeRelevante() {

		return this.getNome();
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {

		this.setNome(propriedadeRelevante);
	}

	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	@Override
	public EnumStatus getStatus() {

		return status;
	}

	@Override
	public void setStatus(EnumStatus status) {

		this.status = status;
	}

}
