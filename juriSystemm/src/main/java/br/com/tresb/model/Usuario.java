package br.com.tresb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.tresb.enums.EnumPerfil;
import br.com.tresb.enums.EnumStatus;

@Entity(name = "usuario")
public class Usuario extends Entidade {

	private static final long serialVersionUID = 6599160552904929532L;

	private String nome;

	private String email;

	private Date dataNascimento;

	private String senha;

	private String salt;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_empresa")
	private List<Empresa> empresas;

	@OneToOne
	private Empresa empresaPrincipal;

	@Enumerated(EnumType.ORDINAL)
	private EnumPerfil perfil;

	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;

	@Transient
	private Empresa empresaAtual;
		
	@Transient
	private String senhaAtual;

	@Transient
	private String novaSenha;

	@Transient
	private String confirmacaoNovaSenha;

	@Override
	public EnumStatus getStatus() {

		return this.status;
	}

	@Override
	public void setStatus(EnumStatus status) {

		this.status = status;
	}

	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getSenha() {

		return senha;
	}

	public void setSenha(String senha) {

		this.senha = senha;
	}

	public EnumPerfil getPerfil() {

		return perfil;
	}

	public void setPerfil(EnumPerfil perfil) {

		this.perfil = perfil;
	}

	public String getSalt() {

		return salt;
	}

	public void setSalt(String salt) {

		this.salt = salt;
	}

	public String getSenhaAtual() {

		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {

		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {

		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {

		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoNovaSenha() {

		return confirmacaoNovaSenha;
	}

	public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {

		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
	}

	public Date getDataNascimento() {

		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {

		this.dataNascimento = dataNascimento;
	}

	@Override
	public String getPropriedadeRelevante() {

		return this.getNome();
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {

		this.setNome(propriedadeRelevante);
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresaPrincipal() {
		return empresaPrincipal;
	}

	public void setEmpresaPrincipal(Empresa empresaPrincipal) {
		this.empresaPrincipal = empresaPrincipal;
	}

	public Empresa getEmpresaAtual() {
		return empresaAtual;
	}

	public void setEmpresaAtual(Empresa empresaAtual) {
		this.empresaAtual = empresaAtual;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
