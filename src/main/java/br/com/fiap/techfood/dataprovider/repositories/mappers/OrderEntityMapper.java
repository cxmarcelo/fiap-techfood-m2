package br.com.fiap.techfood.dataprovider.repositories.mappers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.OrderItemDomain;
import br.com.fiap.techfood.core.domain.OrderItemRequestDomain;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderItemEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderItemPk;
import br.com.fiap.techfood.dataprovider.repositories.entities.ProductEntity;

@Component
public class OrderEntityMapper {

	@Autowired
	private ClientEntityMapper clientEntityMapper;

	@Autowired
	private ProductEntityMapper productEntityMapper;

	public OrderEntity toOrderEntity(OrderDomain orderDomain) {
		var orderEntity = new OrderEntity();
		orderEntity.setStatus(orderDomain.getStatus());
		orderEntity.setIsAnonymous(orderDomain.getIsAnonymous());
		orderEntity.setName(orderDomain.getName());
		return orderEntity;
	}

	public OrderDomain toOrderDomain(OrderEntity orderEntity) {
		var orderDomain = new OrderDomain();
		orderDomain.setId(orderEntity.getId());
		//
		orderDomain.setItems(toOrderItemDomainList(orderEntity.getItems()));
		orderDomain.setStatus(orderEntity.getStatus());
		orderDomain.setIsAnonymous(orderEntity.getIsAnonymous());
		orderDomain.setName(orderEntity.getName());

		if (orderEntity.getClient() != null && orderEntity.getClient().getId() != null) {
			orderDomain.setClient(clientEntityMapper.toClientDomain(orderEntity.getClient()));;
		}

		return orderDomain;
	}

	public List<OrderItemEntity> toOrderItemEntityList(List<OrderItemDomain> orderItemDomainList, OrderEntity orderEntity) {
		return orderItemDomainList.stream().map(orderItemDomain -> toOrderItemEntity(orderItemDomain, orderEntity)).toList();
	}

	public OrderItemEntity toOrderItemEntity(OrderItemDomain orderItemDomain, OrderEntity orderEntity) {
		var orderItemEntity = new OrderItemEntity();
		BeanUtils.copyProperties(orderItemDomain, orderItemEntity);
		orderItemEntity.setId(new OrderItemPk(orderEntity, new ProductEntity(orderItemDomain.getProduct().getId())));
		return orderItemEntity;
	}

	public List<OrderItemDomain>  toOrderItemDomainList(List<OrderItemEntity> orderItemEntityList) {
		return orderItemEntityList.stream().map(orderItemEntity -> toOrderItemDomain(orderItemEntity)).toList();
	}

	/*
	public List<OrderItemRequestDomain>  toOrderItemRequestDomainList(List<OrderItemEntity> orderItemEntityList) {
		return orderItemEntityList.stream().map(orderItemEntity -> toOrderItemRequestDomain(orderItemEntity)).toList();
	}
	*/

	public OrderItemDomain toOrderItemDomain(OrderItemEntity orderItemEntity) {
		var orderItemDomain = new OrderItemDomain();
		BeanUtils.copyProperties(orderItemEntity, orderItemDomain);
		orderItemDomain.setProduct(productEntityMapper.toProductDomain(orderItemEntity.getId().getProduct()));
		return orderItemDomain;
	}

	/*
	public OrderItemRequestDomain toOrderItemRequestDomain(OrderItemEntity orderItemEntity) {
		var orderItemDomain = new OrderItemRequestDomain();
		BeanUtils.copyProperties(orderItemEntity, orderItemDomain);
		orderItemDomain.setProductId(orderItemEntity.getId().getProduct().getId());
		return orderItemDomain;
	}
	*/

}
