package br.com.fiap.techfood.core.domain.enums;

public enum CategoryEnum {

	SNACK(1, "Lanche"),
	SIDE_DISH(2, "Acompanhamento"),
	DRINK(3, "Bebida"),
	DESSERT(4, "Sobremesa"),
	;

	private Integer code;
	private String description;

	private CategoryEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public CategoryEnum toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (CategoryEnum x : CategoryEnum.values()) {
			if (x.getCode().equals(code)) {
				return x;
			}
		}

		throw new RuntimeException("Order Status code invalid.");
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
