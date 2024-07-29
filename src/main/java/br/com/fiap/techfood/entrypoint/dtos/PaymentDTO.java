package br.com.fiap.techfood.entrypoint.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PaymentDTO {

	private UUID id;
	private LocalDateTime dateCreated;
	private LocalDateTime dateOfExpiration;
	private BigDecimal amount;
	private String qrCode;
	private PaymentStatusEnum status;


}
