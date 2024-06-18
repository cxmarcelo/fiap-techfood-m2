package br.com.fiap.techfood.dataprovider.repositories.entities;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PRODUCTS")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private Integer category;

	@Column(nullable = false)
	private String imageURL;

	public ProductEntity() {
		super();
	}

	public ProductEntity(UUID id) {
		super();
		this.id = id;
	}

	public ProductEntity(UUID id, String name, String description, BigDecimal price, Integer category,
			String imageURL) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.imageURL = imageURL;
	}

	public CategoryEnum getCategory() {
		return CategoryEnum.toEnum(this.category);
	}

	public void setCategory(CategoryEnum category) {
		this.category = category.getCode();
	}

}
