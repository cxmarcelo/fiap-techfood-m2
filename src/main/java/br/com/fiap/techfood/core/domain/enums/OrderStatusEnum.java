package br.com.fiap.techfood.core.domain.enums;

public enum OrderStatusEnum {

	AWAITING_PAYMENT(1),
	PAYMENT_APPROVED(2),
	PREPARED(3),
	READY_FOR_PICKUP(4),
	FINISHED(5),
	PAYMENT_REJECTED(9)
	;

	private Integer code;

	private OrderStatusEnum(Integer code) {
		this.code = code;
	}

	public static OrderStatusEnum toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (OrderStatusEnum x : OrderStatusEnum.values()) {
			if (x.getCode().equals(code)) {
				return x;
			}
		}

		throw new RuntimeException("Order Status code invalid.");
	}

	public Integer getCode() {
		return code;
	}

}
