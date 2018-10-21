package br.com.tresb.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.tresb.enums.EnumStatus;

@Entity(name = "indicante")
public class Indicante extends Entidade {

	private static final long serialVersionUID = -3971025929529201107L;

	private String nome;

	private String cpf;

	private String email;

	private String observacoes;

	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;

	@OneToOne
	private Empresa empresa;

	@ElementCollection(targetClass = Telefone.class)
	private List<Telefone> telefones;

	@Transient
	private String telefoneFormatado;

	public List<Telefone> getTelefones() {

		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {

		this.telefones = telefones;
	}

	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public String getCpf() {

		return cpf;
	}

	public void setCpf(String cpf) {

		this.cpf = cpf;
	}

	@Override
	public EnumStatus getStatus() {

		return status;
	}

	@Override
	public void setStatus(EnumStatus status) {

		this.status = status;
	}

	public String getObservacoes() {

		return observacoes;
	}

	public void setObservacoes(String observacoes) {

		this.observacoes = observacoes;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	@Override
	public String getPropriedadeRelevante() {

		return this.getNome();
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {

		this.setNome(propriedadeRelevante);
	}

	public String getTelefoneFormatado() {

		return telefoneFormatado;
	}

	public void setTelefoneFormatado(String telefoneFormatado) {

		this.telefoneFormatado = telefoneFormatado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
