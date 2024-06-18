package br.com.fiap.techfood.core.domain;

import java.util.UUID;

public class OrderItemDomain {

	private Integer quantity;
	private UUID productId;
	private String description;

	public OrderItemDomain() {
	}

	public OrderItemDomain(Integer quantity, UUID productId, String description) {
		super();
		this.quantity = quantity;
		this.productId = productId;
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
