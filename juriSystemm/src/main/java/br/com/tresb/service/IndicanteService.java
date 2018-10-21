package br.com.tresb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.IndicanteDAO;
import br.com.tresb.model.Indicante;
import br.com.tresb.model.Telefone;
import br.com.tresb.util.UtilMessages;
import br.com.tresb.util.UtilObjeto;

@Service
public class IndicanteService extends GenericService<Indicante, IndicanteDAO> {

	@Autowired
	private IndicanteDAO dao;

	@Override
	public IndicanteDAO getDao() {

		return dao;
	}

	@Override
	public boolean salvar(Indicante captador) {

		if (this.validar(captador)) {
			
			for (int i = 0; i < captador.getTelefones().size();) {

				if (UtilObjeto.isEmpty(captador.getTelefones().get(i).getNumero())) {

					captador.getTelefones().remove(i);
				} else {
					
					i++;
				} 					
			}

			return super.salvar(captador);
		}

		return false;
	}

	@Override
	protected boolean validar(Indicante captador) {

		boolean result = false;

		List<String> camposObrigatorios = new ArrayList<String>();

		if (UtilObjeto.isEmpty(captador.getNome())) {

			camposObrigatorios.add("Nome");
		}

		if (camposObrigatorios.isEmpty()) {

			String mensagem = "Já existe um captador cadastrado com esse nome.";

			result = !this.registroJaExiste(captador, this.getDao().obterPorNome(captador.getNome()), mensagem);

		} else {

			UtilMessages.addMessageCamposObrigatorios(camposObrigatorios);
		}

		return result;
	}

	@Override
	public List<Indicante> listar() {

		List<Indicante> captadores = super.listar();

		this.carregarTelefones(captadores);

		return captadores;
	}

	private void carregarTelefones(List<Indicante> captadores) {

		for (Indicante captador : captadores) {

			String telefones = "";

			for (Telefone telefone : captador.getTelefones()) {

				if (UtilObjeto.isNotEmpty(telefone.getNumero())) {

					telefones += telefone.getNumero() + " / ";
				}
			}

			if (telefones.length() > 1) {

				telefones = telefones.substring(0, telefones.length() - 3);
			}

			captador.setTelefoneFormatado(telefones);
		}
	}

}
