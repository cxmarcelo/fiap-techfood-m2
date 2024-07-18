package br.com.fiap.techfood.entrypoint.controllers;

import java.io.Console;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.techfood.core.usecase.PaymentUseCase;
import br.com.fiap.techfood.entrypoint.dtos.PaymentCreateDTO;
import br.com.fiap.techfood.entrypoint.dtos.PaymentDTO;
import br.com.fiap.techfood.entrypoint.dtos.mercadopago.MercadoPagoWebhookDTO;
import br.com.fiap.techfood.entrypoint.mappers.PaymentMapper;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentUseCase paymentUseCase;

	@Autowired
	private PaymentMapper paymentMapper;


	@PostMapping
	public ResponseEntity<PaymentDTO> makeOrder(@RequestBody @Validated PaymentCreateDTO paymentCreateDTO) {
		var paymentDomain = paymentUseCase.createExternalPayment(paymentCreateDTO.getOrderId(), paymentCreateDTO.getPaymentProvider());
		var responseDto = paymentMapper.toPaymentDto(paymentDomain);
		return ResponseEntity.ok().body(responseDto);
	}

	@PostMapping("/{orderId}/pay")
	public ResponseEntity<String> approvePayment(@PathVariable UUID orderId) {
		var message = paymentUseCase.approvePaymentMock(orderId);
		return ResponseEntity.ok().body(message);
	}

	//deve retornar HTTP STATUS 200 (OK) ou 201 (CREATED).
	@PostMapping("/mercado-pago/webhook")
	public ResponseEntity<Void> mercadoPagoWebHook(@RequestBody MercadoPagoWebhookDTO webhook) {
		//MERCADO PAGO MAPPER
		if(webhook.getType().equals("payment")) {
			if(webhook.getAction().equals("payment.updated")) {
				//TODO
			}
			
		}

		return ResponseEntity.ok().build();
	}


}