package br.com.fiap.techfood.entrypoint.dtos.mercadopago;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MercadoPagoWebhookDataDTO {

	private String id;

}
