package br.com.tresb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.AdvogadoDAO;
import br.com.tresb.model.Advogado;
import br.com.tresb.model.Telefone;
import br.com.tresb.util.UtilMessages;
import br.com.tresb.util.UtilObjeto;

@Service
public class AdvogadoService extends GenericService<Advogado, AdvogadoDAO>{
	
	@Autowired
	private AdvogadoDAO dao;
	
	private final String msgRegistroJaExiste = "Já existe um cliente cadastrado com esse nome.";
	
	public boolean salvar(Advogado advogado, List<Telefone> telefones) {

		if (this.validar(advogado)) {

			advogado.setTelefones(new ArrayList<Telefone>());

			for (Telefone telefone : telefones) {

				if (UtilObjeto.isNotEmpty(telefone.getNumero())) {

					advogado.getTelefones().add(telefone);
				}
			}

			return super.salvar(advogado);
		}

		return false;
	}
	
	@Override
	public AdvogadoDAO getDao() {
		return dao;
	}

	@Override
	protected boolean validar(Advogado advogado) {
		boolean result = false;

		List<String> camposObrigatorios = new ArrayList<String>();

		if (UtilObjeto.isEmpty(advogado.getNome())) {

			camposObrigatorios.add("Nome");
		}

		if (UtilObjeto.isEmpty(advogado.getNumOab())) {

			camposObrigatorios.add("Num. Inscrição OAB");
		}

		if (camposObrigatorios.isEmpty()) {

			result = !this.registroJaExiste(advogado, this.getDao().obterPorNome(advogado.getNome()), msgRegistroJaExiste);

		} else {

			UtilMessages.addMessageCamposObrigatorios(camposObrigatorios);
		}

		return result;
	}

}
