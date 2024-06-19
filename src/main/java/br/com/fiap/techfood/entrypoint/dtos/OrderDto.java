package br.com.fiap.techfood.entrypoint.dtos;

import java.util.List;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderDto {

	private UUID id;
	private String name;
	private List<OrderItemDto> items;
	private OrderStatusEnum status;
	private Boolean isAnonymous;
	private ClientDTO client;

}
