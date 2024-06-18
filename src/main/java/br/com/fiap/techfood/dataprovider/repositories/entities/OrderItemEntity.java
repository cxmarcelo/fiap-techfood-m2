package br.com.fiap.techfood.dataprovider.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_ORDER_ITEMS")
public class OrderItemEntity {

	@EmbeddedId
	private OrderItemPk id;

	@Column(nullable = false)
	private Integer quantity;

	private String description;

}
