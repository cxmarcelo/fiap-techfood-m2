package br.com.fiap.techfood.core.usecase.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.fiap.techfood.core.dataprovider.ClientDataProvider;
import br.com.fiap.techfood.core.dataprovider.OrderDataProvider;
import br.com.fiap.techfood.core.dataprovider.ProductDataProvider;
import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.OrderRequestDomain;
import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;
import br.com.fiap.techfood.core.domain.exceptions.DataIntegrityException;
import br.com.fiap.techfood.core.domain.exceptions.ObjectNotFoundException;
import br.com.fiap.techfood.core.usecase.OrderUseCase;

public class OrderUseCaseImpl implements OrderUseCase {

	private final OrderDataProvider orderDataProvider;
	private final ProductDataProvider productDataProvider ;
	private final ClientDataProvider clientDataProvider ;


	public OrderUseCaseImpl(
			OrderDataProvider orderDataProvider,
			ProductDataProvider productDataProvider,
			ClientDataProvider clientDataProvider) {
		this.orderDataProvider = orderDataProvider;
		this.productDataProvider = productDataProvider;
		this.clientDataProvider = clientDataProvider;
	}

	@Override
	public OrderDomain save(OrderRequestDomain orderRequest, String clientCpf) {
		var orderDomain = new OrderDomain();
		orderDomain.setStatus(OrderStatusEnum.AWAITING_PAYMENT);

		if(orderRequest == null) {
			throw new DataIntegrityException("Invalid Order request: orderRequest is null.");
		}

		if (orderRequest.getOrderName() != null && orderRequest.getOrderName().length() >= 3) {
			orderDomain.setName(orderRequest.getOrderName());
		} else {
			throw new DataIntegrityException("There must be at least 3 characters in the order name");
		}

		if (orderRequest.getRequestProducts() == null || orderRequest.getRequestProducts().isEmpty()) {
			throw new DataIntegrityException("No products were selected.");
		}

		if (clientCpf != null && !clientCpf.isBlank()) {
			var clientDomainOpt = clientDataProvider.findByCpf(clientCpf);
			if (clientDomainOpt.isEmpty()) {
				throw new DataIntegrityException("Client with CPF " +clientCpf + " not found. Make sure you have a registration.");
			}
			orderDomain.setClient(clientDomainOpt.get());
			orderDomain.setIsAnonymous(false);
		}

		orderRequest.getRequestProducts().stream().forEach(requestProduct -> {
			if (requestProduct.getQuantity() == null || requestProduct.getQuantity() <= 0) {
				throw new DataIntegrityException("Ordered Item Quantity cannot be less than or equal to 0");
			}
		});

		Set<UUID> productIdList = orderRequest.getRequestProducts().stream()
				.map(orderItemDomain -> orderItemDomain.getProductId()).collect(Collectors.toSet());

		var productList = productDataProvider.findAllByIds(productIdList);
		if (productList.size() != productIdList.size()) {
			throw new ObjectNotFoundException("Not all requested products were found.");
		}

		orderDomain.setItems(orderRequest.getRequestProducts());
		orderDomain = orderDataProvider.save(orderDomain);
		return orderDomain;
	}

	@Override
	public OrderDomain findById(UUID id) {
		var optOrderDomain = orderDataProvider.findById(id);
		if (optOrderDomain.isEmpty()) {
			throw new ObjectNotFoundException("Order with id " + id + " not found.");
		}
		return optOrderDomain.get();
	}

	@Override
	public List<OrderDomain> findAllByStatus(OrderStatusEnum orderStatus) {
		return orderDataProvider.findAllByOrderStatus(orderStatus);
	}

	@Override
	public void delete(UUID id) {
		var orderDomain = this.findById(id);

		if (!orderDomain.getStatus().getCode().equals(OrderStatusEnum.AWAITING_PAYMENT.getCode())) {
			throw new DataIntegrityException("It is only possible to delete an order with the status of awaiting payment.");
		}

		orderDataProvider.delete(orderDomain.getId());
	}

	@Override
	public String approvePayment(UUID id) {
		var orderDomain = this.findById(id);

		if (!orderDomain.getStatus().getCode().equals(OrderStatusEnum.AWAITING_PAYMENT.getCode())) {
			throw new DataIntegrityException("It is only possible to approve payment for an order with status Awaiting Payment.");
		}

		orderDataProvider.updateStatus(id, OrderStatusEnum.PAYMENT_APPROVED);
		return "Payment Aprroved";
	}

	@Override
	public void prepareOrder(UUID id) {
		var orderDomain = this.findById(id);

		if (!orderDomain.getStatus().getCode().equals(OrderStatusEnum.PAYMENT_APPROVED.getCode())) {
			throw new DataIntegrityException("It is only possible to prepare orders with Payment Approved status.");
		}

		orderDataProvider.updateStatus(id, OrderStatusEnum.PREPARED);
	}

	@Override
	public void finishOrder(UUID id) {
		var orderDomain = this.findById(id);

		if (!orderDomain.getStatus().getCode().equals(OrderStatusEnum.PREPARED.getCode())) {
			throw new DataIntegrityException("It is only possible to finalize orders with a Ready status.");
		}

		orderDataProvider.updateStatus(id, OrderStatusEnum.FINISHED);
	}

}
