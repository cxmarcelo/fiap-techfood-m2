package br.com.fiap.techfood.entrypoint.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderItemDto {

	private UUID productId;
	private Integer quantity;
	private String description;

}
