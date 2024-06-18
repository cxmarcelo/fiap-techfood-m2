package br.com.fiap.techfood.core.domain;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.CategoryEnum;

public class ProductDomain {

	private  UUID id;
	private  String name;
	private  String description;
	private  BigDecimal price;
	private  CategoryEnum category;
	private  String imageURL;

	public ProductDomain() {
		super();
	}

	public ProductDomain(String name, String description, BigDecimal price, CategoryEnum category, String imageURL) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.imageURL = imageURL;
	}

	public ProductDomain(UUID id, String name, String description, BigDecimal price, CategoryEnum category,
			String imageURL) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.imageURL = imageURL;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
