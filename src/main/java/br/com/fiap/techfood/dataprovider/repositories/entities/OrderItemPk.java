package br.com.fiap.techfood.dataprovider.repositories.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemPk {

	@ManyToOne
	@JoinColumn(name = "order_id",  foreignKey =  @ForeignKey(name="fk_order_item_order"))
	private OrderEntity order;

	@ManyToOne
	@JoinColumn(name = "product_id",  foreignKey = @ForeignKey(name="fk_order_item_product"))
	private ProductEntity product;

}
