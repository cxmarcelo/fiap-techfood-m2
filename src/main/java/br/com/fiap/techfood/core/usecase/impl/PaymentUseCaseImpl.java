package br.com.fiap.techfood.core.usecase.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.fiap.techfood.core.dataprovider.OrderDataProvider;
import br.com.fiap.techfood.core.dataprovider.PaymentDataProvider;
import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.PaymentDomain;
import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;
import br.com.fiap.techfood.core.domain.enums.PaymentProviderEnum;
import br.com.fiap.techfood.core.domain.exceptions.DataIntegrityException;
import br.com.fiap.techfood.core.domain.exceptions.InternalServerErrorException;
import br.com.fiap.techfood.core.domain.exceptions.ObjectNotFoundException;
import br.com.fiap.techfood.core.domain.exceptions.PaymentCreateFailException;
import br.com.fiap.techfood.core.usecase.PaymentUseCase;

public class PaymentUseCaseImpl implements PaymentUseCase {

	private final OrderDataProvider orderDataProvider;
	private final Map<PaymentProviderEnum, PaymentDataProvider> mapPaymentDataProvider;


	public PaymentUseCaseImpl(OrderDataProvider orderDataProvider, List<PaymentDataProvider> paymentDataProviderList) {
		this.orderDataProvider = orderDataProvider;
		this.mapPaymentDataProvider = paymentDataProviderList.stream().collect(Collectors.toMap(PaymentDataProvider::getProviderCode, provider -> provider));
	}

	@Override
	public PaymentDomain createExternalPayment(UUID orderId, PaymentProviderEnum orderPaymentProvider) {
		var orderDomain = this.findOrderById(orderId);

		if(!(orderDomain.getStatus().equals(OrderStatusEnum.ORDER_CREATED) 
				|| orderDomain.getStatus().equals(OrderStatusEnum.PAYMENT_CREATE_FAILED)
				|| orderDomain.getStatus().equals(OrderStatusEnum.REJECTED))) {
			throw new DataIntegrityException("It is not possible to create a Payment for the order.");
		}


		try {
			PaymentDataProvider paymentDataProvider = this.mapPaymentDataProvider.get(orderPaymentProvider);

			if(paymentDataProvider == null) {
				throw new PaymentCreateFailException("Payment Service not Implemented.");
			}

			PaymentDomain paymentDomain = paymentDataProvider.createPaymentOrder(orderDomain);
			orderDomain.getPayments().add(paymentDomain);
			orderDomain.setStatus(OrderStatusEnum.AWAITING_PAYMENT);
			orderDomain = orderDataProvider.save(orderDomain);
			return paymentDomain;
		} catch (PaymentCreateFailException e) {
			orderDomain.setStatus(OrderStatusEnum.PAYMENT_CREATE_FAILED);
			orderDomain = orderDataProvider.save(orderDomain);
			e.printStackTrace();
			throw new InternalServerErrorException(e.getMessage());
		} catch (Exception e) {
			orderDomain.setStatus(OrderStatusEnum.PAYMENT_CREATE_FAILED);
			orderDomain = orderDataProvider.save(orderDomain);
			e.printStackTrace();
			throw new InternalServerErrorException("Internal failure to create payment.");
		}
	}

	private OrderDomain findOrderById(UUID id) {
		var optOrderDomain = orderDataProvider.findById(id);
		if (optOrderDomain.isEmpty()) {
			throw new ObjectNotFoundException("Order with id " + id + " not found.");
		}
		return optOrderDomain.get();
	}
	
	@Override
	public String approvePaymentMock(UUID id) {
		var orderDomain = this.findOrderById(id);

		if (!orderDomain.getStatus().getCode().equals(OrderStatusEnum.AWAITING_PAYMENT.getCode())) {
			throw new DataIntegrityException("It is only possible to approve payment for an order with status Awaiting Payment.");
		}

		orderDataProvider.updateStatus(id, OrderStatusEnum.PAYMENT_APPROVED);
		return "Payment Aprroved";
	}


}
