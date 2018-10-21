package br.com.tresb.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Classe responsavel por oferecer metodos de comparacao de objetos
 * 
 * @author sergio
 * @author Hylston Natann
 * 
 * @version 1.1
 */
public class UtilObjeto {

	public static boolean isDifferent(Object objeto1, Object objeto2) {

		return objeto1 == null ? objeto2 != null : !objeto1.equals(objeto2);
	}

	public static String beanAlias(Class<?> clazz) {

		return StringUtils.uncapitalize(clazz.getSimpleName());
	}

	public static boolean isTrue(Boolean bool) {

		return BooleanUtils.isTrue(bool);
	}

	public static boolean isFalse(Boolean bool) {

		return !UtilObjeto.isTrue(bool);
	}

	public static boolean isNotEmpty(Object objeto) {

		return !UtilObjeto.isEmpty(objeto);
	}

	public static void main(String[] args) {

		System.out.println(UtilObjeto.getHashCode("ilion123"));
	}

	public static String getHashCode(String text) {

		try {
			MessageDigest m;

			m = MessageDigest.getInstance("MD5");

			m.update(text.getBytes(), 0, text.length());

			return new BigInteger(1, m.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

	public static String getSalt() {

		return RandomStringUtils.randomAlphanumeric(16);
	}

	public static boolean isEmpty(Object objeto) {

		if (objeto == null) {
			return true;
		} else if (objeto instanceof Collection<?>) {
			return CollectionUtils.isEmpty((Collection<?>) objeto);
		} else if (objeto instanceof Map<?, ?>) {
			return MapUtils.isEmpty((Map<?, ?>) objeto);
		} else if (objeto instanceof String) {
			return StringUtils.isEmpty((String) objeto);
		} else if (objeto instanceof Object[]) {
			return ((Object[]) objeto).length == 0;
		} else if (objeto instanceof Date) {
			return ((Date) objeto) == null;
		} else {
			throw new RuntimeException("Tipo de objeto [" + objeto.getClass() + "] nao e suportado");
		}
	}

	public static String splitCamelCase(String name) {

		return name.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}

	public static List<String> formarListaComString(String string, String delim) {

		List<String> lista = null;

		if (string == null || string.length() == 0) {
			return lista;
		}

		lista = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(string, delim);

		while (st.hasMoreElements()) {

			String e = (String) st.nextElement();

			lista.add(e);
		}

		return lista;
	}

	public static String primeiraLetraCaixaAlta(String string) {

		if (string != null && string.length() > 0) {

			string = string.trim();

			string = string.toLowerCase();

			string = string.substring(0, 1).toUpperCase() + string.substring(1, string.length());

			String aux = string;

			while (aux.contains(" ")) {

				int pos = aux.indexOf(" ");

				aux = aux.replaceFirst(" ", "-");

				string = string.substring(0, pos + 1) + string.substring(pos + 1, pos + 2).toUpperCase() + string.substring(pos + 2, string.length());
			}
		}

		return string;
	}

}