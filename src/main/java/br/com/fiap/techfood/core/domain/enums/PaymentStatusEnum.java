package br.com.fiap.techfood.core.domain.enums;

public enum PaymentStatusEnum {

	PENDING("pending"),
	APPROVED("approved"),
	REJECTED("rejected"),
	CANCELLED("cancelled"),
	REFUNDED("refunded"),
	;

	private String code;

	private PaymentStatusEnum(String code) {
		this.code = code;
	}

	public static PaymentStatusEnum toEnum(String code) {
		if (code == null) {
			return null;
		}

		for (PaymentStatusEnum x : PaymentStatusEnum.values()) {
			if (x.getCode().equals(code)) {
				return x;
			}
		}

		throw new RuntimeException("Payment Status code invalid. Code: " + code);
	}

	public String getCode() {
		return code;
	}

}
