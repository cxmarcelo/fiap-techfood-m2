package br.com.fiap.techfood.entrypoint.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.domain.PaymentDomain;
import br.com.fiap.techfood.entrypoint.dtos.PaymentDTO;

@Component
public class PaymentMapper {

	public PaymentDTO toPaymentDto(PaymentDomain paymentDomain) {
		PaymentDTO paymentDTO = new PaymentDTO();
		BeanUtils.copyProperties(paymentDomain, paymentDTO);
		return paymentDTO;
	}
	
}
