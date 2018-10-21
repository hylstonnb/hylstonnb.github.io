package br.com.tresb.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.ClienteDAO;
import br.com.tresb.dao.LigacaoDAO;
import br.com.tresb.model.Cliente;
import br.com.tresb.model.HistoricoLigacao;
import br.com.tresb.model.Ligacao;
import br.com.tresb.model.Telefone;
import br.com.tresb.model.Usuario;
import br.com.tresb.resources.FacesResources;
import br.com.tresb.util.UtilMessages;
import br.com.tresb.util.UtilString;

@Service
public class LigacaoService extends GenericService<Ligacao, LigacaoDAO> {

	@Autowired
	private LigacaoDAO dao;

	@Autowired
	private ClienteDAO daoCliente;

	public List<Cliente> listarClientes() {

		return (List<Cliente>) this.getDaoCliente().listar();
	}

	public List<Ligacao> listarLigacaoPorCliente(Long idCliente) {

		return (List<Ligacao>) this.getDao().listarPorCliente(idCliente);
	}

	public int reagendarLigacoesPreFechamento() {

		List<Ligacao> ligacoes = (List<Ligacao>) this.getDao().consultarRegistrosNaoReagendados(this.obterDataUmDiaAnterior().getTime());

		for (Ligacao ligacao : ligacoes) {

			ligacao.setDataHora(this.obterDataInicio().getTime());

			this.getDao().mesclar(ligacao);
		}

		return ligacoes.size();
	}

	public int reagendarLigacoesPosFechamento() {

		List<Ligacao> ligacoes = (List<Ligacao>) this.getDao().consultarRegistrosNaoReagendados(this.obterDataUmDiaAnterior().getTime());

		for (Ligacao ligacao : ligacoes) {

			ligacao.setDataHora(this.obterDataInicio().getTime());

			this.getDao().mesclar(ligacao);
		}

		return ligacoes.size();
	}

	public Ligacao obterPorIdCliente(Long id) {

		Ligacao ligacao = this.getDao().obterPorIdCliente(id);

		if (ligacao != null && ligacao.getCliente() != null) {

			return ligacao;

		} else {

			return null;
		}
	}

	// public boolean salvarReagendar(Ligacao ligacao, String resumo) {
	//
	// ligacao.getHistorico().add(this.preencherHistorico(resumo));
	//
	// super.salvar(ligacao);
	//
	// boolean reagendar = false;
	//
	// if
	// (ligacao.getStatusAgendamento().equals(EnumStatusAgendamento.REAGENDADA))
	// {
	//
	// reagendar = true;
	//
	// UtilMessages.addMessageInfo("Preenha os dados da nova ligação.");
	// }
	//
	// return reagendar;
	// }

	public HistoricoLigacao preencherHistorico(String resumo) {

		if (UtilString.isNotEmpty(resumo)) {

			HistoricoLigacao hl = new HistoricoLigacao();

			hl.setDataHora(new Date());

			hl.setResumo(resumo);

			hl.setUsuario((Usuario) FacesResources.getAttSession("usuarioAutenticado"));

			return hl;

		} else {

			return null;
		}
	}

	private Calendar obterDataInicio() {

		Calendar dataInicio = Calendar.getInstance();

		dataInicio.setTime(new Date());

		dataInicio.set(Calendar.HOUR_OF_DAY, 0);

		dataInicio.set(Calendar.MINUTE, 0);

		dataInicio.set(Calendar.SECOND, 0);

		dataInicio.set(Calendar.MILLISECOND, 0);

		return dataInicio;
	}

	private Calendar obterDataUmDiaAnterior() {

		Calendar data = Calendar.getInstance();

		data.setTime(new Date());

		data.set(Calendar.HOUR_OF_DAY, 23);

		data.set(Calendar.MINUTE, 59);

		data.set(Calendar.SECOND, 59);

		// data.set(Calendar.DAY_OF_MONTH, data. - 1);
		data.add(Calendar.DAY_OF_MONTH, -1);

		return data;
	}

	private Calendar obterDataFim() {

		Calendar dataFim = Calendar.getInstance();

		dataFim.setTime(new Date());

		dataFim.set(Calendar.HOUR_OF_DAY, 23);

		dataFim.set(Calendar.MINUTE, 59);

		dataFim.set(Calendar.SECOND, 59);

		return dataFim;
	}

	public List<Ligacao> listarLigacoesDoDia() {

		List<Ligacao> ligacoes = (List<Ligacao>) this.getDao().listarPorIntervaloData(this.obterDataInicio().getTime(),
				this.obterDataFim().getTime());

		this.carregarTelefones(ligacoes);

		return ligacoes;
	}

	public String formatarTelefones(List<Telefone> telefones) {

		String telefonesf = "";

		for (Telefone telefone : telefones) {

			telefonesf += telefone.getNumero() + " : ";

			if (telefone.getOperadora() != null) {

				telefonesf += telefone.getOperadora().getNome();

			} else {

				telefonesf += "Indefinida";
			}

			telefonesf += " / ";
		}

		if (telefonesf.length() > 1) {

			telefonesf = telefonesf.substring(0, telefonesf.length() - 2);
		}

		return telefonesf;
	}

	private void carregarTelefones(List<Ligacao> ligacoes) {

		for (Ligacao ligacao : ligacoes) {

			String telefones = "";

			for (Telefone telefone : ligacao.getCliente().getTelefones()) {

				telefones += telefone.getNumero() + " / ";
			}

			if (telefones.length() > 1) {

				telefones = telefones.substring(0, telefones.length() - 3);
			}

			ligacao.getCliente().setTelefoneFormatado(telefones);
		}
	}

	@Override
	public LigacaoDAO getDao() {

		return dao;
	}

	public void setDao(LigacaoDAO dao) {

		this.dao = dao;
	}

	@Override
	protected boolean validar(Ligacao entidade) {

		boolean result = entidade.getCliente() != null;

		if (result == false) {

			UtilMessages.addMessageWarn("Cliente não pode ser nulo!");
		}

		return result;
	}

	public ClienteDAO getDaoCliente() {

		return daoCliente;
	}

}
