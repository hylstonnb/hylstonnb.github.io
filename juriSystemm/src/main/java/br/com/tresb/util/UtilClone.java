package br.com.tresb.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UtilClone<O extends Object> {

	public List<O> clonar(List<O> lista) {

		List<O> result = new ArrayList<O>();

		for (O entidade : lista) {

			result.add(entidade);
		}

		return result;
	}
}
