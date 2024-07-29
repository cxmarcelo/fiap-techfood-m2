package br.com.fiap.techfood.dataprovider;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.dataprovider.PaymentDataProvider;
import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.PaymentDomain;
import br.com.fiap.techfood.core.domain.enums.PaymentProviderEnum;
import br.com.fiap.techfood.core.domain.enums.PaymentStatusEnum;
import br.com.fiap.techfood.core.domain.exceptions.PaymentCreateFailException;
import br.com.fiap.techfood.dataprovider.repositories.OrderRepository;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.PaymentEntity;

@Component
public class MockPaymentDataProviderImpl implements PaymentDataProvider {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public PaymentDomain createPaymentOrder(OrderDomain orderDomain) throws PaymentCreateFailException {
		PaymentDomain paymentDomain = new PaymentDomain();

		paymentDomain.setId(UUID.randomUUID());
		paymentDomain.setExternalId(UUID.randomUUID().toString());

		paymentDomain.setDateCreated(LocalDateTime.now());
		paymentDomain.setDateLastUpdated(LocalDateTime.now());
		paymentDomain.setDateOfExpiration(LocalDateTime.now().plusMinutes(30));
		paymentDomain.setDateApproved(null);

		paymentDomain.setQrCode("QR_CODE_FAKE_" + paymentDomain.getId());

		paymentDomain.setAmount(orderDomain.getTotal());

		paymentDomain.setStatus(PaymentStatusEnum.PENDING);
		return paymentDomain;
	}

	@Override
	public PaymentDomain checkPaymentStatus(String externalPaymentId) throws PaymentCreateFailException {
		var orderEntity = this.fakeExternalSearch(externalPaymentId);

		PaymentDomain paymentDomain = new PaymentDomain();
		PaymentEntity paymentDomainActive = orderEntity.getPayments().stream().filter(payment -> payment.getExternalId().equals(externalPaymentId)).findFirst().get();
		BeanUtils.copyProperties(paymentDomainActive, paymentDomain);

		paymentDomain.setId(paymentDomainActive.getId().getId());
		paymentDomain.setExternalId(externalPaymentId);
		paymentDomain.setDateCreated(LocalDateTime.now());
		paymentDomain.setDateLastUpdated(LocalDateTime.now());
		paymentDomain.setDateOfExpiration(LocalDateTime.now().plusMinutes(30));
		paymentDomain.setDateApproved(LocalDateTime.now());
		paymentDomain.setStatus(PaymentStatusEnum.APPROVED);
		return paymentDomain;
	}

	private OrderEntity fakeExternalSearch(String externalPaymentId) throws PaymentCreateFailException {
		return orderRepository.findOrderByPaymentExternalId(externalPaymentId).orElseThrow(() -> new PaymentCreateFailException("Error to get payment. Invalid Long Id: " + externalPaymentId));
	}

	@Override
	public PaymentProviderEnum getProviderCode() {
		return PaymentProviderEnum.MOCK_PAYMENT;
	}


}
