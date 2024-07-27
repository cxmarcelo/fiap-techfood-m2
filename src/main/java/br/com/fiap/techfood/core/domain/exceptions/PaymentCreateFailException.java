package br.com.fiap.techfood.core.domain.exceptions;

public class PaymentCreateFailException extends Exception {
	private static final long serialVersionUID = 1L;

	public PaymentCreateFailException(String message) {
		super(message);
	}

	public PaymentCreateFailException(String message, Throwable cause) {
		super(message, cause);
	}

}
