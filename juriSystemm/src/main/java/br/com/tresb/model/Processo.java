package br.com.tresb.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.tresb.enums.EnumPrioridade;
import br.com.tresb.enums.EnumProcFinalizado;
import br.com.tresb.enums.EnumStatus;

@Entity(name = "processo")
public class Processo extends Entidade {

	private static final long serialVersionUID = 1L;

	private EnumStatus status;
	
	private String naturezaProcesso;
	
	private String observacaoDocs;

	private BigDecimal valorCliente;

	private BigDecimal honorarioSucumbencia;

	private Integer porcentagem;

	private BigDecimal valorRecebido;

	private String estagioProcesso;

	private EnumPrioridade prioridade;

	private LocalAudiencia localAudiencia;

	private EnumProcFinalizado finalizacaoProcesso;

	@Temporal(TemporalType.DATE)
	private Date dataProtocoloInterlocutoria;

	@Temporal(TemporalType.DATE)
	private Date dataInicioPrazo;

	@Temporal(TemporalType.DATE)
	private Date dataFimPrazo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraAudiencia;

	@Temporal(TemporalType.DATE)
	private Date dataProtocoloInicial;

	@Temporal(TemporalType.DATE)
	private Date vencPrazoImpugnacao;

	@ManyToOne
	private Cliente cliente;

	@ManyToMany
	@JoinTable(name = "processo_advogado")
	private List<Advogado> advogados;

	@ManyToMany
	@JoinTable(name = "processo_documentosentregues")
	private List<Documento> documentosEntregues;

	@OneToMany(mappedBy = "processo", targetEntity = HistoricoProcesso.class, fetch = FetchType.LAZY)
	private List<HistoricoProcesso> historico;

	@ManyToMany
	@JoinTable(name = "processo_documentosfaltantes")
	private List<Documento> documentosFaltantes;

	@Transient
	private BigDecimal valorReceber;

	@Override
	public String getPropriedadeRelevante() {
		return this.getId().toString();
	}

	@Override
	public void setPropriedadeRelevante(String propriedadeRelevante) {

	}

	@Override
	public EnumStatus getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public BigDecimal getValorCliente() {
		return valorCliente;
	}

	public void setValorCliente(BigDecimal valorCliente) {
		this.valorCliente = valorCliente;
	}

	public BigDecimal getHonorarioSucumbencia() {
		return honorarioSucumbencia;
	}

	public void setHonorarioSucumbencia(BigDecimal honorarioSucumbencia) {
		this.honorarioSucumbencia = honorarioSucumbencia;
	}

	public Integer getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Integer porcentagem) {
		this.porcentagem = porcentagem;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public String getEstagioProcesso() {
		return estagioProcesso;
	}

	public void setEstagioProcesso(String estagioProcesso) {
		this.estagioProcesso = estagioProcesso;
	}

	public EnumPrioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(EnumPrioridade prioridade) {
		this.prioridade = prioridade;
	}

	public LocalAudiencia getLocalAudiencia() {
		return localAudiencia;
	}

	public void setLocalAudiencia(LocalAudiencia localAudiencia) {
		this.localAudiencia = localAudiencia;
	}

	public EnumProcFinalizado getFinalizacaoProcesso() {
		return finalizacaoProcesso;
	}

	public void setFinalizacaoProcesso(EnumProcFinalizado finalizacaoProcesso) {
		this.finalizacaoProcesso = finalizacaoProcesso;
	}

	public Date getDataProtocoloInterlocutoria() {
		return dataProtocoloInterlocutoria;
	}

	public void setDataProtocoloInterlocutoria(Date dataProtocoloInterlocutoria) {
		this.dataProtocoloInterlocutoria = dataProtocoloInterlocutoria;
	}

	public Date getDataInicioPrazo() {
		return dataInicioPrazo;
	}

	public void setDataInicioPrazo(Date dataInicioPrazo) {
		this.dataInicioPrazo = dataInicioPrazo;
	}

	public Date getDataFimPrazo() {
		return dataFimPrazo;
	}

	public void setDataFimPrazo(Date dataFimPrazo) {
		this.dataFimPrazo = dataFimPrazo;
	}

	public Date getDataHoraAudiencia() {
		return dataHoraAudiencia;
	}

	public void setDataHoraAudiencia(Date dataHoraAudiencia) {
		this.dataHoraAudiencia = dataHoraAudiencia;
	}

	public Date getDataProtocoloInicial() {
		return dataProtocoloInicial;
	}

	public void setDataProtocoloInicial(Date dataProtocoloInicial) {
		this.dataProtocoloInicial = dataProtocoloInicial;
	}

	public Date getVencPrazoImpugnacao() {
		return vencPrazoImpugnacao;
	}

	public void setVencPrazoImpugnacao(Date vencPrazoImpugnacao) {
		this.vencPrazoImpugnacao = vencPrazoImpugnacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Advogado> getAdvogados() {
		return advogados;
	}

	public void setAdvogados(List<Advogado> advogados) {
		this.advogados = advogados;
	}

	public List<Documento> getDocumentosEntregues() {
		return documentosEntregues;
	}

	public void setDocumentosEntregues(List<Documento> documentosEntregues) {
		this.documentosEntregues = documentosEntregues;
	}

	public List<HistoricoProcesso> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoProcesso> historico) {
		this.historico = historico;
	}

	public List<Documento> getDocumentosFaltantes() {
		return documentosFaltantes;
	}

	public void setDocumentosFaltantes(List<Documento> documentosFaltantes) {
		this.documentosFaltantes = documentosFaltantes;
	}

	public BigDecimal getValorReceber() {
		return valorReceber;
	}

	public void setValorReceber(BigDecimal valorReceber) {
		this.valorReceber = valorReceber;
	}

	public String getNaturezaProcesso() {
		return naturezaProcesso;
	}

	public void setNaturezaProcesso(String naturezaProcesso) {
		this.naturezaProcesso = naturezaProcesso;
	}

	public String getObservacaoDocs() {
		return observacaoDocs;
	}

	public void setObservacaoDocs(String observacaoDocs) {
		this.observacaoDocs = observacaoDocs;
	}

}
