package br.com.fiap.techfood.core.domain.enums;

public enum OrderStatusEnum {

	//TODO MUDAR PARA STRING?
	ORDER_CREATED(1),
	AWAITING_PAYMENT(2),
	PAYMENT_APPROVED(3),
	REJECTED(4),
	PREPARED(5),
	FINISHED(6),
	PAYMENT_CREATE_FAILED(7),
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
