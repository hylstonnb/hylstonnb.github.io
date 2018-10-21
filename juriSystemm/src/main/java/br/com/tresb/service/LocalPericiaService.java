package br.com.tresb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tresb.dao.LocalPericiaDAO;
import br.com.tresb.model.LocalAudiencia;

@Service
public class LocalPericiaService extends GenericService<LocalAudiencia, LocalPericiaDAO> {

	@Autowired
	private LocalPericiaDAO dao;

	@Override
	public LocalPericiaDAO getDao() {

		return dao;
	}

	@Override
	protected boolean validar(LocalAudiencia entidade) {

		return true;
	}
}
