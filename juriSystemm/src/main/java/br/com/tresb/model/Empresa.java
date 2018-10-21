package br.com.tresb.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import br.com.tresb.enums.EnumStatus;

@Entity(name = "empresa")
public class Empresa extends Entidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6426326712571403738L;

	private String nomeFantasia;
	
	private String razaoSocial;
	
	private String cnpj;
	
	private String nomeResponsavel;
	
	@Transient
	private String telefoneFormatado;
	
	@ElementCollection(targetClass = Telefone.class)
	List<Telefone> telefones;

	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;
	
	@Embedded
	private Endereco endereco;

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
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

	@Override
	public String getPropriedadeRelevante() {
		
		return this.getNomeFantasia();
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {
		this.setNomeFantasia(propriedadeRelevante);
	}

	@Override
	public EnumStatus getStatus() {
		
		return this.status;
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
	
}
