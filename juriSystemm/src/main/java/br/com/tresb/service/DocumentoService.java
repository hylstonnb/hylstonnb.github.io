package br.com.tresb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.DocumentoDAO;
import br.com.tresb.model.Documento;

@Service
public class DocumentoService extends GenericService<Documento, DocumentoDAO> {

	@Autowired
	private DocumentoDAO dao;

	@Override
	public DocumentoDAO getDao() {

		return dao;
	}

	@Override
	protected boolean validar(Documento documento) {

		String mensagem = "Já existe um documento cadastrado com esse nome.";

		boolean result = !this.registroJaExiste(documento, this.getDao().obterPorDescricao(documento.getDescricao()), mensagem);

		return result;
	}
}
