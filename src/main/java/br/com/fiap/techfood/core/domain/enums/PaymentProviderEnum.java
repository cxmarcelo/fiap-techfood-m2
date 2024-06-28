package br.com.fiap.techfood.core.domain.enums;

public enum PaymentProviderEnum {

	MERCADO_PAGO("MERCADO_PAGO"),
	;

	private String code;

	private PaymentProviderEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
