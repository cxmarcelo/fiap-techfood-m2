package br.com.fiap.techfood.entrypoint.mappers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.OrderItemDomain;
import br.com.fiap.techfood.core.domain.OrderRequestDomain;
import br.com.fiap.techfood.entrypoint.dtos.OrderCreateDTO;
import br.com.fiap.techfood.entrypoint.dtos.OrderDto;
import br.com.fiap.techfood.entrypoint.dtos.OrderItemDto;

@Component
public class OrderMapper {

	@Autowired
	private ClientMapper clientMapper;

	public OrderDto toOrderDto(OrderDomain orderDomain) {
		var orderDto = new OrderDto();
		BeanUtils.copyProperties(orderDomain, orderDto);

		if(orderDomain.getItems() != null) {
			orderDto.setItems(orderDomain.getItems().stream().map(orderItem -> toOrderItemDto(orderItem) ).toList());
		}

		if(orderDomain.getClient() != null) {
			orderDto.setClient(clientMapper.toClientDTO(orderDomain.getClient()));
		}

		return orderDto;
	}

	public OrderItemDto toOrderItemDto(OrderItemDomain orderItemDomain) {
		var orderItemDto = new OrderItemDto();
		BeanUtils.copyProperties(orderItemDomain, orderItemDto);
		return orderItemDto;
	}

	public OrderRequestDomain toOrderRequestDomain(OrderCreateDTO orderCreateDto) {
		var orderRequestDomain = new OrderRequestDomain();
		orderRequestDomain.setOrderName(orderCreateDto.getOrderName());
		orderRequestDomain.setRequestProducts(toOrderItemDomainList(orderCreateDto.getOrderItems()));
		return orderRequestDomain;
	}

	public List<OrderItemDomain> toOrderItemDomainList(List<OrderItemDto> items) {
		if (items == null) {
			return null;
		}
		return items.stream().map(orderItemDto -> this.toOrderItemDomain(orderItemDto)).toList();
	}

	public OrderItemDomain toOrderItemDomain(OrderItemDto orderItemDto) {
		var orderItemDomain = new OrderItemDomain();
		BeanUtils.copyProperties(orderItemDto, orderItemDomain);
		return orderItemDomain;
	}

}
