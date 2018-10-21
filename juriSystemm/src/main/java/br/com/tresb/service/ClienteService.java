package br.com.tresb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.IndicanteDAO;
import br.com.tresb.dao.ClienteDAO;
import br.com.tresb.dao.LigacaoDAO;
import br.com.tresb.dao.ProcessoDAO;
import br.com.tresb.enums.EnumStatus;
import br.com.tresb.model.Indicante;
import br.com.tresb.model.Cliente;
import br.com.tresb.model.Ligacao;
import br.com.tresb.model.Processo;
import br.com.tresb.model.Telefone;
import br.com.tresb.util.UtilMessages;
import br.com.tresb.util.UtilObjeto;

@Service
public class ClienteService extends GenericService<Cliente, ClienteDAO> {

	@Autowired
	private ClienteDAO dao;

	@Autowired
	private IndicanteDAO daoIndicante;

	@Autowired
	private LigacaoDAO daoLigacao;

	@Autowired
	private ProcessoDAO daoProcesso;

	public boolean salvar(Cliente cliente, List<Telefone> telefones) {

		if (this.validar(cliente)) {

			cliente.setTelefones(new ArrayList<Telefone>());

			for (Telefone telefone : telefones) {

				if (UtilObjeto.isNotEmpty(telefone.getNumero())) {

					cliente.getTelefones().add(telefone);
				}
			}

			return super.salvar(cliente);
		}

		return false;
	}

	@Override
	public void excluir(Long id) {

		this.getDao().carregarEntidade(id);

		List<Processo> processos = (List<Processo>) this.getDaoProcesso().listarPorCliente(id);

		for (Processo processo : processos) {

			processo.setStatus(EnumStatus.EXCLUIDO);

			this.getDaoProcesso().mesclar(processo);
		}

		Ligacao ligacao = this.getDaoLigacao().obterPorIdCliente(id);

		if (ligacao != null) {

			ligacao.setStatus(EnumStatus.EXCLUIDO);

			this.getDaoLigacao().mesclar(ligacao);
		}

		super.excluir(id);
	}

	@Override
	public ClienteDAO getDao() {

		return dao;
	}

	@Override
	protected boolean validar(Cliente cliente) {

		boolean result = false;

		List<String> camposObrigatorios = new ArrayList<String>();

		if (UtilObjeto.isEmpty(cliente.getNome())) {

			camposObrigatorios.add("Nome");
		}

		if (cliente.getIndicante() == null) {

			camposObrigatorios.add("indicante");
		}

		if (camposObrigatorios.isEmpty()) {

			String mensagem = "Já existe um cliente cadastrado com esse nome.";

			result = !this.registroJaExiste(cliente, this.getDao().obterPorNome(cliente.getNome()), mensagem);

		} else {

			UtilMessages.addMessageCamposObrigatorios(camposObrigatorios);
		}

		return result;
	}

	@Override
	public List<Cliente> listar() {

		List<Cliente> clientes = super.listar();

		this.carregarTelefones(clientes);

		return clientes;
	}

	public List<Cliente> listarClientesSemLigacao() {

		List<Long> idClientes = this.getDaoLigacao().obterIdClientesComLigacao();

		List<Cliente> clientes = (List<Cliente>) this.getDao().listarClientesSemLigacao(idClientes);

		this.carregarTelefones(clientes);

		return clientes;
	}

	private void carregarTelefones(List<Cliente> clientes) {

		for (Cliente cliente : clientes) {

			String telefones = "";

			for (Telefone telefone : cliente.getTelefones()) {

				telefones += telefone.getNumero() + " / ";
			}

			if (telefones.length() > 1) {

				telefones = telefones.substring(0, telefones.length() - 3);
			}

			cliente.setTelefoneFormatado(telefones);
		}
	}

	public List<Ligacao> listarLigacaoPorCliente(Long idCliente) {

		return (List<Ligacao>) this.getDaoLigacao().listarPorCliente(idCliente);
	}

	public List<Processo> listarProcessoPorCliente(Long idCliente) {

		return (List<Processo>) this.getDaoProcesso().listarPorCliente(idCliente);
	}

	public List<Indicante> listarIndicantes() {

		return (List<Indicante>) this.getDaoindicante().listar();
	}

	public IndicanteDAO getDaoindicante() {

		return daoIndicante;
	}

	public LigacaoDAO getDaoLigacao() {

		return daoLigacao;
	}

	public ProcessoDAO getDaoProcesso() {

		return daoProcesso;
	}
}
