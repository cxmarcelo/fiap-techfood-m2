package br.com.fiap.techfood.dataprovider.paymentclient.mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentStatus;

import br.com.fiap.techfood.core.domain.PaymentDomain;
import br.com.fiap.techfood.core.domain.enums.PaymentStatusEnum;

@Component
public class PaymentMercadoPagoMapper {

	public PaymentDomain toPaymentDomain(Payment payment) {
		PaymentDomain paymentDomain = new PaymentDomain();

		paymentDomain.setId(UUID.fromString(payment.getExternalReference()));
		paymentDomain.setExternalId(String.valueOf(payment.getId()));

		paymentDomain.setDateCreated(toLocalDateTime(payment.getDateCreated()));
		paymentDomain.setDateLastUpdated(toLocalDateTime(payment.getDateLastUpdated()));
		paymentDomain.setDateOfExpiration(toLocalDateTime(payment.getDateOfExpiration()));
		paymentDomain.setDateApproved(toLocalDateTime(payment.getDateApproved()));

		if(payment.getPointOfInteraction() != null && payment.getPointOfInteraction().getTransactionData() != null) {
			paymentDomain.setQrCode(payment.getPointOfInteraction().getTransactionData().getQrCode());
		}

		paymentDomain.setAmount(payment.getTransactionAmount());

		paymentDomain.setStatus(toStatus(payment.getStatus()));


		return paymentDomain;
	}

	private PaymentStatusEnum toStatus(String status) {
		switch (status) {
		case PaymentStatus.PENDING: {
			return PaymentStatusEnum.PENDING;
		}

		case PaymentStatus.APPROVED: {
			return PaymentStatusEnum.APPROVED;
		}

		case PaymentStatus.REJECTED: {
			return PaymentStatusEnum.REJECTED;
		}

		case PaymentStatus.CANCELLED: {
			return PaymentStatusEnum.CANCELLED;
		}

		case PaymentStatus.REFUNDED: {
			return PaymentStatusEnum.REFUNDED;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + status);
		}
	}

	private LocalDateTime toLocalDateTime(OffsetDateTime date) {
		if(date == null) {
			return null;
		}
		return date.toLocalDateTime();
	}

}
