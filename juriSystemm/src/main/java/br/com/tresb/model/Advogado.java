package br.com.tresb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import br.com.tresb.enums.EnumEstadosBR;
import br.com.tresb.enums.EnumStatus;

@Entity(name = "advogado")
public class Advogado extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8899961837299177179L;

	private String nome;

	private String cpf;

	private String numOab;

	@Enumerated(EnumType.STRING)
	private EnumEstadosBR ufInscricaoOab;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "advogado_empresa")
	private List<Empresa> empresasOndeAtua;

	@Transient
	private String telefoneFormatado;

	@ElementCollection(targetClass = Telefone.class)
	List<Telefone> telefones;

	@Embedded
	private Endereco endereco;

	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;

	@Override
	public String getPropriedadeRelevante() {
		return this.getNome();
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

	public String getNumOab() {
		return numOab;
	}

	public void setNumOab(String numOab) {
		this.numOab = numOab;
	}

	public EnumEstadosBR getUfInscricaoOab() {
		return ufInscricaoOab;
	}

	public void setUfInscricaoOab(EnumEstadosBR ufInscricaoOab) {
		this.ufInscricaoOab = ufInscricaoOab;
	}

	public List<Empresa> getEmpresasOndeAtua() {
		return empresasOndeAtua;
	}

	public void setEmpresasOndeAtua(List<Empresa> empresasOndeAtua) {
		this.empresasOndeAtua = empresasOndeAtua;
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {
		this.setNome(propriedadeRelevante);

	}

	@Override
	public EnumStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public String getTelefoneFormatado() {
		return telefoneFormatado;
	}

	public void setTelefoneFormatado(String telefoneFormatado) {
		this.telefoneFormatado = telefoneFormatado;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
