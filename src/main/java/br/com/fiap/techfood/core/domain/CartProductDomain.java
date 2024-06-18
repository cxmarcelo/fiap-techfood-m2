package br.com.fiap.techfood.core.domain;

public class CartProductDomain {

	private ProductDomain product;
	private Integer quantity;
	private String description;

	public CartProductDomain() {
		super();
	}

	public CartProductDomain(ProductDomain product, Integer quantity, String description) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.description = description;
	}

	public ProductDomain getProduct() {
		return product;
	}

	public void setProduct(ProductDomain product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
