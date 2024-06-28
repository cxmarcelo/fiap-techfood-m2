package br.com.fiap.techfood.entrypoint.dtos.mercadopago;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MercadoPagoWebhookDTO {

	private Long id;
	private Boolean live_mode;
	private String type;
	private OffsetDateTime date_created;
	private Long user_id;
	private String api_version;
	private String action;

	private MercadoPagoWebhookDataDTO data;

}
