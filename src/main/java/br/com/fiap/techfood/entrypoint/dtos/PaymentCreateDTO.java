package br.com.fiap.techfood.entrypoint.dtos;

import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.PaymentProviderEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PaymentCreateDTO {

	@NotNull
	private UUID orderId;

	@NotNull
	private PaymentProviderEnum paymentProvider;

}
