package br.com.fiap.techfood.core.dataprovider;

import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.PaymentDomain;
import br.com.fiap.techfood.core.domain.enums.PaymentProviderEnum;
import br.com.fiap.techfood.core.domain.exceptions.PaymentCreateFailException;

public interface PaymentDataProvider {

	public PaymentDomain createPaymentOrder(OrderDomain orderDomain) throws PaymentCreateFailException;
	
	//TODO
	public PaymentDomain createPaymentLink(OrderDomain orderDomain);

	public PaymentProviderEnum getProviderCode();

}
