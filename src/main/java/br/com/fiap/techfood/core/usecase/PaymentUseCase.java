package br.com.fiap.techfood.core.usecase;

import java.util.UUID;

import br.com.fiap.techfood.core.domain.PaymentDomain;
import br.com.fiap.techfood.core.domain.enums.PaymentProviderEnum;

public interface PaymentUseCase {

	PaymentDomain createExternalPayment(UUID orderId, PaymentProviderEnum orderPaymentProvider);

}
