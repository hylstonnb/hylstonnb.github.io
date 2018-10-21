package br.com.tresb.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.AdvogadoDAO;
import br.com.tresb.dao.DocumentoDAO;
import br.com.tresb.dao.LocalPericiaDAO;
import br.com.tresb.dao.ProcessoDAO;
import br.com.tresb.enums.EnumEstagioProcesso;
import br.com.tresb.model.Processo;
import br.com.tresb.model.Advogado;
import br.com.tresb.model.Documento;
import br.com.tresb.model.LocalAudiencia;
import br.com.tresb.util.UtilMessages;

@Service
public class ProcessoService extends GenericService<Processo, ProcessoDAO> {

	@Autowired
	private ProcessoDAO dao;

	@Autowired
	private DocumentoDAO daoDocumento;
	
	@Autowired
	private AdvogadoDAO daoAdvogado;

	@Autowired
	private LocalPericiaDAO daoLocalPericia;

	public List<Documento> listarDocumentos() {

		return (List<Documento>) this.getDaoDocumento().listar();
	}

	public List<LocalAudiencia> listarLocalPericia() {

		return (List<LocalAudiencia>) this.getDaoLocalPericia().listar();
	}

	@Override
	public ProcessoDAO getDao() {

		return dao;
	}
	
	public Collection<Advogado> listarAdvogados(){
		
		return daoAdvogado.listar();
	}

	@Override
	protected boolean validar(Processo entidade) {

		boolean result = true;

//		if (entidade.getTipo() == null) {
//
//			UtilMessages.addMessageWarn("Selecione o tipo do processo.");
//
//			result = false;
//
//		} else if (entidade.getStatusProcesso().equals(EnumEstagioProcesso.EM_ANALISE) && entidade.getDataEnvio() == null) {
//
//			UtilMessages.addMessageWarn("Selecione a data de envio");
//
//			result = false;
//		}

		return result;
	}

	private Calendar obterDataInicioMesAtual() {

		Calendar dataInicio = Calendar.getInstance();

		dataInicio.setTime(new Date());

		dataInicio.set(Calendar.DAY_OF_MONTH, 1);

		dataInicio.set(Calendar.HOUR_OF_DAY, 0);

		dataInicio.set(Calendar.MINUTE, 0);

		dataInicio.set(Calendar.SECOND, 0);

		dataInicio.set(Calendar.MILLISECOND, 0);

		return dataInicio;
	}

	private Calendar obterDataFimMesAtual() {

		Calendar dataFim = Calendar.getInstance();

		dataFim.setTime(new Date());

		dataFim.set(Calendar.DAY_OF_MONTH, dataFim.getActualMaximum(Calendar.DAY_OF_MONTH));

		dataFim.set(Calendar.HOUR_OF_DAY, 23);

		dataFim.set(Calendar.MINUTE, 59);

		dataFim.set(Calendar.SECOND, 59);

		return dataFim;
	}

	private Calendar obterDataInicioMesAnterior() {

		Calendar dataInicio = Calendar.getInstance();

		dataInicio.setTime(new Date());

		dataInicio.set(Calendar.MONTH, dataInicio.get(Calendar.MONTH) - 1);

		dataInicio.set(Calendar.DAY_OF_MONTH, 1);

		dataInicio.set(Calendar.HOUR_OF_DAY, 0);

		dataInicio.set(Calendar.MINUTE, 0);

		dataInicio.set(Calendar.SECOND, 0);

		dataInicio.set(Calendar.MILLISECOND, 0);

		return dataInicio;
	}

	private Calendar obterDataFimMesAnterior() {

		Calendar dataFim = Calendar.getInstance();

		dataFim.setTime(new Date());

		dataFim.set(Calendar.MONTH, dataFim.get(Calendar.MONTH) - 1);

		dataFim.set(Calendar.DAY_OF_MONTH, dataFim.getActualMaximum(Calendar.DAY_OF_MONTH));

		dataFim.set(Calendar.HOUR_OF_DAY, 23);

		dataFim.set(Calendar.MINUTE, 59);

		dataFim.set(Calendar.SECOND, 59);

		return dataFim;
	}

	public List<Processo> obterEnviadosNoMesAtual() {

		List<Processo> processos = (List<Processo>) this.getDao().listarEnviadosNoMes(this.obterDataInicioMesAtual().getTime(),
				this.obterDataFimMesAtual().getTime());

		return processos;
	}

	public List<Processo> obterEnviadosNoMesAnterior() {

		List<Processo> processos = (List<Processo>) this.getDao().listarEnviadosNoMes(this.obterDataInicioMesAnterior().getTime(),
				this.obterDataFimMesAnterior().getTime());

		return processos;
	}

	public Map<String, Integer> obterQtdeProcEnvPorCaptadorMesAtual() {

		Map<String, Integer> captadores = new TreeMap<String, Integer>();

		for (Processo processo : this.obterEnviadosNoMesAtual()) {

			String nome = processo.getCliente().getIndicante().getNome();

			if (captadores.containsKey(nome)) {

				captadores.put(nome, captadores.get(nome) + 1);

			} else {

				captadores.put(nome, 1);
			}
		}

		return captadores;
	}

	public Map<String, Integer> obterQtdeProcEnvPorCaptadorMesAnterior() {

		Map<String, Integer> captadores = new TreeMap<String, Integer>();

		for (Processo processo : this.obterEnviadosNoMesAnterior()) {

			String nome = processo.getCliente().getIndicante().getNome();

			// System.out.println(processo.getCliente().getNome());

			if (captadores.containsKey(nome)) {

				captadores.put(nome, captadores.get(nome) + 1);

			} else {

				captadores.put(nome, 1);
			}
		}

		return captadores;
	}

	public void obterQtdeProcRecebidoMesAnterior() {

		int qtde = this.getDao().obterQtdeProcRecebidosNoPeriodo(this.obterDataInicioMesAnterior().getTime(),
				this.obterDataFimMesAnterior().getTime());

		System.out.println("Foram recebidos " + qtde + " processos no mês passado.");
	}

	public DocumentoDAO getDaoDocumento() {

		return daoDocumento;
	}

	public LocalPericiaDAO getDaoLocalPericia() {

		return daoLocalPericia;
	}
	
}
