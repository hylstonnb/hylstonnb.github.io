package br.com.tresb.util;

import org.apache.commons.validator.routines.EmailValidator;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;

/**
 * Classe de validacao de CNPJ, CPF e E-mail.
 * 
 * @author sergio
 * @author Hylston Natann
 * 
 * @version 1.1
 */
public class UtilValidator {

	private static CPFValidator cpfValidator = new CPFValidator();

	private static CNPJValidator cnpjValidator = new CNPJValidator();

	private static final String CNPJ_INVALIDO = "CNPJ inválido!";

	private static final String EMAIL_INVALIDO = "E-mail inválido!";

	private static final String CPF_INVALIDO = "CPF inválido!";

	/**
	 * Valida cpf
	 * 
	 * @param cpf
	 */
	public static boolean isNotValidCPF(String cpf) {

		try {

			cpfValidator.assertValid(cpf);

			return false;

		} catch (Exception e) {

			UtilMessages.addMessageWarn(CPF_INVALIDO);

			return true;
		}
	}

	/**
	 * Valida cnpj
	 * 
	 * @param cnpj
	 */
	public static boolean isNotValidCNPJ(String cnpj) {

		try {

			cnpjValidator.assertValid(cnpj);

			return false;

		} catch (Exception e) {

			UtilMessages.addMessageWarn(CNPJ_INVALIDO);

			return true;
		}
	}

	/**
	 * Responsavel por verificar se um e-mail Ã© valido.
	 * 
	 * @param email
	 *            - E-mail que se deseja validar
	 * 
	 * @return true se o email nao for valido, caso contrario false;
	 */
	public static boolean isNotValidEmail(String email) {

		boolean result = false;

		if (!email.isEmpty() && !EmailValidator.getInstance().isValid(email)) {

			result = true;

			UtilMessages.addMessageWarn(EMAIL_INVALIDO);
		}

		return result;
	}

}