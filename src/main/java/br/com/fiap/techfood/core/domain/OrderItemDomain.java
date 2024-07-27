package br.com.fiap.techfood.core.domain;

import java.math.BigDecimal;

public class OrderItemDomain {

	private Integer quantity;
	private ProductDomain product;
	private String description;

	public OrderItemDomain() {
	}

	public OrderItemDomain(Integer quantity, ProductDomain product, String description) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductDomain getProduct() {
		return product;
	}

	public void setProduct(ProductDomain product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getTotal() {
		if(this.quantity == null || this.product == null) {
			return BigDecimal.ZERO;
		}
		
		return new BigDecimal(this.quantity).multiply(this.product.getPrice());
	}

}
