package br.com.tresb.util;

/**
 * Responsavel por fornecer metodos utilitarios para a classe String.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public class UtilString {

	public static final String upperToLowercase(String string, int posBegin, int posEnd) {

		String lower = string.substring(posBegin, posEnd).toLowerCase();

		return string.substring(0, posBegin) + lower + string.substring(posEnd, string.length());
	}

	public static boolean isNotEmpty(String str) {

		return str != null && !str.isEmpty();
	}

}
