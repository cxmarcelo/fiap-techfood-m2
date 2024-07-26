package br.com.fiap.techfood.dataprovider.repositories.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.OrderItemDomain;
import br.com.fiap.techfood.core.domain.PaymentDomain;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderItemEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderItemPk;
import br.com.fiap.techfood.dataprovider.repositories.entities.PaymentEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.PaymentPk;
import br.com.fiap.techfood.dataprovider.repositories.entities.ProductEntity;

@Component
public class OrderEntityMapper {

	@Autowired
	private ClientEntityMapper clientEntityMapper;

	@Autowired
	private ProductEntityMapper productEntityMapper;

	public OrderEntity toOrderEntity(OrderDomain orderDomain) {
		var orderEntity = new OrderEntity();
		BeanUtils.copyProperties(orderDomain, orderEntity);
		orderEntity.setStatus(orderDomain.getStatus());
		return orderEntity;
	}

	public OrderDomain toOrderDomain(OrderEntity orderEntity) {
		var orderDomain = new OrderDomain();
		BeanUtils.copyProperties(orderEntity, orderDomain);
		orderDomain.setStatus(orderEntity.getStatus());
		
		if (orderEntity.getClient() != null && orderEntity.getClient().getId() != null) {
			orderDomain.setClient(clientEntityMapper.toClientDomain(orderEntity.getClient()));;
		}
		
		orderDomain.setItems(toOrderItemDomainList(orderEntity.getItems()));
		orderDomain.setPayments(toPaymentDomainList(orderEntity.getPayments()));

		return orderDomain;
	}

	public List<OrderItemEntity> toOrderItemEntityList(List<OrderItemDomain> orderItemDomainList, OrderEntity orderEntity) {
		return orderItemDomainList.stream().map(orderItemDomain -> toOrderItemEntity(orderItemDomain, orderEntity)).collect(Collectors.toList());
	}

	public OrderItemEntity toOrderItemEntity(OrderItemDomain orderItemDomain, OrderEntity orderEntity) {
		var orderItemEntity = new OrderItemEntity();
		BeanUtils.copyProperties(orderItemDomain, orderItemEntity);
		orderItemEntity.setId(new OrderItemPk(orderEntity, new ProductEntity(orderItemDomain.getProduct().getId())));
		return orderItemEntity;
	}

	public List<OrderItemDomain>  toOrderItemDomainList(List<OrderItemEntity> orderItemEntityList) {
		return orderItemEntityList.stream().map(orderItemEntity -> toOrderItemDomain(orderItemEntity)).collect(Collectors.toList());
	}

	public OrderItemDomain toOrderItemDomain(OrderItemEntity orderItemEntity) {
		var orderItemDomain = new OrderItemDomain();
		BeanUtils.copyProperties(orderItemEntity, orderItemDomain);
		orderItemDomain.setProduct(productEntityMapper.toProductDomain(orderItemEntity.getId().getProduct()));
		return orderItemDomain;
	}

	public List<PaymentEntity> toPaymentEntityList(List<PaymentDomain> paymentDomainList, OrderEntity orderEntity) {
		return paymentDomainList.stream().map(paymentDomain -> toPaymentEntity(paymentDomain, orderEntity)).collect(Collectors.toList());
	}

	public PaymentEntity toPaymentEntity(PaymentDomain paymentDomain, OrderEntity orderEntity) {
		var paymentEntity = new PaymentEntity();
		BeanUtils.copyProperties(paymentDomain, paymentEntity);
		paymentEntity.setId(new PaymentPk(orderEntity, paymentDomain.getId()));
		paymentEntity.setStatus(paymentDomain.getStatus());
		return paymentEntity;
	}
	
	public List<PaymentDomain> toPaymentDomainList(List<PaymentEntity> paymentEntityList) {
		return paymentEntityList.stream().map(paymentEntity -> toPaymentDomain(paymentEntity)).collect(Collectors.toList());
	}

	public PaymentDomain toPaymentDomain(PaymentEntity paymentEntity) {
		var paymentDomain = new PaymentDomain();
		BeanUtils.copyProperties(paymentEntity, paymentDomain);
		paymentDomain.setId(paymentEntity.getId().getId());
		paymentDomain.setStatus(paymentEntity.getStatus());
		return paymentDomain;
	}

	
}
