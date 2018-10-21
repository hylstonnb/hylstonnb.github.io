package br.com.tresb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.EmpresaDAO;
import br.com.tresb.model.Empresa;
import br.com.tresb.model.Telefone;
import br.com.tresb.util.UtilMessages;
import br.com.tresb.util.UtilObjeto;

@Service
public class EmpresaService extends GenericService<Empresa, EmpresaDAO> {

	@Autowired
	private EmpresaDAO dao;

	@Override
	public EmpresaDAO getDao() {
		return dao;
	}
	
	private static final String msgRegistroJaExiste = "Já existe uma empresa cadastrada com esse nome fantasia.";

	@Override
	protected boolean validar(Empresa empresa) {

		boolean result = false;

		List<String> camposObrigatorios = new ArrayList<String>();

		if (UtilObjeto.isEmpty(empresa.getNomeFantasia())) {

			camposObrigatorios.add("Nome Fantasia");
		}

		if (camposObrigatorios.isEmpty()) {

			result = !this.registroJaExiste(empresa, this.getDao().obterPorNomeFantasia(empresa.getNomeFantasia()), msgRegistroJaExiste);

		} else {

			UtilMessages.addMessageCamposObrigatorios(camposObrigatorios);
		}

		return result;
	}

	@Override
	public boolean salvar(Empresa empresa) {

		if (this.validar(empresa)) {
			
			for (int i = 0; i < empresa.getTelefones().size();) {

				if (UtilObjeto.isEmpty(empresa.getTelefones().get(i).getNumero())) {

					empresa.getTelefones().remove(i);
					
				} else {
					
					i++;
				} 					
			}

			return super.salvar(empresa);
		}

		return false;
	}
	
	@Override
	public List<Empresa> listar() {

		List<Empresa> empresas = super.listar();

		this.carregarTelefones(empresas);

		return empresas;
	}

	private void carregarTelefones(List<Empresa> empresas) {

		for (Empresa empresa : empresas) {

			String telefones = "";

			for (Telefone telefone : empresa.getTelefones()) {

				if (UtilObjeto.isNotEmpty(telefone.getNumero())) {

					telefones += telefone.getNumero() + " / ";
				}
			}

			if (telefones.length() > 1) {

				telefones = telefones.substring(0, telefones.length() - 3);
			}

			empresa.setTelefoneFormatado(telefones);
		}
	}

}
