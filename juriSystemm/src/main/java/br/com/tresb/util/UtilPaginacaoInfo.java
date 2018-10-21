package br.com.tresb.util;

import java.util.Map;

import org.primefaces.model.SortOrder;

/**
 * Responsavel por armazenar os dados necessarios para a utilizacao de paginacao
 * lazy de tabelas. O objetivo da classe é concentrar os dados necessarios para
 * a paginacao lazy em um objeto.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public class UtilPaginacaoInfo {

	private int first;

	private int pageSize;

	private int rowCount;

	private String sortField;

	private SortOrder sortOrder;

	private Map<String, String> filters;

	public UtilPaginacaoInfo(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

		super();

		this.first = first;

		this.pageSize = pageSize;

		this.sortField = sortField;

		this.sortOrder = sortOrder != null ? sortOrder : SortOrder.ASCENDING;

		this.filters = filters;
	}

	public int getFirst() {

		return first;
	}

	public void setFirst(int first) {

		this.first = first;
	}

	public int getPageSize() {

		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}

	public int getRowCount() {

		return rowCount;
	}

	public void setRowCount(int rowCount) {

		this.rowCount = rowCount;
	}

	public String getSortField() {

		return sortField;
	}

	public void setSortField(String sortField) {

		this.sortField = sortField;
	}

	public SortOrder getSortOrder() {

		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {

		this.sortOrder = sortOrder;
	}

	public Map<String, String> getFilters() {

		return filters;
	}

	public void setFilters(Map<String, String> filters) {

		this.filters = filters;
	}

}