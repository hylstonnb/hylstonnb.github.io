package br.com.tresb.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.tresb.enums.EnumStatus;

/**
 * Classe mãe de todas as entidades persistentes. Um de seus objetivos é poder
 * criar métodos genericos como por exemplo o crud, que atenda a todas as
 * entidades filhas de Entidade.
 * 
 * @author Hylston Natann
 * 
 */
@MappedSuperclass
public abstract class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public abstract String getPropriedadeRelevante();

	public abstract void setPropriedadeRelevante(String propriedadeRelevante);

	public abstract EnumStatus getStatus();

	public abstract void setStatus(EnumStatus status);

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Entidade other = (Entidade) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return this.getId() != null ? this.getId().toString() : "";
	}

	public boolean isNew() {

		return this.getId() == null;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

}
