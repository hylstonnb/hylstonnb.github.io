package br.com.tresb.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.tresb.enums.EnumSexo;
import br.com.tresb.enums.EnumStatus;

@Entity(name = "cliente")
public class Cliente extends Entidade {

	private static final long serialVersionUID = 2788779348497535503L;

	private String nome;

	private String cpf;

	private String email;

	private EnumSexo sexo;

	private String observacoes;
	
	@OneToMany(mappedBy="cliente", targetEntity = Processo.class, fetch = FetchType.LAZY)
	private List<Processo> processos;
	
	@ManyToOne
	private Empresa empresa;
	
	@ManyToOne
	private Indicante indicante;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;

	@Embedded
	private Endereco endereco;

	@ElementCollection(targetClass = Telefone.class)
	List<Telefone> telefones;

	@Transient
	private String telefoneFormatado;

	@Transient
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

	public Endereco getEndereco() {

		return endereco;
	}

	public void setEndereco(Endereco endereco) {

		this.endereco = endereco;
	}

	public List<Telefone> getTelefones() {

		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {

		this.telefones = telefones;
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
	public EnumStatus getStatus() {

		return status;
	}

	@Override
	public void setStatus(EnumStatus status) {

		this.status = status;
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

	public Date getDataCadastro() {

		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {

		this.dataCadastro = dataCadastro;
	}

	public EnumSexo getSexo() {

		return sexo;
	}

	public void setSexo(EnumSexo sexo) {

		this.sexo = sexo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Indicante getIndicante() {
		return indicante;
	}

	public void setIndicante(Indicante indicante) {
		this.indicante = indicante;
	}

}
